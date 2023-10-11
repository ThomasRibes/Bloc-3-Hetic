
package com.heroku.mercadona.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class S3Config {
    
    // For dev local :
//    @Value("${cloud.aws.accessKey}")
//    private String accessKey;
//    @Value("${cloud.aws.secretKey}")
//    private String secretKey;
//    @Value("${cloud.aws.region.static}")
//    private String region;
    
    @Bean
    public AmazonS3 s3(){
        // For dev local :
//        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
//        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withRegion(region).build();

        // For prod Heroku :
        AWSCredentials awsCredentials = new BasicAWSCredentials(System.getenv("AWS_S3_KEY"), System.getenv("AWS_S3_SECRET"));
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(System.getenv("AWS_S3_REGION")).build();
    }
}