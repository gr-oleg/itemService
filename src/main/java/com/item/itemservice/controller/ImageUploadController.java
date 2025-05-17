package com.item.itemservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class ImageUploadController {

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;

    @Value("${aws.s3.region}")
    private String region;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // Ініціалізація S3 клієнта
        S3Client s3 = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)
                )).build();

        String key = UUID.randomUUID() + "-" + file.getOriginalFilename();
        s3.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(file.getContentType())
                        .acl("public-read")
                        .build(),
                software.amazon.awssdk.core.sync.RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );

        // Формуємо публічний URL
        String url = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
        Map<String, String> result = new HashMap<>();
        result.put("url", url);
        return result;
    }
}
