package at.htl.api.resources;

import at.htl.database.entity.SensorType;
import at.htl.database.repository.SensorTypeRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sensortype")
@Tag(name= "Sensortype endpoint")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorTypeResource {

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @GET
    @Operation(
            summary = "get a SensorTYpe",
            description ="get the desired sensortype by id"
    )
    public Response getSensorType(@QueryParam("id") Long sensorTypeId){
        if (sensorTypeId != null){
            return Response
                    .accepted(sensorTypeRepository.findById(sensorTypeId))
                    .build();
        }else {
            return Response
                    .accepted(sensorTypeRepository.findAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation (
            summary = "save a sensortype",
            description = "save the desired sensortype"
    )
    public Response addSensorType(SensorType sensorType){
        return Response
                .accepted(sensorTypeRepository.save(sensorType))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "delete a actor",
            description = "delete the desired sensorType by id"
    )
    public Response deleteSensorTypeById(@QueryParam("id") Long sensorTypeId){
        return Response
                .accepted(sensorTypeRepository.deleteById(sensorTypeId))
                .build();
    }

}
