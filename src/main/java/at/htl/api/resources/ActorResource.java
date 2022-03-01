package at.htl.api.resources;

import at.htl.database.entity.Actor;
import at.htl.database.repository.ActorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("actor")
@Tag(name = "Actor REST endpoint")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActorResource {

    @Inject
    ActorRepository actorRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get an actor",
            description = "get the desired actor by id"
    )
    public Response getActor(@QueryParam("id") Long actorId){
        if(actorId != null){
            return Response
                    .accepted(actorRepository.findById(actorId))
                    .build();
        }else {
            return Response
                    .accepted(actorRepository.findAll())
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save an actor",
            description = "save the desired actor object"
    )
    public Response addActor(Actor actor){
        return Response
                .accepted(actorRepository.save(actor))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "delete an actor",
            description = "delete the desired actor by id"
    )
    public Response deleteActorById(@QueryParam("id") Long actorId){
        return Response
                .accepted(actorRepository.deleteById(actorId))
                .build();
    }
}
