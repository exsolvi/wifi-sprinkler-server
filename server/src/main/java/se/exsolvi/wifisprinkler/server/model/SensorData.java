package se.exsolvi.wifisprinkler.server.model;

import java.io.Serializable;

public class SensorData implements Serializable {

    // @formatter:off
    /*
       {
         "SensorName": "gurka", 
         "Moisture" : "512.0",
         "Humidity": "63.1",
         "Temperature" : "23.2",
         "Light": "143.4"
       }
     */
    // @formatter:on

    private static final long serialVersionUID = 1L;
    private String sensorName;
    private double moisture;
    private double humidity;
    private double temperature;
    private double light;

    public String getSensorName() {
        return sensorName;
    }

    public double getMoisture() {
        return moisture;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getLight() {
        return light;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public SensorData withSensorName(String sensorName) {
        this.sensorName = sensorName;
        return this;
    }

    public SensorData withMoisture(double moisture) {
        this.moisture = moisture;
        return this;
    }

    public SensorData withHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public SensorData withTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public SensorData withLight(double light) {
        this.light = light;
        return this;
    }

    @Override
    public String toString() {
        return String.format("SensorData [sensorName=%s, moisture=%s, humidity=%s, temperature=%s, light=%s]", sensorName, moisture, humidity, temperature,
                light);
    }

}
