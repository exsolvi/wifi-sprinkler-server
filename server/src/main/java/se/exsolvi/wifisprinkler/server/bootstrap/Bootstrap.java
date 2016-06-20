package se.exsolvi.wifisprinkler.server.bootstrap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Bootstrap {

    public static final String configUrl = "https://l6bv2wlqwg.execute-api.us-east-1.amazonaws.com/prod/config";

    public String handleRequest(Context context) {

        LambdaLogger logger = context.getLogger();

        try {
            logger.log("Sent bootstrap configuration");
            return "{ \"configurl\" : \"" + configUrl + "}";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}