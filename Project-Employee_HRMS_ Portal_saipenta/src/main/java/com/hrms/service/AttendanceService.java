package com.hrms.service;

import com.hrms.dao.AttendanceDAO;
import com.hrms.model.Attendance;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AttendanceService {
    private final AttendanceDAO attendanceDAO;

    public AttendanceService() {
        this.attendanceDAO = new AttendanceDAO();
    }

    public boolean markAttendance(int empId, Date attendanceDate, String status) {
        Attendance attendance = new Attendance(empId, attendanceDate, status);
        
        // Set check-in time for present/late status
        if ("PRESENT".equals(status) || "LATE".equals(status)) {
            attendance.setCheckInTime(Time.valueOf(LocalTime.now()));
        }
        
        return attendanceDAO.markAttendance(attendance);
    }

    public boolean markAttendanceWithTimes(int empId, Date attendanceDate, String status, 
                                         Time checkInTime, Time checkOutTime) {
        Attendance attendance = new Attendance(empId, attendanceDate, status);
        attendance.setCheckInTime(checkInTime);
        attendance.setCheckOutTime(checkOutTime);
        
        return attendanceDAO.markAttendance(attendance);
    }

    public List<Attendance> getAttendanceByEmployee(int empId) {
        return attendanceDAO.findByEmpId(empId);
    }

    public List<Attendance> getAttendanceByDate(Date date) {
        return attendanceDAO.findByDate(date);
    }

    public List<Attendance> getMonthlyReport(int empId, String month) {
        return attendanceDAO.getMonthlyReport(empId, month);
    }

    public List<Attendance> getTodayAttendance() {
        Date today = Date.valueOf(LocalDate.now());
        return attendanceDAO.findByDate(today);
    }

    public String determineAttendanceStatus(Time checkInTime) {
        LocalTime standardTime = LocalTime.of(9, 0); // 9:00 AM
        LocalTime actualTime = checkInTime.toLocalTime();
        
        if (actualTime.isAfter(standardTime.plusMinutes(30))) {
            return "LATE";
        } else {
            return "PRESENT";
        }
    }
}