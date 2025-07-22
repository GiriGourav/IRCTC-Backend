package com.substring.irctc.service.impl;

import com.substring.irctc.entity.ImageMetadata;
import com.substring.irctc.helper.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService implements com.substring.irctc.service.FileUploadService {

    @Value("${file.upload.folder}")
    private String folder;

    @Override
    public ImageMetadata upload(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        System.out.println(originalFilename);
        InputStream inputStream=file.getInputStream();
//        create folder if not exist
        if(!Files.exists(Paths.get(folder)))
        {
            Files.createDirectories(Paths.get(folder));
        }

        String fileNameWithPath= Helper.getFileName(folder,file.getOriginalFilename());
//      Lets upload the file
        Files.copy(file.getInputStream(),Paths.get(fileNameWithPath), StandardCopyOption.REPLACE_EXISTING);
        ImageMetadata metadata=new ImageMetadata();
        metadata.setFileId(UUID.randomUUID().toString());
        metadata.setFileName(fileNameWithPath);
        metadata.setFileSize(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }
}
