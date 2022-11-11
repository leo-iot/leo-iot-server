package at.htl.repository;

import at.htl.dto.MessageProcessingDto;
import at.htl.entity.*;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

@ApplicationScoped
public class MessageRepository {

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    LocationRepository locationRepository;

    @Inject
    ThingRepository thingRepository;

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @Inject
    ActorTypeRepository actorTypeRepository;

    @Inject
    SensorRepository sensorRepository;

    @Inject
    ActorRepository actorRepository;

    @Inject
    ActorActionRepository actorActionRepository;

    public static MessageProcessingDto convertToMessageProcessingDto(String str){
        str = str.replaceAll("}","");
        str = str.replaceAll("\\{","");
        var parts = str.split(",");
        double val = 0;
        long timestamp = 0;
        if(parts[0].split(":")[0].equals("\"value\"")){
            val = Double.parseDouble(parts[0].split(":")[1]);
            timestamp = Long.parseLong(parts[1].split(":")[1]);
        }
        else{
            val = Double.parseDouble(parts[1].split(":")[1]);
            timestamp = Long.parseLong(parts[0].split(":")[1]);
        }

        return new MessageProcessingDto(timestamp, val);
    }

    @Transactional
    public void processingMessage(String topic, String message) {
        MessageProcessingDto mpObject = convertToMessageProcessingDto(message);

        var pathSegments = topic.split("/");
        String deviceString = pathSegments[pathSegments.length - 2];
        String thingString = pathSegments[pathSegments.length - 3];
        String[] locationStrings = Arrays
                .stream(pathSegments)
                .limit(pathSegments.length - 2)
                .toList()
                .toArray(new String[pathSegments.length - 2]);
        double value = mpObject.value();
        // * 1000 for converting seconds to milliseconds

        var timeStamp = new Timestamp(mpObject.timestamp() * 1000);
        var location = locationRepository.getLocationByTree(locationStrings);
        var thing = thingRepository.getOrCreateByTree(thingString, location);

        var sensorType = sensorTypeRepository.getByName(deviceString);
        var actorType = actorTypeRepository.getByName(deviceString);

        if (sensorType.isPresent()) {
            var sensor = sensorRepository.getOrCreateSensorByTree(sensorType.get(), thing);
            measurementRepository.save(processMeasurement(
                    sensor,
                    timeStamp,
                    value
            ));
        } else if (actorType.isPresent()) {
            var actor = actorRepository.getOrCreateActorByTree(actorType.get(), thing, value);
            actor.value = value;
            actorActionRepository.save(processActorAction(
                    actor,
                    timeStamp,
                    value
            ));
        } else {
            var newSensorType = sensorTypeRepository.save(
                    new SensorType(deviceString, null)
            );

            var sensor = sensorRepository.save(
                    new Sensor(thing, newSensorType)
            );

            measurementRepository.persist(processMeasurement(
                    sensor,
                    timeStamp,
                    value
            ));
        }
    }

    private Measurement processMeasurement(Sensor sensor, Timestamp timeStamp, double value) {
        return new Measurement(
                new Measurement.MeasurementKey(
                        timeStamp,
                        sensor
                ),
                value
        );
    }

    private ActorAction processActorAction(Actor actor, Timestamp timeStamp, double value) {
        return new ActorAction(
                new ActorAction.ActorActionKey(
                        timeStamp,
                        actor
                ),
                value
        );
    }
}
