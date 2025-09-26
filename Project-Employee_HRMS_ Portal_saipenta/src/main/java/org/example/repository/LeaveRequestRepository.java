package org.example.repository;

import org.example.entity.LeaveRequest;
import org.example.util.DynamoDBConfig;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LeaveRequestRepository {
    
    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "LeaveRequests";
    
    public LeaveRequestRepository() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create(DynamoDBConfig.DYNAMODB_ENDPOINT))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create("dummy", "dummy"))
                .build();
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists() {
        try {
            DescribeTableRequest describeRequest = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();
            dynamoDbClient.describeTable(describeRequest);
        } catch (ResourceNotFoundException e) {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName(tableName)
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
        }
    }
    
    public LeaveRequest save(LeaveRequest leaveRequest) {
        if (leaveRequest.getId() == null) {
            leaveRequest.setId(UUID.randomUUID().toString());
        }
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(leaveRequest.getId()).build());
        item.put("employeeId", AttributeValue.builder().n(leaveRequest.getEmployeeId().toString()).build());
        item.put("leaveType", AttributeValue.builder().s(leaveRequest.getLeaveType()).build());
        item.put("startDate", AttributeValue.builder().s(leaveRequest.getStartDate().toString()).build());
        item.put("endDate", AttributeValue.builder().s(leaveRequest.getEndDate().toString()).build());
        item.put("reason", AttributeValue.builder().s(leaveRequest.getReason()).build());
        item.put("status", AttributeValue.builder().s(leaveRequest.getStatus()).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();
        
        dynamoDbClient.putItem(request);
        return leaveRequest;
    }
    
    public LeaveRequest findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());
        
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();
        
        GetItemResponse response = dynamoDbClient.getItem(request);
        
        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            LeaveRequest leave = new LeaveRequest();
            leave.setId(item.get("id").s());
            leave.setEmployeeId(Long.parseLong(item.get("employeeId").n()));
            leave.setLeaveType(item.get("leaveType").s());
            leave.setReason(item.get("reason").s());
            leave.setStatus(item.get("status").s());
            return leave;
        }
        return null;
    }
    
    public java.util.List<LeaveRequest> findByEmployeeId(Long employeeId) {
        java.util.List<LeaveRequest> results = new java.util.ArrayList<>();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .filterExpression("employeeId = :empId")
                .expressionAttributeValues(java.util.Map.of(
                        ":empId", AttributeValue.builder().n(employeeId.toString()).build()))
                .build();
        
        ScanResponse response = dynamoDbClient.scan(scanRequest);
        
        for (Map<String, AttributeValue> item : response.items()) {
            LeaveRequest leave = new LeaveRequest();
            leave.setId(item.get("id").s());
            leave.setEmployeeId(Long.parseLong(item.get("employeeId").n()));
            leave.setLeaveType(item.get("leaveType").s());
            leave.setReason(item.get("reason").s());
            leave.setStatus(item.get("status").s());
            results.add(leave);
        }
        
        return results;
    }
    
    public LeaveRequest update(LeaveRequest leaveRequest) {
        Map<String, AttributeValueUpdate> updates = new HashMap<>();
        updates.put("leaveType", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(leaveRequest.getLeaveType()).build())
                .action(AttributeAction.PUT).build());
        updates.put("startDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(leaveRequest.getStartDate().toString()).build())
                .action(AttributeAction.PUT).build());
        updates.put("endDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(leaveRequest.getEndDate().toString()).build())
                .action(AttributeAction.PUT).build());
        updates.put("reason", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(leaveRequest.getReason()).build())
                .action(AttributeAction.PUT).build());
        updates.put("status", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(leaveRequest.getStatus()).build())
                .action(AttributeAction.PUT).build());
        
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("id", AttributeValue.builder().s(leaveRequest.getId()).build()))
                .attributeUpdates(updates)
                .build();
        
        dynamoDbClient.updateItem(request);
        return leaveRequest;
    }
    
    public void deleteById(String id) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();
        
        dynamoDbClient.deleteItem(request);
    }
    
    public java.util.List<LeaveRequest> findAll() {
        java.util.List<LeaveRequest> results = new java.util.ArrayList<>();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();
        
        ScanResponse response = dynamoDbClient.scan(scanRequest);
        
        for (Map<String, AttributeValue> item : response.items()) {
            LeaveRequest leave = new LeaveRequest();
            leave.setId(item.get("id").s());
            leave.setEmployeeId(Long.parseLong(item.get("employeeId").n()));
            leave.setLeaveType(item.get("leaveType").s());
            leave.setReason(item.get("reason").s());
            leave.setStatus(item.get("status").s());
            results.add(leave);
        }
        
        return results;
    }
}