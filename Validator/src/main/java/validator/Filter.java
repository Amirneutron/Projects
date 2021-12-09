package dit355.validator;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface Filter {
    boolean validate(MqttMessage message);
}
