package se.exsolvi.wifisprinkler.server.model;

public class LambdaRequest {
    private String bodyJson;
    private String params;
    private String stageVariables;
    private String context;

    public String getBodyjson() {
        return bodyJson;
    }

    public void setBodyjson(String bodyJson) {
        this.bodyJson = bodyJson;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStagevariables() {
        return stageVariables;
    }

    public void setStagevariables(String stageVariables) {
        this.stageVariables = stageVariables;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return String.format("LambdaRequest [bodyJson=%s, params=%s, stageVariables=%s, context=%s]", bodyJson, params, stageVariables, context);
    }
}