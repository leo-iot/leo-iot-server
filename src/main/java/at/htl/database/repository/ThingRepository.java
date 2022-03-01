package at.htl.database.repository;

import at.htl.database.entity.Location;
import at.htl.database.entity.Thing;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.Optional;

@ApplicationScoped
public class ThingRepository implements Repository<Thing, Long> {

    public Thing getOrCreateByTree(String name, Location location) {
        return getThingByNameAndLocation(name, location)
                .orElseGet(() -> save(new Thing(
                        location,
                        name
                )));
    }

    private Optional<Thing> getThingByNameAndLocation(String name, Location location) {
        TypedQuery<Thing> query;
        if (location != null) {
            query = getEntityManager().createQuery(
                    "select t from Thing t where t.name = :name and t.location = :location",
                    Thing.class
            );

            query.setParameter("location", location);
        } else {
            query = getEntityManager().createQuery(
                    "select t from Thing t where t.name = :name and t.location is null",
                    Thing.class
            );
        }

        query.setParameter("name", name);

        return query
                .getResultStream()
                .findFirst();
    }
}
