package com.example.bideew.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;



public class FileDownloadUtil {
    private Path foundFile;
     
    public Resource getFileAsByte(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");
         
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });
 
        if (foundFile == null) {
            // File not found
            return null;
        }

        // return Files.readAllBytes(foundFile);
        return new UrlResource(foundFile.toUri());
    }
    
    
}
