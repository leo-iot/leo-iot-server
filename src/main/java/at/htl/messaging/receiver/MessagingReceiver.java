package at.htl.messaging.receiver;

import at.htl.database.repository.MeasurementRepository;
import at.htl.database.repository.MessageRepository;
import io.quarkus.runtime.BlockingOperationNotAllowedException;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MessagingReceiver {

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
