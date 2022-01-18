package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema(description = "contains a thing and a sensortype")
public class Sensor {

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

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", thing=" + thing +
                ", sensorType=" + sensorType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id) && Objects.equals(thing, sensor.thing) && Objects.equals(sensorType, sensor.sensorType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thing, sensorType);
    }
}
