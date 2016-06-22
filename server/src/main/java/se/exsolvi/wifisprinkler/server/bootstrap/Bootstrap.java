package se.exsolvi.wifisprinkler.server.bootstrap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@Path("/")
public class Bootstrap {

    static final Logger logger = Logger.getLogger(Bootstrap.class);

    public static final String configUrl = "https://l6bv2wlqwg.execute-api.us-east-1.amazonaws.com/prod/config";

    @GET
    @Path("/bootstrap")
    public Response bootstrapEndpoint() {


        try {
            logger.log(Level.INFO, "Sent bootstrap configuration");
            return Response.status(200).entity("{ \"configurl\" : \"" + configUrl + "}").build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}