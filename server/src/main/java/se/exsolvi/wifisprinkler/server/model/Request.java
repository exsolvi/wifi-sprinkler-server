package se.exsolvi.wifisprinkler.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Map;

/**
 * Request class is a POJO.
 * <p>
 * Event json that lambda function got is automatically serialized to this POJO. For more details see Lambda documentation:
 * <p>
 * http://docs.aws.amazon.com/lambda/latest/dg/java-handler-io-type-pojo.html
 */
public class Request<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum RequestMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
    }

    /**
     * Package name
     */
    protected String packageName;

    /**
     * Request method
     */
    protected RequestMethod method;

    /**
     * Request path
     */
    protected String pathTemplate;

    /**
     * Request body
     */
    protected T requestBody;

    /**
     * Query parameters
     */
    protected Map<String, String> pathParameters;

    /**
     * Query parameters
     */
    protected Map<String, String> queryParams;

    /**
     * Request headers
     */
    protected Map<String, String> requestHeaders;

    /**
     * Consumed media type
     */
    protected MediaType consumedMediaType = MediaType.APPLICATION_JSON_TYPE;

    /**
     * Produced media Type
     */
    protected MediaType producedMediaType = MediaType.APPLICATION_JSON_TYPE;


    private MediaType getMediaTypeFromString(String mimeType) {
        String[] m = mimeType.split("/");
        return new MediaType(m[0], m[1]);
    }


    @JsonProperty("method")
    public Request<T> setMethod(RequestMethod method) {
        this.method = method;
        return this;
    }


    public RequestMethod getMethod() {
        return method;
    }


    public String getPackage() {
        return packageName;
    }

    @JsonProperty("package")
    public Request<T> setPackage(String packageName) {
        this.packageName = packageName;
        return this;
    }


    public String getPathTemplate() {
        return pathTemplate;
    }

    @JsonProperty("pathTemplate")
    public Request<T> setPathtemplate(String pathTemplate) {
        this.pathTemplate = pathTemplate;
        return this;
    }


    public T getRequestBody() {
        return requestBody;
    }

    @JsonProperty("requestBody")
    public Request<T> setRequestbody(T requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public MediaType getConsumedMediaType() {
        return consumedMediaType;
    }

    @JsonProperty("consumes")
    public Request<T> setConsumes(String consumedMediaType) {
        this.consumedMediaType = getMediaTypeFromString(consumedMediaType);
        return this;
    }


    public MediaType getProducedMediaType() {
        return producedMediaType;
    }

    @JsonProperty("produces")
    public Request<T> setProduces(String producedMediaType) {
        this.producedMediaType = getMediaTypeFromString(producedMediaType);
        return this;
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    @JsonProperty("path")
    public Request<T> setPath(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
        return this;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    @JsonProperty("querystring")
    public Request<T> setQuerystring(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    @JsonProperty("header")
    public Request<T> setHeader(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    @Override
    public String toString() {
        return "Request{" +
                "packageName='" + packageName + '\'' +
                ", method=" + method +
                ", pathTemplate='" + pathTemplate + '\'' +
                ", requestBody=" + requestBody +
                ", pathParameters=" + pathParameters +
                ", queryParams=" + queryParams +
                ", requestHeaders=" + requestHeaders +
                ", consumedMediaType=" + consumedMediaType +
                ", producedMediaType=" + producedMediaType +
                '}';
    }
}
