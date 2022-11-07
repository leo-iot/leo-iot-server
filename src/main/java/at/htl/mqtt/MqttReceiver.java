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

    /*https://github.com/smallrye/smallrye-reactive-messaging/issues/1945
    https://github.com/smallrye/smallrye-reactive-messaging/issues/1906
    https://github.com/smallrye/smallrye-reactive-messaging/pull/1948
    https://github.com/smallrye/smallrye-reactive-messaging/issues/750
    https://gitter.im/smallrye-io/community?at=5f60d49589b38d09212cf7b5
    */
    //TODO: will crash when to many sensors are active
    // (there is no way to change it currently with this implementation)
    // See Links above
    @Incoming("sensordata")
    @Blocking
    public CompletionStage<Void> process(MqttMessage<byte[]> message) {
        try {
            String messageContent = new String(message.getPayload());
            messageRepository.processingMessage(message.getTopic(), messageContent);
        } catch (BlockingOperationNotAllowedException exception) {
            exception.printStackTrace();
        }

        return message.ack();
    }
}
