package com.example.bideew.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.ServletContext;

@Service
@Transactional
public class UploadPath {
    @Autowired
    ServletContext context;
    
    public File getFilePath(String modifiedFileName, String path) throws IOException {
        boolean exist = new File(context.getRealPath("/"+path+"/")).exists();
        if (!exist) {
            System.out.println("Creating the directory");
            new File(context.getRealPath("/"+path+"/")).mkdir();
            Path uploadPath = Paths.get("/"+path+"/");
            Files.createDirectories(uploadPath);
            System.out.println("Getting file path " + uploadPath);
        }
        String modifiedFilePath = context.getRealPath("/"+path+"/"+File.separator+modifiedFileName);
        File file = new File(modifiedFilePath);
        return file;
    }
}