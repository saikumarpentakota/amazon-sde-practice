package org.example.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DynamoDBTableCreator {
    
    private final DynamoDbClient dynamoDbClient;
    
    public DynamoDBTableCreator() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create(DynamoDBConfig.DYNAMODB_ENDPOINT))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create("dummy", "dummy"))
                .build();
    }
    
    public void createTables() {
        createEmployeesTable();
        createPayrollTable();
        createLeaveRequestsTable();
        createDepartmentTable();
    }
    
    public void createDepartmentTable() {
        try {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName("Departments")
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
            dynamoDbClient.createTable(request);
            System.out.println("Department table created!");
        } catch (ResourceInUseException e) {
            System.out.println("Department table already exists");
        }
    }
    
    private void createEmployeesTable() {
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
            dynamoDbClient.createTable(request);
        } catch (ResourceInUseException e) {

        }
    }
    
    private void createPayrollTable() {
        try {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName("Payroll")
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
            dynamoDbClient.createTable(request);
        } catch (ResourceInUseException e) {

        }
    }
    
    private void createLeaveRequestsTable() {
        try {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName("LeaveRequests")
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
            dynamoDbClient.createTable(request);
        } catch (ResourceInUseException e) {

        }
    }
    
    public static void main(String[] args) {
        System.out.println("Creating DynamoDB tables...");
        DynamoDBTableCreator creator = new DynamoDBTableCreator();
        creator.createTables();
        System.out.println("Tables created successfully!");
    }
}