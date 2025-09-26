package org.example.entity;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalTime;

public class AttendanceTest {
    
    @Test
    public void testAttendance() {
        Attendance att = new Attendance();
        att.setEmployeeId(1L);
        att.setCheckIn(LocalTime.of(9, 0));
        att.setHoursWorked(8.0);
        
        assertEquals(Long.valueOf(1L), att.getEmployeeId());
        assertEquals(LocalTime.of(9, 0), att.getCheckIn());
        assertEquals(Double.valueOf(8.0), att.getHoursWorked());
    }
}