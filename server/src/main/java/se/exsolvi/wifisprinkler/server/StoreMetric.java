package se.exsolvi.wifisprinkler.server;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import se.exsolvi.wifisprinkler.server.model.Request;
import se.exsolvi.wifisprinkler.server.model.SensorData;

public class StoreMetric {

    public String handleRequest(Request<SensorData> sensorDataRequest, Context context) {

        LambdaLogger logger = context.getLogger();
        SensorData sensorData = sensorDataRequest.getRequestBody();
        
        try {
            logger.log("Request: " + sensorDataRequest.toString());
            logger.log("SensorData: " + sensorData);
            Dimension plant = new Dimension().withName("plant").withValue(sensorData.getSensorName());
            MetricDatum moisture = new MetricDatum().withMetricName("Moisture").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getMoisture());
            MetricDatum humididty = new MetricDatum().withMetricName("Humidity").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getHumidity());
            MetricDatum temperature = new MetricDatum().withMetricName("Temperature").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getTemperature());
            MetricDatum light = new MetricDatum().withMetricName("Light").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getLight());
            PutMetricDataRequest metricDataRequest = new PutMetricDataRequest().withNamespace("Test01").withMetricData(moisture).withMetricData(humididty)
                    .withMetricData(temperature).withMetricData(light);

            AmazonCloudWatch acw = new AmazonCloudWatchClient();
            acw.putMetricData(metricDataRequest);
            logger.log("Sensor data sent to CloudWatch");

            // DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
            // Table sensorDataTable = dynamoDB.getTable("SensorData");

            return "Ok";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}