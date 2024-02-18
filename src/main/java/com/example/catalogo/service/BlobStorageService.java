package com.example.catalogo.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import com.example.catalogo.config.BlobStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlobStorageService {
    @Autowired
    private BlobStorageConfig blobStorageConfig;

    public void uploadBlob(MultipartFile file, String filename) throws IOException {
        BlobClient blobClient = blobStorageConfig.blobContainerClient().getBlobClient(filename);
        blobClient.upload(file.getInputStream(), file.getSize());
    }
    public List<String> listBlobs(){
        return blobStorageConfig.blobContainerClient().listBlobs()
                .stream()
                .map(BlobItem::getName)
                .toList();
    }
    public void deleteBlob(String blobName){
        blobStorageConfig.blobContainerClient().getBlobClient(blobName)
                .delete();
    }
    public BinaryData downloadBlob(String blobName) {
        BlobClient blobClient = blobStorageConfig.blobContainerClient().getBlobClient(blobName);
        return blobClient.downloadContent();
    }
}
