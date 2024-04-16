package com.example.demoCosmos.repo;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import com.example.demoCosmos.config.CosmosBlobConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AudioBlobRepo {

    @Autowired
    CosmosBlobConfig cosmosBlobConfig;


    public List<String> listFiles() {
        BlobContainerClient container = cosmosBlobConfig.containerClient();
        List<String> list = new ArrayList<>();
        for (BlobItem blobItem : container.listBlobs()) {
            list.add(blobItem.getName());
        }
        return list;
    }

    public ByteArrayOutputStream downloadFile(String blobitem) {
        BlobContainerClient containerClient = cosmosBlobConfig.containerClient();
        BlobClient blobClient = containerClient.getBlobClient(blobitem);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        blobClient.download(os);
        return os;
    }

    public String storeFile(String filename, InputStream content, long length) {
        BlobClient client = cosmosBlobConfig.containerClient().getBlobClient(filename);
        if (client.exists()) {
        } else {
            client.upload(content, length);
        }
        return "File uploaded with success!";
    }
}
