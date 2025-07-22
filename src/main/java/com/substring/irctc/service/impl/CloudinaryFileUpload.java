package com.substring.irctc.service.impl;

import com.substring.irctc.entity.ImageMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Primary
public class CloudinaryFileUpload extends FileUploadService {

    @Override
    public ImageMetadata upload(MultipartFile file) throws IOException{
//        Logic to upload file to cloudinary
        return null;
    }
}
