package com.hrms.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Attendance {
    private int attendanceId;
    private int empId;
    private Date attendanceDate;
    private String status; // PRESENT, ABSENT, LATE, HALF_DAY
    private Time checkInTime;
    private Time checkOutTime;
    private String remarks;
    private Timestamp createdAt;
    private String employeeName; // For display purposes

    public Attendance() {}

    public Attendance(int empId, Date attendanceDate, String status) {
        this.empId = empId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }


    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public Date getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(Date attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Time getCheckInTime() { return checkInTime; }
    public void setCheckInTime(Time checkInTime) { this.checkInTime = checkInTime; }

    public Time getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(Time checkOutTime) { this.checkOutTime = checkOutTime; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
}