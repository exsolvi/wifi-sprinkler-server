package se.exsolvi.wifisprinkler.server.util;

import com.amazonaws.auth.*;

public class SampleAWSCredentialProvider extends AWSCredentialsProviderChain {

    public SampleAWSCredentialProvider() {
        super(new ClasspathPropertiesFileCredentialsProvider(), new InstanceProfileCredentialsProvider(),
                new SystemPropertiesCredentialsProvider(), new EnvironmentVariableCredentialsProvider());
    }
}


