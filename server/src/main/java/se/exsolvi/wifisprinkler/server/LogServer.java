package se.exsolvi.wifisprinkler.server;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import se.exsolvi.wifisprinkler.server.model.LogData;

@Path("/")
public class LogServer {

    @PUT
    @Path("/log/{deviceid}/")
    public Response metricEndpoint(@PathParam("deviceid") String deviceId, LogData logData) {
        
        Logger logger = Logger.getLogger(deviceId);
        
        try {
            switch (logData.getLoglevel()) {
            case NONE:
                logger.log(Level.OFF, logData.getMessage());
            case TRACE:
                logger.log(Level.TRACE, logData.getMessage());
            case DEBUG:
                logger.log(Level.DEBUG, logData.getMessage());
            case ERROR:
                logger.log(Level.ERROR, logData.getMessage());
            case WARN:
                logger.log(Level.WARN, logData.getMessage());
            case INFO:
                logger.log(Level.INFO, logData.getMessage());
            }
            return Response.status(200).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}