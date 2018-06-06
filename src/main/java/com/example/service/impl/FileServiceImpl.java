package com.example.service.impl;

import com.example.service.api.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final Path uploadDirectory;

    public FileServiceImpl(@Value("${upload.files.dir}") Path uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

    @Override
    public String saveFile(MultipartFile file) {
        String filename = UUID.randomUUID().toString() + StringUtils.cleanPath(file.getOriginalFilename());

        Path targetLocation = uploadDirectory.resolve(filename);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("File can't be saved", e);
        }

        return filename;
    }
}
