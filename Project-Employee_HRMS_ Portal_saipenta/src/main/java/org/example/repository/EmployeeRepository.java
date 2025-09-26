package org.example.repository;

import org.example.entity.Employee;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    
    public Employee save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, email, department_id, salary, join_date) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setLong(3, Long.parseLong(employee.getDepartmentId()));
            stmt.setBigDecimal(4, employee.getSalary());
            stmt.setDate(5, Date.valueOf(employee.getJoinDate()));
            
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                employee.setId(String.valueOf(keys.getLong(1)));
            }
        }
        return employee;
    }
    
    public Employee findById(String id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, Long.parseLong(id));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Employee emp = new Employee();
                emp.setId(String.valueOf(rs.getLong("id")));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartmentId(String.valueOf(rs.getLong("department_id")));
                emp.setSalary(rs.getBigDecimal("salary"));
                emp.setJoinDate(rs.getDate("join_date").toLocalDate());
                return emp;
            }
        }
        return null;
    }
    
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(String.valueOf(rs.getLong("id")));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartmentId(String.valueOf(rs.getLong("department_id")));
                emp.setSalary(rs.getBigDecimal("salary"));
                emp.setJoinDate(rs.getDate("join_date").toLocalDate());
                employees.add(emp);
            }
        }
        return employees;
    }
}