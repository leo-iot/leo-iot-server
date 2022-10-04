package at.htl.boundary;

import at.htl.entity.Location;
import at.htl.entity.Thing;
import at.htl.repository.ThingRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("thing")
@Tag(name = "Thing REST endpoint")
public class ThingResource {

    @Inject
    ThingRepository thingRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get a thing",
            description = "get the desired thing by id"
    )
    public Response getThing(@QueryParam("id") Long thingId){
        if(thingId != null) {
            Thing t = thingRepository.findById(thingId);
            if(t == null){
                return Response.status(404).build();
            }
            return Response
                    .accepted(t)
                    .build();
        }else {
            return  Response
                    .accepted(thingRepository.listAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save a thing",
            description = "save the desired thing"
    )
    @Transactional
    public Response addThing(Thing thing){
        return Response
                .accepted(thingRepository.save(thing))
                .build();
    }

    @DELETE
    @Operation(
            summary = "delete a thing",
            description = "delte a thing by id"
    )
    @Transactional
    public Response deleteThingById(@QueryParam("id") Long thingId){
        return Response
                .accepted(thingRepository.deleteById(thingId))
                .build();
    }

    @PUT
    @Operation(
            summary = "update a location",
            description = "update the desired location"
    )
    @Transactional
    public Response updateThing(Thing thing){
        Thing t = thingRepository.updateThing(thing);
        if(t == null){
            return Response.status(404).build();
        }
        return Response
                .accepted(t)
                .build();
    }
}
