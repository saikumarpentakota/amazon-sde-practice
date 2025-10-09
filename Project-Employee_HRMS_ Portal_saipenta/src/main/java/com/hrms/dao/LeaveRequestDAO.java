package com.hrms.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.hrms.model.LeaveRequest;
import com.hrms.util.DynamoDBConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveRequestDAO {
    private static final String TABLE_NAME = "LeaveRequests";
    private AmazonDynamoDB client;
    
    // Fallback mock storage if DynamoDB is not available
    private static final Map<String, LeaveRequest> mockStorage = new HashMap<>();
    private boolean useMockStorage = false;

    public LeaveRequestDAO() {
        try {
            this.client = DynamoDBConfig.getClient();
            // Test connection
            client.describeTable(TABLE_NAME);
        } catch (Exception e) {
            System.out.println("DynamoDB not available, using mock storage");
            this.client = null;
            this.useMockStorage = true;
        }
    }

    public boolean save(LeaveRequest leaveRequest) {
        try {
            if (leaveRequest.getRequestId() == null) {
                leaveRequest.setRequestId("LR" + System.currentTimeMillis());
            }
            leaveRequest.setRequestDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            if (useMockStorage) {
                mockStorage.put(leaveRequest.getRequestId(), leaveRequest);
                return true;
            }
            
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("requestId", new AttributeValue(leaveRequest.getRequestId()));
            item.put("empId", new AttributeValue(leaveRequest.getEmpId()));
            item.put("leaveDate", new AttributeValue(leaveRequest.getLeaveDate()));
            item.put("reason", new AttributeValue(leaveRequest.getReason()));
            item.put("status", new AttributeValue(leaveRequest.getStatus()));
            item.put("requestDate", new AttributeValue(leaveRequest.getRequestDate()));
            
            PutItemRequest request = new PutItemRequest()
                .withTableName(TABLE_NAME)
                .withItem(item);
            
            client.putItem(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LeaveRequest> findByEmpId(String empId) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        
        try {
            if (useMockStorage) {
                for (LeaveRequest request : mockStorage.values()) {
                    if (empId.equals(request.getEmpId())) {
                        leaveRequests.add(request);
                    }
                }
                return leaveRequests;
            }
            
            Map<String, AttributeValue> values = new HashMap<>();
            values.put(":empId", new AttributeValue(empId));
            
            ScanRequest request = new ScanRequest()
                .withTableName(TABLE_NAME)
                .withFilterExpression("empId = :empId")
                .withExpressionAttributeValues(values);
            
            ScanResult result = client.scan(request);
            
            for (Map<String, AttributeValue> item : result.getItems()) {
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setRequestId(item.get("requestId") != null ? item.get("requestId").getS() : null);
                leaveRequest.setEmpId(item.get("empId") != null ? item.get("empId").getS() : null);
                leaveRequest.setLeaveDate(item.get("leaveDate") != null ? item.get("leaveDate").getS() : null);
                leaveRequest.setReason(item.get("reason") != null ? item.get("reason").getS() : null);
                leaveRequest.setStatus(item.get("status") != null ? item.get("status").getS() : null);
                leaveRequest.setRequestDate(item.get("requestDate") != null ? item.get("requestDate").getS() : null);
                leaveRequests.add(leaveRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return leaveRequests;
    }

    public boolean updateStatus(String requestId, String status) {
        try {
            if (useMockStorage) {
                LeaveRequest request = mockStorage.get(requestId);
                if (request != null) {
                    request.setStatus(status);
                    return true;
                }
                return false;
            }
            
            Map<String, AttributeValue> key = new HashMap<>();
            key.put("requestId", new AttributeValue(requestId));
            
            Map<String, String> names = new HashMap<>();
            names.put("#status", "status");
            
            Map<String, AttributeValue> values = new HashMap<>();
            values.put(":status", new AttributeValue(status));
            
            UpdateItemRequest request = new UpdateItemRequest()
                .withTableName(TABLE_NAME)
                .withKey(key)
                .withUpdateExpression("SET #status = :status")
                .withExpressionAttributeNames(names)
                .withExpressionAttributeValues(values);
            
            client.updateItem(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public LeaveRequest findById(String requestId) {
        try {
            if (useMockStorage) {
                return mockStorage.get(requestId);
            }
            
            Map<String, AttributeValue> key = new HashMap<>();
            key.put("requestId", new AttributeValue(requestId));
            
            GetItemRequest request = new GetItemRequest()
                .withTableName(TABLE_NAME)
                .withKey(key);
            
            GetItemResult result = client.getItem(request);
            Map<String, AttributeValue> item = result.getItem();
            
            if (item != null && !item.isEmpty()) {
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setRequestId(item.get("requestId") != null ? item.get("requestId").getS() : null);
                leaveRequest.setEmpId(item.get("empId") != null ? item.get("empId").getS() : null);
                leaveRequest.setLeaveDate(item.get("leaveDate") != null ? item.get("leaveDate").getS() : null);
                leaveRequest.setReason(item.get("reason") != null ? item.get("reason").getS() : null);
                leaveRequest.setStatus(item.get("status") != null ? item.get("status").getS() : null);
                leaveRequest.setRequestDate(item.get("requestDate") != null ? item.get("requestDate").getS() : null);
                return leaveRequest;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}