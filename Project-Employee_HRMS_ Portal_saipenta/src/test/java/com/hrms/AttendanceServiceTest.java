package com.hrms;

import com.hrms.service.AttendanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceServiceTest {
    private AttendanceService attendanceService;

    @BeforeEach
    void setUp() {
        attendanceService = new AttendanceService();
    }

    @Test
    void testDetermineAttendanceStatusPresent() {
        Time checkInTime = Time.valueOf(LocalTime.of(9, 0)); // 9:00 AM
        String status = attendanceService.determineAttendanceStatus(checkInTime);
        assertEquals("PRESENT", status);
    }

    @Test
    void testDetermineAttendanceStatusEarly() {
        Time checkInTime = Time.valueOf(LocalTime.of(8, 30)); // 8:30 AM
        String status = attendanceService.determineAttendanceStatus(checkInTime);
        assertEquals("PRESENT", status);
    }

    @Test
    void testDetermineAttendanceStatusLate() {
        Time checkInTime = Time.valueOf(LocalTime.of(9, 45)); // 9:45 AM (45 minutes late)
        String status = attendanceService.determineAttendanceStatus(checkInTime);
        assertEquals("LATE", status);
    }

    @Test
    void testDetermineAttendanceStatusBoundary() {
        Time checkInTime = Time.valueOf(LocalTime.of(9, 30)); // 9:30 AM (exactly 30 minutes)
        String status = attendanceService.determineAttendanceStatus(checkInTime);
        assertEquals("PRESENT", status);
    }

    @Test
    void testDetermineAttendanceStatusJustLate() {
        Time checkInTime = Time.valueOf(LocalTime.of(9, 31)); // 9:31 AM (31 minutes late)
        String status = attendanceService.determineAttendanceStatus(checkInTime);
        assertEquals("LATE", status);
    }
}