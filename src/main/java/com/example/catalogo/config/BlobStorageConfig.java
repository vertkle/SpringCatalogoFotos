package com.example.catalogo.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlobStorageConfig {
    @Value("${azure_connection}")
    private String connectionString;
    @Value("${azure_container}")
    private String container;
    @Bean
    public BlobContainerClient blobContainerClient(){
        return new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(container)
                .buildClient();
    }
}
