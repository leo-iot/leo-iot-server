package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema (description = "contains the actortype of the active actor ")
public class ActorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonbProperty("actortype_name")
    public String name;

    @ManyToOne
    public Unit unit;

    public ActorType() {
    }

    public ActorType(String name, Unit unit) {
        this();
        this.name = name;
        this.unit = unit;
    }

    public ActorType(Long id, String name, Unit unit) {
        this(name, unit);
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ActorType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorType actorType = (ActorType) o;
        return Objects.equals(id, actorType.id) && Objects.equals(name, actorType.name) && Objects.equals(unit, actorType.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit);
    }
}
