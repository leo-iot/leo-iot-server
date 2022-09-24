package at.htl.boundary;

import at.htl.dto.AddLocationDto;
import at.htl.entity.Location;
import at.htl.repository.LocationRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("location")
@Tag(name ="Location REST endpoint")
public class LocationResource {

    @Inject
    LocationRepository locationRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get a location",
            description = "get the desired location by id"
    )
    public Response getLocation(@QueryParam("id") Long locationId){
        if (locationId != null){
            Location l = locationRepository.findById(locationId);
            if(l == null){
                return Response.status(404).build();
            }
            return Response
                    .accepted(l)
                    .build();
        }else{
            return Response
                    .accepted(locationRepository.listAll())
                    .build();
        }
    }

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save a location",
            description = "save the desired location"
    )
    @Transactional
    public Response addLocation(AddLocationDto locationDto){
        return Response
                .accepted(locationRepository.add(locationDto.name, locationDto.parentLocationId))
                .build();
    }

    @DELETE
    @Operation(
            summary =  "delete a location",
            description = "delete the desired location"
    )
    @Transactional
    public Response deleteLocationById(@QueryParam("id") Long locationId){
        return Response
                .accepted(locationRepository.deleteById(locationId))
                .build();
    }

    @PUT
    @Operation(
            summary = "update a location",
            description = "update the desired location"
    )
    @Transactional
    public Response updateLocation(Location location){
        Location l = locationRepository.update(location);
        if(l == null){
            return Response.status(404).build();
        }
        return Response
                .accepted(l)
                .build();
    }

}
