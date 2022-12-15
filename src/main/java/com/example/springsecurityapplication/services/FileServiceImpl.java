package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.repositories.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String storagePath;

    private final ImageRepository imageRepository;

    public FileServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        Image file;
        try {
            file = imageRepository.findByFileName(fileName).orElseThrow(FileNotFoundException::new);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            IOUtils.copy(new FileInputStream(storagePath + "\\" + file.getFileName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
