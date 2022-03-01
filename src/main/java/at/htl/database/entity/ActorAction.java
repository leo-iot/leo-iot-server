
package at.htl.database.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Schema(description = "contains the value of the active actoraction")
public class ActorAction extends DataBaseEntity {

    @EmbeddedId
    public ActorActionKey actorActionKey;

    @Schema(required = true)
    public double value;

    public ActorAction (ActorActionKey actorActionKey, double value){
        this();
        this.actorActionKey = actorActionKey;
        this.value = value;
    }

    public ActorAction (){

    }

    @Embeddable
    public static class ActorActionKey implements Serializable {

        public Timestamp timestamp;

        @ManyToOne
        public Actor actor;

        public ActorActionKey(){

        }

        public ActorActionKey(Timestamp timestamp, Actor actor){
            this();
            this.timestamp = timestamp;
            this.actor = actor;
        }
    }
}

