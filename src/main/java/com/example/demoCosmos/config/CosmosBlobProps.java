package com.example.demoCosmos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("azure.myblob")
public class CosmosBlobProps {

    @Value("${azure.myblob.connectionstring}")
    private String connectionstring;

    @Value("${azure.myblob.container}")
    private String container;

    public String getConnectionstring() {
        return connectionstring;
    }

    public void setConnectionstring(String connectionstring) {
        this.connectionstring = connectionstring;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }
}
