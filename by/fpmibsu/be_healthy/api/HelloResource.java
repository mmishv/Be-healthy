package by.fpmibsu.be_healthy.api;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, Gruppe 2!!! XML SHIT HER";
    }
}
/*
@Path("/user")
*/
/*@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)*//*

public class ProfileApi extends Application {
*/
/*    @PUT
    @Path("/users/{user_id}")
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
    }*//*


*/
/*    @GET
*//*
*/
/*    @Path("/{id}/get")*//*
*/
/*
    @ApiOperation(value = "List instance for a user, or enable/disable/drop a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Error reading user list")})
    public String getEntityById(
            @ApiParam(value = "User ID", required = true) @PathParam("id") Integer id) {
        try {
            return new ProfileService().getEntityByIdJSON(1);
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }*//*

    @GET
    @Produces("text/plain")
    public String getHelloText() {
        return "Hello, World!";
    }

    @GET
    @Produces("text/html")
    public String getHelloHtml() {
        return "<html>" + "<head><title>Hello</title></head>"

                + "<body>Hello, World!</body>" + "</html>";
    }

}*/
