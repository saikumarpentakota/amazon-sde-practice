package org.example.util;

import org.example.entity.Employee;
import org.example.repository.EmployeeDynamoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddSampleEmployees {
    
    public static void main(String[] args) {
        System.out.println("Adding sample employees to DynamoDB...");
        
        EmployeeDynamoRepository repo = new EmployeeDynamoRepository();
        
        try {
            // Add sample employees
            Employee emp1 = new Employee();
            emp1.setName("John Doe");
            emp1.setEmail("john.doe@company.com");
            emp1.setDepartmentId("1");
            emp1.setSalary(new BigDecimal("75000"));
            emp1.setJoinDate(LocalDate.of(2024, 1, 15));
            repo.save(emp1);
            
            Employee emp2 = new Employee();
            emp2.setName("Jane Smith");
            emp2.setEmail("jane.smith@company.com");
            emp2.setDepartmentId("2");
            emp2.setSalary(new BigDecimal("65000"));
            emp2.setJoinDate(LocalDate.of(2024, 2, 1));
            repo.save(emp2);
            
            Employee emp3 = new Employee();
            emp3.setName("Mike Johnson");
            emp3.setEmail("mike.johnson@company.com");
            emp3.setDepartmentId("1");
            emp3.setSalary(new BigDecimal("80000"));
            emp3.setJoinDate(LocalDate.of(2024, 1, 10));
            repo.save(emp3);
            
            System.out.println("Sample employees added successfully!");
            
            // Verify by listing all employees
            System.out.println("Current employees in DynamoDB:");
            repo.findAll().forEach(emp -> 
                System.out.println("- " + emp.getName() + " (" + emp.getEmail() + ") - $" + emp.getSalary())
            );
            
        } catch (Exception e) {
            System.err.println("Error adding sample employees: " + e.getMessage());
            e.printStackTrace();
        }
    }
}