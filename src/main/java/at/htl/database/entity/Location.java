package at.htl.database.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Schema(description = "contains a thingList and a location of the current location")
public class Location extends DataBaseEntity {

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
}
