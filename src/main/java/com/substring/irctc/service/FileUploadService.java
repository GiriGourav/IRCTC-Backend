package com.substring.irctc.service;

import com.substring.irctc.entity.ImageMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
   ImageMetadata upload(MultipartFile file) throws IOException;
}
