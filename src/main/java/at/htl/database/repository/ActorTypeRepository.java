package at.htl.database.repository;

import at.htl.database.entity.ActorType;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ActorTypeRepository implements Repository<ActorType, Long> {

    public Optional<ActorType> getByName(String name) {
        return find("name", name)
                .firstResultOptional();
    }
}
