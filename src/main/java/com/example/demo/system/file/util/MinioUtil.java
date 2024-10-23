package com.example.demo.system.file.util;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Component
public class MinioUtil {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.url}")
    private String url;


    public String uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "-" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder()
                    .object(fileName)
                    .bucket(bucketName).stream(inputStream, file.getSize(), -1).build());
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败");
        }
        return url + bucketName + "/" + fileName;
    }
}