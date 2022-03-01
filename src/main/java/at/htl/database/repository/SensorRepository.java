package at.htl.database.repository;

import at.htl.database.entity.Sensor;
import at.htl.database.entity.SensorType;
import at.htl.database.entity.Thing;
import at.htl.database.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SensorRepository implements Repository<Sensor, Long> {

    @Inject
    ThingRepository thingRepository;

    @Inject
    LocationRepository locationRepository;

    @Inject
    SensorTypeRepository sensorTypeRepository;

    public Sensor getOrCreateSensorByTree(SensorType sensorType, Thing thing) {
        var query = getEntityManager().createQuery(
                "select s from Sensor s where s.sensorType = :sensorType and s.thing = :thing",
                Sensor.class
        );

        query.setParameter("sensorType", sensorType);
        query.setParameter("thing", thing);

        return query
                .getResultStream()
                .findFirst()
                .orElseGet(() -> save(new Sensor(
                    thing,
                    sensorType
                )));
    }
}
