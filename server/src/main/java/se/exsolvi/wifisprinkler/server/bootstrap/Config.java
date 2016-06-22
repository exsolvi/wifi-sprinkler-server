package se.exsolvi.wifisprinkler.server.bootstrap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import se.exsolvi.wifisprinkler.server.model.ConfigModel;

@Path("/")
public class Config {

    static final Logger logger = Logger.getLogger(Config.class);

    private static final String UPDATE_FREQUENCE_KEY = "updatefrequence";
    private static final String DEVICE_ID_KEY = "deviceid";
    private static final String DATA_PATH_KEY = "datapath";
    private static final String DEVICE_NAME_KEY = "devicename";

    @GET
    @Path("/config/{deviceid}")
    public Response exampleEndpoint(@PathParam("deviceid") String deviceId) {

        try {
            DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
            Table configTable = dynamoDB.getTable("DeviceConfig");
            logger.log(Level.INFO, "Fetched table: " + configTable);
            Item deviceConfig = configTable.getItem(DEVICE_ID_KEY, deviceId);
            logger.log(Level.INFO,"Fetched item: " + deviceConfig);
            ConfigModel configModel = new ConfigModel();
            configModel.setDeviceId(deviceId);
            configModel.setUpdateFrequence(deviceConfig.getInt(UPDATE_FREQUENCE_KEY));
            configModel.setDataPath(deviceConfig.getString(DATA_PATH_KEY) + "/" + deviceConfig.getString(DEVICE_NAME_KEY).toLowerCase());
            logger.log(Level.INFO,"Sent config device: " + deviceId);
            return Response.status(200).entity(configModel).build();
        } catch (Exception e) {
            logger.error("RuntimeException: " + e.toString());
            throw new RuntimeException(e);
        }

    }
}