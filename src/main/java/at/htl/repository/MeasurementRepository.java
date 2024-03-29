package at.htl.repository;

import at.htl.entity.Measurement;
import at.htl.entity.Sensor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

@ApplicationScoped
public class MeasurementRepository
        implements Repository<Measurement, Measurement.MeasurementKey>
{

    @Inject
    SensorRepository sensorRepository;

    @Inject
    LocationRepository locationRepository;

    @Inject
    ThingRepository thingRepository;

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @Inject
    ActorTypeRepository actorTypeRepository;

    @Inject
    ActorRepository actorRepository;

    public List<Measurement> get(Timestamp from, Timestamp to, Sensor sensor) throws IllegalArgumentException {
        if (from.after(to)) throw new IllegalArgumentException();
        var query = getEntityManager().createQuery(
                "select m " +
                        "from Measurement m " +
                        "where m.measurementKey.timestamp >= :fromTimestamp " +
                        "and m.measurementKey.timestamp <= :toTimestamp " +
                        "and m.measurementKey.sensor = :sensor",
                Measurement.class
        );

        query.setParameter("fromTimestamp", from);
        query.setParameter("toTimestamp", to);
        query.setParameter("sensor", sensor);

        return query.getResultList();
    }

    public List<Measurement> get(Timestamp from, Timestamp to) throws IllegalArgumentException {
        if (from.after(to)) throw new IllegalArgumentException();
        var query = getEntityManager().createQuery(
                "select m " +
                        "from Measurement m " +
                        "where m.measurementKey.timestamp >= :fromTimestamp " +
                        "and m.measurementKey.timestamp <= :toTimestamp",
                Measurement.class
        );

        query.setParameter("fromTimestamp", from);
        query.setParameter("toTimestamp", to);

        return query.getResultList();
    }

    public List<Measurement> getMeasurementByTimestamp (Timestamp timestamp){
        System.out.println(timestamp.toString());
        var query = getEntityManager().createQuery(
                "select  m " +
                        "from Measurement m " +
                        "where m.measurementKey.timestamp = :searchedTimstamp",
                Measurement.class);

        query.setParameter("searchedTimstamp", timestamp);
        return  query.getResultList();
    }

}
