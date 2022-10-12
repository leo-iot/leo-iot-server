package at.htl.boundary;

import at.htl.entity.Location;
import at.htl.entity.SensorType;
import at.htl.entity.Unit;
import at.htl.repository.SensorTypeRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sensortype")
@Tag(name= "Sensortype endpoint")
public class SensorTypeResource {

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @GET
    @Operation(
            summary = "get a SensorType",
            description ="get the desired sensortype by id"
    )
    public Response getSensorType(@QueryParam("id") Long sensorTypeId){
        if (sensorTypeId != null){
            SensorType st = sensorTypeRepository.findById(sensorTypeId);
            if(st == null){
                return Response.status(404).build();
            }
            return Response
                    .accepted(st)
                    .build();
        }else {
            return Response
                    .accepted(sensorTypeRepository.listAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation (
            summary = "save a sensortype",
            description = "save the desired sensortype"
    )
    @Transactional
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
    @Transactional
    public Response deleteSensorTypeById(@QueryParam("id") Long sensorTypeId){
        return Response
                .accepted(sensorTypeRepository.deleteById(sensorTypeId))
                .build();
    }

    @PUT
    @Operation(
            summary = "update a unit",
            description = "update the desired unit"
    )
    @Transactional
    public Response updateSensorType(SensorType sensorType){
        SensorType st = sensorTypeRepository.updateSensorType(sensorType);
        if(st == null){
            return Response.status(404).build();
        }
        return Response
                .accepted(st)
                .build();
    }
}
