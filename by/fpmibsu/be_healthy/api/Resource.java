package by.fpmibsu.be_healthy.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@Path("/hello-world")
public class Resource extends Application {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}