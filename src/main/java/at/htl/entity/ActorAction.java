
package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Schema(description = "contains the value of the active actoraction")
public class ActorAction{

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

    @Override
    public String toString() {
        return "ActorAction{" +
                "actorActionKey=" + actorActionKey +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorAction that = (ActorAction) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(actorActionKey, that.actorActionKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorActionKey, value);
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

        @Override
        public String toString() {
            return "ActorActionKey{" +
                    "timestamp=" + timestamp +
                    ", actor=" + actor +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActorActionKey that = (ActorActionKey) o;
            return Objects.equals(timestamp, that.timestamp) && Objects.equals(actor, that.actor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, actor);
        }
    }


}

