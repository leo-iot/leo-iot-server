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
public class Thing {

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

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", location=" + location +
                ", actorList=" + actorList +
                ", sensorList=" + sensorList +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thing thing = (Thing) o;
        return Objects.equals(id, thing.id) && Objects.equals(location, thing.location) && Objects.equals(actorList, thing.actorList) && Objects.equals(sensorList, thing.sensorList) && Objects.equals(name, thing.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, actorList, sensorList, name);
    }
}
