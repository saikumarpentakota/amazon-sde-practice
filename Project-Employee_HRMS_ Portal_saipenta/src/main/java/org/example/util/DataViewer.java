package org.example.util;

import org.example.entity.Employee;
import org.example.repository.EmployeeDynamoRepository;

import java.util.List;

public class DataViewer {
    
    public static void main(String[] args) {
        System.out.println("Viewing DynamoDB data...");
        
        EmployeeDynamoRepository empRepo = new EmployeeDynamoRepository();
        List<Employee> employees = empRepo.findAll();
        
        System.out.println("Total employees: " + employees.size());
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Email: " + emp.getEmail());
        }
    }
}