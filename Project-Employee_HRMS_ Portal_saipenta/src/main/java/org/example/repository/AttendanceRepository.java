package org.example.repository;

import org.example.entity.Attendance;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository {
    
    public Attendance save(Attendance attendance) throws SQLException {
        String sql = "INSERT INTO attendance (employee_id, date, check_in, check_out, hours_worked) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, attendance.getEmployeeId());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setTime(3, Time.valueOf(attendance.getCheckIn()));
            stmt.setTime(4, attendance.getCheckOut() != null ? Time.valueOf(attendance.getCheckOut()) : null);
            stmt.setDouble(5, attendance.getHoursWorked() != null ? attendance.getHoursWorked() : 0);
            
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                attendance.setId(keys.getLong(1));
            }
        }
        return attendance;
    }
    
    public List<Attendance> findByEmployeeId(Long employeeId) throws SQLException {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Attendance att = new Attendance();
                att.setId(rs.getLong("id"));
                att.setEmployeeId(rs.getLong("employee_id"));
                att.setDate(rs.getDate("date").toLocalDate());
                att.setCheckIn(rs.getTime("check_in").toLocalTime());
                if (rs.getTime("check_out") != null) {
                    att.setCheckOut(rs.getTime("check_out").toLocalTime());
                }
                att.setHoursWorked(rs.getDouble("hours_worked"));
                attendances.add(att);
            }
        }
        return attendances;
    }
}