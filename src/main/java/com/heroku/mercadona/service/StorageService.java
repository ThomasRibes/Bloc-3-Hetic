package com.heroku.mercadona.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String saveFile(MultipartFile file, String fileName);
    String deleteFile(String fileName);
    String urlGen(String fileName);
    String fileNameGen(MultipartFile file);
    String urlToFileName(String url);
}