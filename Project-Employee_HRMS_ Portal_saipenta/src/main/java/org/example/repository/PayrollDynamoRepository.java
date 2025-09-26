package org.example.repository;

import org.example.entity.Payroll;
import org.example.util.DynamoDBConfig;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

public class PayrollDynamoRepository {
    
    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Payroll";
    
    public PayrollDynamoRepository() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create(DynamoDBConfig.DYNAMODB_ENDPOINT))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create("dummy", "dummy"))
                .build();
    }
    
    public Payroll save(Payroll payroll) {
        if (payroll.getId() == null) {
            payroll.setId(UUID.randomUUID().toString());
        }
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(payroll.getId()).build());
        item.put("employeeId", AttributeValue.builder().s(payroll.getEmployeeId()).build());
        item.put("month", AttributeValue.builder().s(payroll.getMonth()).build());
        item.put("basicSalary", AttributeValue.builder().n(payroll.getBasicSalary().toString()).build());
        item.put("overtime", AttributeValue.builder().n(payroll.getOvertime().toString()).build());
        item.put("total", AttributeValue.builder().n(payroll.getTotal().toString()).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();
        
        dynamoDbClient.putItem(request);
        return payroll;
    }
    
    public List<Payroll> findByEmployeeId(String employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .filterExpression("employeeId = :empId")
                .expressionAttributeValues(Map.of(
                        ":empId", AttributeValue.builder().s(employeeId).build()))
                .build();
        
        ScanResponse response = dynamoDbClient.scan(scanRequest);
        
        for (Map<String, AttributeValue> item : response.items()) {
            Payroll payroll = new Payroll();
            payroll.setId(item.get("id").s());
            payroll.setEmployeeId(item.get("employeeId").s());
            payroll.setMonth(item.get("month").s());
            payroll.setBasicSalary(new java.math.BigDecimal(item.get("basicSalary").n()));
            payroll.setOvertime(new java.math.BigDecimal(item.get("overtime").n()));
            payroll.setTotal(new java.math.BigDecimal(item.get("total").n()));
            payrolls.add(payroll);
        }
        
        return payrolls;
    }
}