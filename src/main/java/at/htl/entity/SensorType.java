package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema(description = "contains the sensortype of ta sensor")
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToOne
    public Unit unit;

    public SensorType() { }

    public SensorType(String name, Unit unit) {
        this();
        this.name = name;
        this.unit = unit;
    }

    public SensorType(Long id, String name, Unit unit) {
        this(name, unit);
        this.id = id;
    }

    @Override
    public String toString() {
        return "SensorType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorType that = (SensorType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit);
    }
}
