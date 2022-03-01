package at.htl.api.resources;

import at.htl.database.entity.Actor;
import at.htl.database.entity.ActorAction;
import at.htl.database.repository.ActorActionRepository;
import at.htl.database.repository.ActorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("actoraction")
@Tag(name = "ActorAction REST endpoint")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActorActionResource {

    @Inject
    ActorActionRepository actorActionRepository;

    @Inject
    ActorRepository actorRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get an actoraction",
            description = "get the desired actoraction by timestamp"
    )
    public Response getActorActionByTimestamp(@QueryParam("timestamp") Long actorActionTimestamp ) {
        if (actorActionTimestamp != null) {
            return Response
                    .accepted(actorActionRepository.getActorActionByTimestamp(new Timestamp(actorActionTimestamp * 1000)))
                    .build();
        } else {
            return Response
                    .accepted(actorActionRepository.findAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save an actoraction",
            description = "save the desired actor object"

    )
    public Response addActorAction(@QueryParam("actorId") Long actorId, JsonObject jsonObject){
        ActorAction actorAction = new ActorAction();
        actorAction.value = jsonObject.getInt("value");
        ActorAction.ActorActionKey actorActionKey =
                new ActorAction.ActorActionKey(new Timestamp(jsonObject.getJsonObject("actorActionKey").getInt("timestamp")*1000), new Actor());
        actorActionKey.actor = actorRepository.findById(actorId);
        actorAction.actorActionKey = actorActionKey;
        return Response.accepted( actorActionRepository.save(actorAction)).build();
    }

    @DELETE
    @Operation(
            summary = "delete an actoraction",
            description = "delete the desired actoraction object"
    )
    public Response removeActorAction(ActorAction actorAction){
        actorActionRepository.delete(actorAction);
        return Response.accepted(actorAction).build();
    }
}
