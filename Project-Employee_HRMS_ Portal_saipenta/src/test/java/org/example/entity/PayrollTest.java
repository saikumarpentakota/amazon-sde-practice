package org.example.entity;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;

public class PayrollTest {
    
    @Test
    public void testPayrollCalculation() {
        Payroll payroll = new Payroll();
        payroll.setBasicSalary(new BigDecimal("50000"));
        payroll.setOvertime(new BigDecimal("5000"));
        payroll.setTotal(payroll.getBasicSalary().add(payroll.getOvertime()));
        
        assertEquals(new BigDecimal("55000"), payroll.getTotal());
    }
}