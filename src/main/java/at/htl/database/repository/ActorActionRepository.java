package at.htl.database.repository;

import at.htl.database.entity.ActorAction;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.util.List;

@ApplicationScoped
public class ActorActionRepository implements Repository<ActorAction, ActorAction.ActorActionKey> {

    public List<ActorAction> getActorActionByTimestamp(Timestamp timestamp){
        var query = getEntityManager().createQuery(
                "select a " +
                "from ActorAction a " +
                "where a.actorActionKey.timestamp = :searchedTimestamp",
                ActorAction.class);

        query.setParameter("searchedTimestamp", timestamp);
        return query.getResultList();
    }

}
