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
    @PUT
    @ApiOperation(value = "Updates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Response updateUser(@ApiParam(value = "User", required = true) @RequestBody Profile profile){
        if (update(profile)){
            return Response.ok("User successfully updated").build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Deletes a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Response deleteUser(
            @ApiParam(value = "User id", required = true) @PathParam("id") Integer id) {
        if (delete(id)){
            return Response.ok("User successfully deleted").build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    @ApiOperation(value = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request. " +
                    "Probably, this username is already taken"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public Response createUser(
            @ApiParam(value = "User", required = true) @RequestBody Profile profile) {
        if (create(profile)){
            return Response.ok("User successfully created").build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @GET
    @Path("/{id}")
    @ApiOperation(value = "Gets a user by id")
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
    @ApiOperation(value = "Gets the list of all users")
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

    @PATCH
    @ApiOperation(value = "Updates user's main info (name and avatar)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Response updateUserRole(@ApiParam(value = "User", required = true) @RequestBody Profile profile){
        if (updateMainInfo(profile)){
            return Response.ok("User's role successfully updated").build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}