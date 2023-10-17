package com.heroku.mercadona.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.heroku.mercadona.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class StorageServiceImpl implements StorageService {
    private final AmazonS3 s3;

    public StorageServiceImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String saveFile(MultipartFile file, String fileName) {
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                File file1 = convertMultiPartToFile(file);
                PutObjectResult putObjectResult = s3.putObject(System.getenv("AWS_S3_BUCKET"), fileName, file1);
                try {
                    boolean result = Files.deleteIfExists(file1.toPath());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return putObjectResult.getContentMd5();
            } catch (IOException e) {
                if (++count == maxTries) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String deleteFile(String fileName) {
        s3.deleteObject(System.getenv("AWS_S3_BUCKET"), fileName);
        return "File deleted";
    }

    @Override
    public String fileNameGen(MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename();
    }

    @Override
    public String urlToFileName(String url) {
        return url.substring(48);
    }

    @Override
    public String urlGen(String fileName) {
        return "https://mercadona-s3.s3.eu-west-3.amazonaws.com/" + fileName;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
