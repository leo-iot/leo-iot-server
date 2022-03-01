package at.htl.database.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Schema(description = "contains a thing and a sensortype")
public class Sensor extends DataBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public Thing thing;

    @ManyToOne
    public SensorType sensorType;

    public Sensor() {
    }

    public Sensor(Thing thing, SensorType sensorType) {
        this();
        this.thing = thing;
        this.sensorType = sensorType;
    }

    public Sensor(Long id, Thing thing, SensorType sensorType) {
        this(thing, sensorType);
        this.id = id;
    }
}
