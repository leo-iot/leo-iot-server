package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema(description = "contains the sensortype of ta sensor")
public class SensorType extends DataBaseEntity {

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
}
