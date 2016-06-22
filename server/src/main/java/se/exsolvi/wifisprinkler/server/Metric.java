package se.exsolvi.wifisprinkler.server;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import se.exsolvi.wifisprinkler.server.model.SensorData;

@Path("/")
public class Metric {

    static final Logger logger = Logger.getLogger(Metric.class);

    @PUT
    @Path("/metric/{deviceid}/")
    public Response metricEndpoint(@PathParam("deviceid") String deviceId, SensorData sensorData) {

        try {
            logger.log(Level.DEBUG, "SensorData: " + sensorData);
            Dimension plant = new Dimension().withName("plant").withValue(sensorData.getSensorName());
            MetricDatum moisture = new MetricDatum().withMetricName("Moisture").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getMoisture());
            MetricDatum humididty = new MetricDatum().withMetricName("Humidity").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getHumidity());
            MetricDatum temperature = new MetricDatum().withMetricName("Temperature").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getTemperature());
            MetricDatum light = new MetricDatum().withMetricName("Light").withUnit(StandardUnit.None).withDimensions(plant).withValue(sensorData.getLight());
            PutMetricDataRequest metricDataRequest = new PutMetricDataRequest().withNamespace("Test01").withMetricData(moisture).withMetricData(humididty)
                    .withMetricData(temperature).withMetricData(light);

            AmazonCloudWatch acw = new AmazonCloudWatchClient();
            acw.putMetricData(metricDataRequest);
            logger.log(Level.INFO, "Sensor data sent to CloudWatch");

            // DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
            // Table sensorDataTable = dynamoDB.getTable("SensorData");

            return Response.status(200).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}