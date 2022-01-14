package at.htl.mqtt;

import at.htl.entity.Measurement;
import at.htl.entity.Thing;
import at.htl.repository.MeasurementRepository;
import at.htl.repository.MessageRepository;
import at.htl.repository.ThingRepository;
import io.quarkus.runtime.BlockingOperationNotAllowedException;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MqttReceiver {

    @Inject
    MessageRepository messageRepository;

    @Inject
    MeasurementRepository measurementRepository;

    @Incoming("sensordata")
    @Blocking
    @Transactional
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
