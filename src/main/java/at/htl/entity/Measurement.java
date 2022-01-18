package at.htl.entity;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Schema(description = "contains the value of the  measurement")
public class Measurement {

    @EmbeddedId
    private MeasurementKey measurementKey;

    @Schema(required = true)
    private double value;

    public Measurement(){

    }

    public Measurement(MeasurementKey measurementKey, double value){
        this();
        this.measurementKey = measurementKey;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementKey=" + measurementKey +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(measurementKey, that.measurementKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(measurementKey, value);
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

        @Override
        public String toString() {
            return "MeasurementKey{" +
                    "timestamp=" + timestamp +
                    ", sensor=" + sensor +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MeasurementKey that = (MeasurementKey) o;
            return Objects.equals(timestamp, that.timestamp) && Objects.equals(sensor, that.sensor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, sensor);
        }
    }
}
