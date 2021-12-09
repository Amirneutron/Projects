package dit355.validator;

import org.eclipse.paho.client.mqttv3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Validator implements MqttCallback, Filter {

	private final static ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();
	private final static String USER_ID = "validator";
	private final static String DEFAULT_BROKER = "tcp://localhost:1883";
	private final static String DEFAULT_PUB_TOPIC = "map";
	private final static String DEFAULT_SUB_TOPIC = "external";

	private String broker;
	private String pubTopic;
	private String subTopic;
	private IMqttClient middleware;
	private CircuitBreaker circuitBreaker;

	public static void main(String[] args) throws MqttException {
		Validator validator = new Validator();
		validator.initializeConfig(args);
		validator.initializeMiddleware();
		validator.listen();
		
	}

	public Validator() {
		circuitBreaker = new CircuitBreaker();
	}

	private void initializeMiddleware() throws MqttException{
		middleware = new MqttClient(broker, USER_ID);
		middleware.connect();
		middleware.setCallback(this);
	}

	private void listen() {
		try {
			middleware.subscribe(subTopic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	private void initializeConfig(String[] args) {
		try {
			broker = args[0];
			pubTopic = args[1];
			subTopic = args[2];
		} catch (Exception e) {
			broker = DEFAULT_BROKER;
			pubTopic = DEFAULT_PUB_TOPIC;
			subTopic = DEFAULT_SUB_TOPIC;
	    }
	}

	@Override
	public boolean validate(MqttMessage message) {
		long rId;
		double origLat, origLong;
		double destLat, destLong;
		String time;

		String payload = message.toString();
		JSONParser parser = new JSONParser();
		JSONObject json;

		// Parsing payload and checking JSON object attributes for nulls

		try {
			json = (JSONObject) parser.parse(payload);
		} catch (ParseException e) {
			System.out.println("Message cannot be parsed into JSON");
			return false;
		}

		if (json.get("deviceId") == null) {
			System.out.println("Payload lacks the attribute deviceId");
			return false;
		}

		if (json.get("requestId") == null) {
			System.out.println("Payload lacks the attribute requestId");
			return false;
		}

		if (json.get("origin") == null) {
			System.out.println("Payload lacks the attribute origin");
			return false;
		}

		if (json.get("destination") == null) {
			System.out.println("Payload lacks the attribute destination");
			return false;
		}

		if (json.get("timeOfDeparture") == null) {
			System.out.println("Payload lacks the attribute timeOfDeparture");
			return false;
		} else if (! (json.get("timeOfDeparture") instanceof String)) {
			System.out.println("timeOfDeparture must be a string");
			return false;
		}

		if (json.get("purpose") == null) {
			System.out.println("Payload lacks the attribute purpose");
			return false;
		} else if (! (json.get("purpose") instanceof String)) {
			System.out.println("purpose must be a string");
			return false;
		}

		if (json.get("issuance") == null) {
			System.out.println("Payload lacks the attribute issuance");
			return false;
		}
		
		// check if deviceId is of type long; we are only interested in seeing if the parsing is successful
		try {
			Long.parseLong(json.get("deviceId").toString());
		} catch(NumberFormatException exception){
			System.out.println("deviceId is not of type long");
			return false;
		}

		//check if requestId is a positive long (as per requirements, requestId is to monotonically grow from 1)
		try {
			rId = Long.parseLong(json.get("requestId").toString());
			if(rId < 0) {
				System.out.println("requestId is negative");
				return false;
			}
		} catch(NumberFormatException exception){
			System.out.println("requestId is not of type long");
			return false;
		}

		JSONObject destination = (JSONObject)json.get("destination");
		JSONObject origin = (JSONObject)json.get("origin");

		// check if destination coordinates are of type double & if within Gbg area
		try {
			destLat = Double.parseDouble(destination.get("latitude").toString());
			destLong = Double.parseDouble(destination.get("longitude").toString());
		} catch(NumberFormatException exception){
			System.out.println("Destination latitude/longitude is not of type double");
			return false;
		}

		if (destLat < 57.46 || destLat > 58.32) {
			System.out.println("Destination latitude out of bound region");
			return false;
		}
		if (destLong < 11.61 || destLong > 12.54) {
			System.out.println("Destination longitude out of bound region");
			return false;
		}
		
		// check if origin coordinates are of type double & if within Gbg area
		try {
			origLat = Double.parseDouble(origin.get("latitude").toString());
			origLong = Double.parseDouble(origin.get("longitude").toString());
		} catch(NumberFormatException exception){
			System.out.println("Origin latitude/longitude is not of type double");
			return false;
		}

		if (origLat < 57.46 || origLat > 58.32) {
			System.out.println("Origin latitude out of bound region");
			return false;
		}
		if (origLong < 11.61 || origLong > 12.54) {
			System.out.println("Origin longitude out of bound region");
			return false;
		}

		// check if time of departure has the right format
		try {
			time = json.get("timeOfDeparture").toString();
			LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} catch (DateTimeParseException exception) {
			System.out.println("Time is not in yyyy-MM-dd HH:mm format");
			return false;
		}

		// check if issuance is a long; we are only interested in seeing if the parsing is successful
		try {
			Long.parseLong(json.get("issuance").toString());
		} catch(NumberFormatException exception){
			System.out.println("issuance is not of type long");
			return false;
		}

		return true;
	}

	private void forwardMessage(byte[] payload) {
		MqttMessage message = new MqttMessage();
		message.setQos(2);
		message.setRetained(false);
		message.setPayload(payload);

		THREAD_POOL.submit(() -> {
			try {
				middleware.publish(pubTopic, message);
			} catch (MqttException error) {
				System.out.println(error);
			}
		});
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		circuitBreaker.registerRequest();

		if (circuitBreaker.isClosed()) {

			boolean messageIsValid = validate(message);
			if (messageIsValid) {
				System.out.println("Processed travel request and forwarded");
				forwardMessage(message.getPayload());
			} else {
				System.out.println("Processed travel request, payload not valid, not forwarding");
			}
		} else {
			System.out.println("Load too high");
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}

	// On losing connection, attempts to re-establish it
	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost!");
		System.out.println("Attempting to reconnect...");

		for (int i = 0; i < 100; i++) {
			if (middleware.isConnected()) {
				System.out.println("Reconnected successfully!");
				break;
			} else {
				try {
					middleware.connect();
				} catch (MqttException error) {
					System.out.println("Failed to connect! Retrying...");
				}
			}
		}

		int attemptsLeft = 100;
		while(!middleware.isConnected() && attemptsLeft > 0)
		{
			try {
				middleware.connect();
			} 
			catch (MqttException e) 
			{
				System.out.println("Failed to connect! Retrying... Attempts left: " + attemptsLeft);
			}

			attemptsLeft--;
		}

		System.out.println("Reconnected successfully!");
	}

}
