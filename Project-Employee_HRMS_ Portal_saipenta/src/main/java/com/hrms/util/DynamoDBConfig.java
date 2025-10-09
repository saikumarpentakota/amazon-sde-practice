package com.hrms.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConfig {
    private static final String ACCESS_KEY = "fakeMyKeyId";
    private static final String SECRET_KEY = "fakeSecretAccessKey";
    private static final String REGION = "us-east-1";
    private static final String ENDPOINT = "http://localhost:8000"; // Local DynamoDB endpoint
    
    private static AmazonDynamoDB client;

    public static AmazonDynamoDB getClient() {
        if (client == null) {
            BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            
            client = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION))
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();
        }
        return client;
    }


    
    public static boolean isConnected() {
        try {
            getClient().listTables();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}