package at.htl.database.repository;

import at.htl.database.entity.SensorType;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class SensorTypeRepository implements Repository<SensorType, Long> {
    public SensorType getOrCreateByName(String name) {
        return getByName(name)
                .orElseGet(() -> save(new SensorType(
                        name,
                        null
                )));
    }

    public Optional<SensorType> getByName(String name) {
        return find("name", name)
                .firstResultOptional();
    }
}
