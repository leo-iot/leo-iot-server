package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Schema(description = "contains a thingList and a location of the current location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public Location location;

    @JsonbTransient
    @OneToMany(mappedBy = "location")
    public List<Thing> thingList;

    @JsonbProperty("location_name")
    public String name;

    public Location() {
        this.thingList = new LinkedList<>();
    }

    public Location(Location location, String name) {
        this();
        this.location = location;
        this.name = name;
    }

    public Location(Long id, Location location, String name) {
        this(location, name);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", location=" + location +
                ", thingList=" + thingList +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return Objects.equals(id, location1.id) && Objects.equals(location, location1.location) && Objects.equals(thingList, location1.thingList) && Objects.equals(name, location1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, thingList, name);
    }
}
