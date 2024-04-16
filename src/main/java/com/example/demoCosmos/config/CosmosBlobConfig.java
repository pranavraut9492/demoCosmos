package com.example.demoCosmos.config;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CosmosBlobProps.class)
@PropertySource("classpath:application.properties")
public class CosmosBlobConfig {

    @Autowired
    CosmosBlobProps cosmosBlobProps;

    public BlobContainerClient containerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(cosmosBlobProps.getConnectionstring()).buildClient();
        BlobContainerClient container = serviceClient.getBlobContainerClient(cosmosBlobProps.getContainer());
        return container;
    }
}
