package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Schema(description = "contains a list of actors and sensors of the  thing ")
public class Thing extends DataBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public Location location;

    @JsonbTransient
    @OneToMany(mappedBy = "thing")
    public List<Actor> actorList;

    @JsonbTransient
    @OneToMany(mappedBy = "thing")
    public List<Sensor> sensorList;

    @JsonbProperty("thing_name")
    public String name;

    public Thing(){
        this.actorList = new LinkedList<>();
        this.sensorList = new LinkedList<>();
    }

    public Thing(Location location, String name) {
        this();
        this.location = location;
        this.name = name;
    }

    public Thing(Long id, Location location, String name) {
        this(location, name);
        this.id = id;
    }
}
