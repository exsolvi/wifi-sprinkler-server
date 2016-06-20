package se.exsolvi.wifisprinkler.server.model;

public class ConfigModel {

    private String deviceId;
    private int updateFrequence;
    private String dataPath;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getUpdateFrequence() {
        return updateFrequence;
    }

    public void setUpdateFrequence(int updateFrequence) {
        this.updateFrequence = updateFrequence;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

}
