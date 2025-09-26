package org.example.repository;

import org.example.entity.Payroll;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollRepository {
    
    public Payroll save(Payroll payroll) throws SQLException {
        String sql = "INSERT INTO payroll (employee_id, month, basic_salary, overtime, total) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, Long.parseLong(payroll.getEmployeeId()));
            stmt.setString(2, payroll.getMonth());
            stmt.setBigDecimal(3, payroll.getBasicSalary());
            stmt.setBigDecimal(4, payroll.getOvertime());
            stmt.setBigDecimal(5, payroll.getTotal());
            
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                payroll.setId(String.valueOf(keys.getLong(1)));
            }
        }
        return payroll;
    }
    
    public List<Payroll> findAll() throws SQLException {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM payroll";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Payroll payroll = new Payroll();
                payroll.setId(String.valueOf(rs.getLong("id")));
                payroll.setEmployeeId(String.valueOf(rs.getLong("employee_id")));
                payroll.setMonth(rs.getString("month"));
                payroll.setBasicSalary(rs.getBigDecimal("basic_salary"));
                payroll.setOvertime(rs.getBigDecimal("overtime"));
                payroll.setTotal(rs.getBigDecimal("total"));
                payrolls.add(payroll);
            }
        }
        return payrolls;
    }
}