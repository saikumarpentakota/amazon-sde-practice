package org.example.entity;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;

public class EmployeeTest {
    
    @Test
    public void testEmployee() {
        Employee emp = new Employee();
        emp.setId("emp-1");
        emp.setName("John");
        emp.setSalary(new BigDecimal("50000"));
        
        assertEquals("emp-1", emp.getId());
        assertEquals("John", emp.getName());
        assertEquals(new BigDecimal("50000"), emp.getSalary());
    }
}