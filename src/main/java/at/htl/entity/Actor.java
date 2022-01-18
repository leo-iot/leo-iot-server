package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema(description = "contains the value of the active actor")
public class Actor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(required = true)
    private Long id;

    @ManyToOne
    private Thing thing;

    @ManyToOne
    private ActorType actortype;

    @Schema(required = true)
    public double value;

    public Actor() { }


    public Actor(Thing thing, ActorType actortype, double value) {
        this();
        this.thing = thing;
        this.actortype = actortype;
        this.value = value;
    }

    public Actor(Long id, Thing thing, ActorType actortype, double value) {
        this(thing, actortype, value);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", thing=" + thing +
                ", actortype=" + actortype +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Double.compare(actor.value, value) == 0 && Objects.equals(id, actor.id) && Objects.equals(thing, actor.thing) && Objects.equals(actortype, actor.actortype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thing, actortype, value);
    }
}
