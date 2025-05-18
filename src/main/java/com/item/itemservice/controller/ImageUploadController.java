package com.item.itemservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class ImageUploadController {

    private final S3Client s3;
    private final String bucket;
    private final String region;

    public ImageUploadController(
            S3Client s3,
            @Value("${aws.s3.bucket}") String bucket,
            @Value("${aws.s3.region}") String region
    ) {
        this.s3 = s3;
        this.bucket = bucket;
        this.region = region;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
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

        String url = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
        Map<String, String> result = new HashMap<>();
        result.put("url", url);
        return result;
    }
}

@Configuration
class S3ClientConfig {
    @Bean
    public S3Client s3Client(
            @Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretAccessKey}") String secretAccessKey,
            @Value("${aws.s3.region}") String region
    ) {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)
                ))
                .build();
    }
}
