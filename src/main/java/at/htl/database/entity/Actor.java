package at.htl.database.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import javax.persistence.*;

@Entity
@Schema(description = "contains the value of the active actor")
public class Actor extends DataBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(required = true)
    public Long id;

    @ManyToOne
    public Thing thing;

    @ManyToOne
    public ActorType actortype;

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
}
