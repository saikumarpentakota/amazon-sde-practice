package org.example.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DynamoDBTester {
    
    public static void main(String[] args) {
        System.out.println("Testing DynamoDB Local connection...");
        
        try {
            DynamoDbClient client = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create("http://localhost:8000"))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create("dummy", "dummy"))
                .build();
            
            // Test connection by listing tables
            ListTablesResponse response = client.listTables();
            System.out.println("Connected to DynamoDB Local successfully!");
            System.out.println("Existing tables: " + response.tableNames());
            
            // Check if Employees table exists
            if (!response.tableNames().contains("Employees")) {
                System.out.println("Employees table not found. Creating...");
                createEmployeesTable(client);
            } else {
                System.out.println("Employees table exists");
            }
            
        } catch (Exception e) {
            System.err.println("Failed to connect to DynamoDB Local: " + e.getMessage());
            System.err.println("Make sure DynamoDB Local is running on port 8000");
        }
    }
    
    private static void createEmployeesTable(DynamoDbClient client) {
        try {
            CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Employees")
                .keySchema(KeySchemaElement.builder()
                    .attributeName("id")
                    .keyType(KeyType.HASH)
                    .build())
                .attributeDefinitions(AttributeDefinition.builder()
                    .attributeName("id")
                    .attributeType(ScalarAttributeType.S)
                    .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
            
            client.createTable(request);
            System.out.println("Employees table created successfully!");
        } catch (Exception e) {
            System.err.println("Failed to create Employees table: " + e.getMessage());
        }
    }
}