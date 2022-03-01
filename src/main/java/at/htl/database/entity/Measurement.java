package at.htl.database.entity;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Schema(description = "contains the value of the  measurement")
public class Measurement extends DataBaseEntity {

    @EmbeddedId
    public MeasurementKey measurementKey;

    @Schema(required = true)
    public double value;

    public Measurement(){

    }

    public Measurement(MeasurementKey measurementKey, double value){
        this();
        this.measurementKey = measurementKey;
        this.value = value;
    }

    @Embeddable
    public static class MeasurementKey implements Serializable{

        public Timestamp timestamp;

        @ManyToOne
        public Sensor sensor;

        public MeasurementKey() { }

        public MeasurementKey(Timestamp timestamp, Sensor sensor) {
            this();
            this.timestamp = timestamp;
            this.sensor = sensor;
        }
    }
}
