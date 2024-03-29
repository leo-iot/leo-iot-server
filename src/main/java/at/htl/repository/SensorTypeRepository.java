package at.htl.repository;

import at.htl.entity.SensorType;

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

    public SensorType updateSensorType(SensorType sensorType) {
        SensorType st = findById(sensorType.id);
        if(st == null){
            return null;
        }
        st.unit = sensorType.unit;
        st.name = sensorType.name;
        return st;
    }
}
