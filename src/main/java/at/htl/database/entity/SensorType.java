package at.htl.database.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

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
