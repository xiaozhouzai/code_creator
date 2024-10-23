package com.example.demo.system.file.util;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder().endpoint(endpoint).credentials(accessKey,secretKey).build();
        } catch (Exception e) {
            throw new RuntimeException("创建minio客户端异常");
        }
    }
}
