
package at.htl.database.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Schema(description = "contains the unit type/symbol of the sensortype")
public class Unit extends DataBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonbProperty("symbol")
    public String symbol;

    @JsonbTransient
    @OneToMany(mappedBy = "unit")
    public List<SensorType> sensorTypeList;

    @JsonbTransient
    @OneToMany(mappedBy = "unit")
    public List<ActorType> actorTypeList;


    public Unit(){
        this.sensorTypeList = new LinkedList<>();
        this.actorTypeList = new LinkedList<>();
    }

    public Unit(String symbol) {
        this();
        this.symbol = symbol;
    }

    public Unit(Long id, String symbol) {
        this(symbol);
        this.id = id;
    }
}


