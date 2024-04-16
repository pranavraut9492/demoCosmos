package com.example.demoCosmos.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;

import javax.annotation.Nullable;
import java.util.logging.Logger;

@Configuration
@EnableConfigurationProperties(CosmosDbProps.class)
@EnableCosmosRepositories(basePackages = "com.example.demoCosmos.repo")
@PropertySource("classpath:application.properties")

public class CosmosDbConfig extends AbstractCosmosConfiguration {

    @Autowired
    CosmosDbProps cosmosDbProps;

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        DirectConnectionConfig directConnectionConfig = DirectConnectionConfig.getDefaultConfig();
        return new CosmosClientBuilder()
                .endpoint(cosmosDbProps.getUri())
                .key(cosmosDbProps.getKey())
                .directMode(directConnectionConfig);
    }

    @Override
    protected String getDatabaseName() {
        return "ToDoList";
    }

}
