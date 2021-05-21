package pl.edu.agh.soa.lab;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {

    @GET
    @Produces( { "application/json" })
    public Response hello(
            @DefaultValue("World") @QueryParam("name") String name
    ) {

        return Response
                .status(200)
                .entity("Hello, " + name + "!").build();
    }
}