package at.htl.repository;

import at.htl.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    public Sensor updateSensor(Sensor sensor) {
        Sensor s = findById(sensor.id);
        if(s == null){
            return null;
        }
        s.sensorType = sensor.sensorType;
        s.thing = sensor.thing;
        return s;
    }
}
