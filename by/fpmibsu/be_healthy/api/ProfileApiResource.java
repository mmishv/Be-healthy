package by.fpmibsu.be_healthy.api;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.oas.annotations.parameters.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProfileApiResource extends ProfileService{
/*
    @PUT
    @Path("/{user_id}")
    @ApiOperation(value = "Update a user", notes = "Updates a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully updated schema"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public String updateProfile(
            @ApiParam(value = "User id", required = true) @PathParam("user_id") Integer user_id,
            FormDataMultiPart multiPart) {
        if (null != movie) {
            EntityTag movieTag = getMovieTag(movie);
            Response.ResponseBuilder responseBuilder
                    = request.evaluatePreconditions(
                    movieTag);
            if (null == responseBuilder) {
                responseBuilder = Response.ok(movie)
                        .tag(movieTag);
            }
            return responseBuilder.build();
        } else {
            throw new WebApplicationException(
                    Response.Status.NOT_FOUND);
        }
        return update(user_id, multiPart);
    }

    @DELETE
    @Path("/users/{user_id}")
    @ApiOperation(value = "Delete a user", notes = "Deletes a user by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Error deleting user")})
    public SuccessResponse deleteSchema(
            @ApiParam(value = "user id", required = true) @PathParam("user_id") Integer user_id) {
        deleteSchemaInternal(schemaName);
        return new SuccessResponse("Schema " + schemaName + " deleted");
    }
*/

    @POST
    @ApiOperation(value = "Creates new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    public Response createUser(
            @ApiParam(value = "User", required = true) @RequestBody Profile profile) {
        if (create(profile)){
            return Response.ok(profile).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @GET
    @Path("/{id}")
    @ApiOperation(value = "Gets user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")
            })
    public Response getUserById(
            @ApiParam(value = "User ID", required = true) @PathParam("id") Integer id) {
        try {
            String profile = getEntityByIdJSON(id);
            return Objects.equals(profile, "null") ? Response.status(Response.Status.NOT_FOUND).build() :
                    Response.ok(profile).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @ApiOperation(value = "Gets list of all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public Response getAllUsers(){
        try {
            String profiles = getAllJSON();
            return Response.ok(profiles).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}