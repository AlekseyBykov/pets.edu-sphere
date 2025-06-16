package dev.abykov.pets.edusphere.content.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class S3Config {

    // S3-compatible endpoint (e.g., http://localhost:9000 for MinIO)
    @Value("${content.s3.endpoint}")
    private String endpoint;

    // AWS region (required by the SDK)
    @Value("${content.s3.region}")
    private String region;

    // MinIO or AWS access key
    @Value("${content.s3.access-key}")
    private String accessKey;

    // MinIO or AWS secret key
    @Value("${content.s3.secret-key}")
    private String secretKey;

    /**
     * Creates and configures the S3 client
     */
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .serviceConfiguration(
                        S3Configuration.builder()
                                .pathStyleAccessEnabled(true)
                                .build()
                )
                .build();
    }
}
