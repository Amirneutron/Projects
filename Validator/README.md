# Validator node

This node serves as a validation layer between the data-visualizer node and
a data emitter node. It makes sure that the payload is valid in terms
of object structure and format and that the locations are within the Greater Gothenburg region (to the best of the team's abilities of approximating).

<br>

The flow of the data could be observed like this:

1. An emitter node publishes data to the topic that the Validator is listening on.
2. Validator receives the data as message.
3. Validator attempts to validate the message content (the transport request information) by 'running it through a filter'. This includes checking parameters for the right format and data type.
4. Validator forwards the payload to the Visualizer if the data respects the format and restrictions. Otherwise, it will not be forwarded.
5. The Visualizer receives it.


## What is valid data?

Valid data is data that conforms to the format restrictions stated in the requirements, and coordinates that can be pinpointed to the city of Gothenburg.

##### An example of a message payload

    {
        "deviceId": 1,
        "requestId": 2,
        "purpose": "work",
        "timeOfDeparture": "yyyy-MM-dd HH:mm",
        "origin": {
            "latitude": 57.05,
            "longitude": 58.10
        },
        "destination": {
            "latitude": 56.12,
            "longitude": 57.50
        },
        "issuance": 1349333576093
    }

## Running the node
Make sure you have Maven, Java JDK and MQTT broker installed and running.

- CD into the root of the repository
- run 'maven clean install'
- CD into 'target'
- run 'java -jar validator.jar'

By default, the node has default values for the broker adress, sub- and pub topic.
These can be configured by providing command line arguments when executing the jar file

### Example
    java -jar validator.jar tcp://someadress:x pub-topic sub-topic

