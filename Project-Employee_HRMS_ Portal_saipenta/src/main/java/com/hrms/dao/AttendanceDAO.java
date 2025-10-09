package com.hrms.dao;

import com.hrms.model.Attendance;
import com.hrms.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    
    public boolean markAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendance (emp_id, attendance_date, status, check_in_time, check_out_time, remarks) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE status=?, check_in_time=?, check_out_time=?, remarks=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, attendance.getEmpId());
            stmt.setDate(2, attendance.getAttendanceDate());
            stmt.setString(3, attendance.getStatus());
            stmt.setTime(4, attendance.getCheckInTime());
            stmt.setTime(5, attendance.getCheckOutTime());
            stmt.setString(6, attendance.getRemarks());
            stmt.setString(7, attendance.getStatus());
            stmt.setTime(8, attendance.getCheckInTime());
            stmt.setTime(9, attendance.getCheckOutTime());
            stmt.setString(10, attendance.getRemarks());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Attendance> findByEmpId(int empId) {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT a.*, e.first_name, e.last_name FROM attendance a JOIN employees e ON a.emp_id = e.emp_id WHERE a.emp_id = ? ORDER BY a.attendance_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                attendances.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }
    
    public List<Attendance> findByDate(Date date) {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT a.*, e.first_name, e.last_name FROM attendance a JOIN employees e ON a.emp_id = e.emp_id WHERE a.attendance_date = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                attendances.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }
    
    public List<Attendance> getMonthlyReport(int empId, String month) {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE emp_id = ? AND DATE_FORMAT(attendance_date, '%Y-%m') = ? ORDER BY attendance_date";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, empId);
            stmt.setString(2, month);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                attendances.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }
    
    private Attendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(rs.getInt("attendance_id"));
        attendance.setEmpId(rs.getInt("emp_id"));
        attendance.setAttendanceDate(rs.getDate("attendance_date"));
        attendance.setStatus(rs.getString("status"));
        attendance.setCheckInTime(rs.getTime("check_in_time"));
        attendance.setCheckOutTime(rs.getTime("check_out_time"));
        attendance.setRemarks(rs.getString("remarks"));
        attendance.setCreatedAt(rs.getTimestamp("created_at"));
        

        try {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            if (firstName != null && lastName != null) {
                attendance.setEmployeeName(firstName + " " + lastName);
            }
        } catch (SQLException e) {

        }
        
        return attendance;
    }
}