package at.htl.mqtt;

import at.htl.entity.Measurement;
import at.htl.entity.Thing;
import at.htl.repository.MeasurementRepository;
import at.htl.repository.MessageRepository;
import at.htl.repository.ThingRepository;
import io.quarkus.runtime.BlockingOperationNotAllowedException;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MqttReceiver {

    @Inject
    MessageRepository messageRepository;

    @Inject
    MeasurementRepository measurementRepository;

    @ConfigProperty(name = "leoiot.testing")
    Boolean isTesting;

    /*https://github.com/smallrye/smallrye-reactive-messaging/issues/1945
    https://github.com/smallrye/smallrye-reactive-messaging/issues/1906
    https://github.com/smallrye/smallrye-reactive-messaging/pull/1948
    https://github.com/smallrye/smallrye-reactive-messaging/issues/750
    https://gitter.im/smallrye-io/community?at=5f60d49589b38d09212cf7b5
    */
    //TODO: will crash when to many sensors are active at startup
    // Current solution: multiple income channels, not 100% guaranteed that this will work
    // (there is no way to change it currently with this implementation)
    // See Links above
    @Incoming("sensordata")
    @Blocking
    public CompletionStage<Void> processEg(MqttMessage<byte[]> message) {
        processMessage(message);
        return message.ack();
    }

    @Incoming("sensordata_ug")
    @Blocking
    public CompletionStage<Void> processUg(MqttMessage<byte[]> message) {
        processMessage(message);
        return message.ack();
    }

    @Incoming("sensordata_og")
    @Blocking
    public CompletionStage<Void> processOg(MqttMessage<byte[]> message) {
        processMessage(message);
        return message.ack();
    }

    @Incoming("sensordata_og2")
    @Blocking
    public CompletionStage<Void> processOg2(MqttMessage<byte[]> message) {
        processMessage(message);
        return message.ack();
    }

    private void processMessage(MqttMessage<byte[]> message){
        try {
            String messageContent = new String(message.getPayload());
            if(!isTesting)
                messageRepository.processingMessage(message.getTopic(), messageContent);
        } catch (BlockingOperationNotAllowedException exception) {
            exception.printStackTrace();
        }
    }
}
