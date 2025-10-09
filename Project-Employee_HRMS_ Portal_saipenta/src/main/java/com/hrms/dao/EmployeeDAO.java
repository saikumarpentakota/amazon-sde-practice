package com.hrms.dao;

import com.hrms.model.Employee;
import com.hrms.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public boolean save(Employee employee) {
        String sql = "INSERT INTO employees (emp_code, first_name, last_name, email, phone, hire_date, dept_id, position, salary, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, employee.getEmpCode());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());
            stmt.setDate(6, employee.getHireDate());
            stmt.setInt(7, employee.getDeptId());
            stmt.setString(8, employee.getPosition());
            stmt.setBigDecimal(9, employee.getSalary());
            stmt.setString(10, employee.getStatus());
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    employee.setEmpId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Employee findById(int empId) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY emp_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    public boolean update(Employee employee) {
        String sql = "UPDATE employees SET emp_code=?, first_name=?, last_name=?, email=?, phone=?, hire_date=?, dept_id=?, position=?, salary=?, status=? WHERE emp_id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employee.getEmpCode());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());
            stmt.setDate(6, employee.getHireDate());
            stmt.setInt(7, employee.getDeptId());
            stmt.setString(8, employee.getPosition());
            stmt.setBigDecimal(9, employee.getSalary());
            stmt.setString(10, employee.getStatus());
            stmt.setInt(11, employee.getEmpId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int empId) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, empId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setEmpCode(rs.getString("emp_code"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setEmail(rs.getString("email"));
        employee.setPhone(rs.getString("phone"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setDeptId(rs.getInt("dept_id"));
        employee.setPosition(rs.getString("position"));
        employee.setSalary(rs.getBigDecimal("salary"));
        employee.setStatus(rs.getString("status"));
        return employee;
    }
}