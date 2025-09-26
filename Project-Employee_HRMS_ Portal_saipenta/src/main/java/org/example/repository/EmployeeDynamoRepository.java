package org.example.repository;

import org.example.entity.Employee;
import org.example.util.DynamoDBConfig;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

public class EmployeeDynamoRepository {
    
    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Employees";
    
    public EmployeeDynamoRepository() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(java.net.URI.create(DynamoDBConfig.DYNAMODB_ENDPOINT))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create("dummy", "dummy"))
                .build();
    }
    
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID().toString());
        }
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(employee.getId()).build());
        item.put("name", AttributeValue.builder().s(employee.getName()).build());
        item.put("email", AttributeValue.builder().s(employee.getEmail()).build());
        item.put("departmentId", AttributeValue.builder().s(employee.getDepartmentId()).build());
        item.put("salary", AttributeValue.builder().n(employee.getSalary().toString()).build());
        item.put("joinDate", AttributeValue.builder().s(employee.getJoinDate().toString()).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();
        
        dynamoDbClient.putItem(request);
        return employee;
    }
    
    public Employee findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());
        
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();
        
        GetItemResponse response = dynamoDbClient.getItem(request);
        
        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            Employee emp = new Employee();
            emp.setId(item.get("id").s());
            emp.setName(item.get("name").s());
            emp.setEmail(item.get("email").s());
            emp.setDepartmentId(item.get("departmentId").s());
            emp.setSalary(new java.math.BigDecimal(item.get("salary").n()));
            emp.setJoinDate(java.time.LocalDate.parse(item.get("joinDate").s()));
            return emp;
        }
        return null;
    }
    
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();
        
        ScanResponse response = dynamoDbClient.scan(scanRequest);
        
        for (Map<String, AttributeValue> item : response.items()) {
            Employee emp = new Employee();
            emp.setId(item.get("id").s());
            emp.setName(item.get("name").s());
            emp.setEmail(item.get("email").s());
            emp.setDepartmentId(item.get("departmentId").s());
            emp.setSalary(new java.math.BigDecimal(item.get("salary").n()));
            emp.setJoinDate(java.time.LocalDate.parse(item.get("joinDate").s()));
            employees.add(emp);
        }
        
        return employees;
    }
    
    public void deleteById(String id) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();
        
        dynamoDbClient.deleteItem(request);
    }
}