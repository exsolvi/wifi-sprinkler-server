package se.exsolvi.wifisprinkler.server.bootstrap;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import se.exsolvi.wifisprinkler.server.model.ConfigModel;
import se.exsolvi.wifisprinkler.server.model.Request;

public class Config {

    private static final String UPDATE_FREQUENCE_KEY = "updatefrequence";
    private static final String DEVICE_ID_KEY = "deviceid";
    private static final String DATA_PATH_KEY = "datapath";
    private static final String DEVICE_NAME_KEY = "devicename";

    private static final String deviceId = "A01";

    public ConfigModel handleRequest(Request<?> request, Context context) {

        LambdaLogger logger = context.getLogger();

        try {
            logger.log("Request: " + request.toString());
            DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
            Table configTable = dynamoDB.getTable("DeviceConfig");
            logger.log("Fetched table: " + configTable);
            Item deviceConfig = configTable.getItem(DEVICE_ID_KEY, deviceId);
            logger.log("Fetched item: " + deviceConfig);
            ConfigModel configModel = new ConfigModel();
            configModel.setDeviceId(deviceId);
            configModel.setUpdateFrequence(deviceConfig.getInt(UPDATE_FREQUENCE_KEY));
            configModel.setDataPath(deviceConfig.getString(DATA_PATH_KEY) + "/" + deviceConfig.getString(DEVICE_NAME_KEY).toLowerCase());
            logger.log("Sent config device: " + deviceId);
            return configModel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}