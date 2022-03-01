package at.htl.database.repository;

import at.htl.database.entity.*;
import at.htl.database.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import java.sql.Timestamp;
import java.util.Arrays;

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

    public void processingMessage(String topic, String message) {
        JsonObject object = JsonbBuilder
                .create()
                .fromJson(message, JsonObject.class);

        var pathSegments = topic.split("/");
        String deviceString = pathSegments[pathSegments.length - 2];
        String thingString = pathSegments[pathSegments.length - 3];
        String[] locationStrings = Arrays
                .stream(pathSegments)
                .limit(pathSegments.length - 3)
                .toList()
                .toArray(new String[pathSegments.length - 3]);
        double value = object.getJsonNumber("value").doubleValue();
        // * 1000 for converting seconds to milliseconds

        var timeStamp = new Timestamp(object.getJsonNumber("timestamp").longValue() * 1000);
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
