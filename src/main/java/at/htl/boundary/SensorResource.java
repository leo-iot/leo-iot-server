package at.htl.boundary;

import at.htl.entity.Sensor;
import at.htl.repository.SensorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sensor")
@Tag(name = "Sensor REST endpoint")
public class SensorResource {

    @Inject
    SensorRepository sensorRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get a sensor",
            description = "get the desired sensor by id"
    )
    public Response getSensor(@QueryParam("id") Long sensorId){
        if (sensorId != null){
            Sensor s = sensorRepository.findById(sensorId);
            if(s == null){
                return Response.status(404).build();
            }
            return Response
                    .accepted(s)
                    .build();
        }else {
            return Response
                    .accepted(sensorRepository.listAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save a sensor",
            description = "save the desired sensor object"
    )
    @Transactional
    public Response addSensor(Sensor sensor){
        return Response
                .accepted(sensorRepository.save(sensor))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "delete a sensor",
            description = "delete the desired sensor by id"
    )
    @Transactional
    public Response deleteSensorById(@QueryParam("id") Long sensorId){
        return Response
                .accepted(sensorRepository.deleteById(sensorId))
                .build();
    }

    @PUT
    @Operation(
            summary = "update a unit",
            description = "update the desired unit"
    )
    @Transactional
    public Response updateSensor(Sensor sensor){
        Sensor s = sensorRepository.updateSensor(sensor);
        if(s == null){
            return Response.status(404).build();
        }
        return Response
                .accepted(s)
                .build();
    }
}
