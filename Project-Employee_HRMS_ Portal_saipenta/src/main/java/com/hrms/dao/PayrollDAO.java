package com.hrms.dao;

import com.hrms.model.Payroll;
import com.hrms.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {
    
    public boolean save(Payroll payroll) {
        String sql = "INSERT INTO payroll (emp_id, pay_period, basic_salary, allowances, deductions, gross_salary, tax_deduction, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, payroll.getEmpId());
            stmt.setString(2, payroll.getPayPeriod());
            stmt.setBigDecimal(3, payroll.getBasicSalary());
            stmt.setBigDecimal(4, payroll.getAllowances());
            stmt.setBigDecimal(5, payroll.getDeductions());
            stmt.setBigDecimal(6, payroll.getGrossSalary());
            stmt.setBigDecimal(7, payroll.getTaxDeduction());
            stmt.setBigDecimal(8, payroll.getNetSalary());
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    payroll.setPayrollId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Payroll> findByEmpId(int empId) {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM payroll WHERE emp_id = ? ORDER BY pay_period DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                payrolls.add(mapResultSetToPayroll(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
    
    public List<Payroll> findByPeriod(String payPeriod) {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT p.*, e.first_name, e.last_name FROM payroll p JOIN employees e ON p.emp_id = e.emp_id WHERE p.pay_period = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, payPeriod);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                payrolls.add(mapResultSetToPayroll(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
    
    public Payroll findByEmpIdAndPeriod(int empId, String payPeriod) {
        String sql = "SELECT * FROM payroll WHERE emp_id = ? AND pay_period = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, empId);
            stmt.setString(2, payPeriod);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToPayroll(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Payroll> findAll() {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT p.*, e.first_name, e.last_name FROM payroll p JOIN employees e ON p.emp_id = e.emp_id ORDER BY p.processed_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                payrolls.add(mapResultSetToPayroll(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
    
    private Payroll mapResultSetToPayroll(ResultSet rs) throws SQLException {
        Payroll payroll = new Payroll();
        payroll.setPayrollId(rs.getInt("payroll_id"));
        payroll.setEmpId(rs.getInt("emp_id"));
        payroll.setPayPeriod(rs.getString("pay_period"));
        payroll.setBasicSalary(rs.getBigDecimal("basic_salary"));
        payroll.setAllowances(rs.getBigDecimal("allowances"));
        payroll.setDeductions(rs.getBigDecimal("deductions"));
        payroll.setGrossSalary(rs.getBigDecimal("gross_salary"));
        payroll.setTaxDeduction(rs.getBigDecimal("tax_deduction"));
        payroll.setNetSalary(rs.getBigDecimal("net_salary"));
        payroll.setProcessedDate(rs.getTimestamp("processed_date"));
        
        // Set employee name if available
        try {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            if (firstName != null && lastName != null) {
                payroll.setEmployeeName(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            // Column doesn't exist, ignore
        }
        
        return payroll;
    }
}