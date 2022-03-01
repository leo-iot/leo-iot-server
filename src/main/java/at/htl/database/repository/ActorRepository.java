package at.htl.database.repository;

import at.htl.database.entity.Actor;
import at.htl.database.entity.ActorType;
import at.htl.database.entity.Thing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActorRepository implements Repository<Actor, Long> {

    public Actor getOrCreateActorByTree(ActorType actorType, Thing thing, double value) {
        var query = getEntityManager().createQuery(
                "select a from Actor a where a.thing = :thing and a.actortype = :actorType",
                Actor.class
        );

        query.setParameter("thing", thing);
        query.setParameter("actorType", actorType);

        return query
                .getResultStream()
                .findFirst()
                .orElseGet(() -> save(new Actor(
                        thing,
                        actorType,
                        value
                )));
    }
}
