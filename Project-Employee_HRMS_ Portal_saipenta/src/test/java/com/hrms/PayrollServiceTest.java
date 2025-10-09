package com.hrms;

import com.hrms.model.Employee;
import com.hrms.model.Payroll;
import com.hrms.service.PayrollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PayrollServiceTest {
    private PayrollService payrollService;
    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        payrollService = new PayrollService();
        testEmployee = new Employee();
        testEmployee.setEmpId(1);
        testEmployee.setEmpCode("EMP001");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setEmail("john.doe@company.com");
        testEmployee.setHireDate(Date.valueOf("2023-01-01"));
        testEmployee.setDeptId(1);
        testEmployee.setPosition("Software Developer");
        testEmployee.setSalary(new BigDecimal("75000"));
        testEmployee.setStatus("ACTIVE");
    }

    @Test
    void testPayrollCalculation() {
        Payroll payroll = new Payroll(1, "2024-01", new BigDecimal("75000"));
        payroll.setAllowances(new BigDecimal("1500"));
        payroll.setDeductions(new BigDecimal("2250"));
        payroll.calculateSalary();

        assertEquals(0, new BigDecimal("74250.00").compareTo(payroll.getGrossSalary()));
        assertEquals(0, new BigDecimal("11137.50").compareTo(payroll.getTaxDeduction()));
        assertEquals(0, new BigDecimal("63112.50").compareTo(payroll.getNetSalary()));
    }

    @Test
    void testQueueOperations() {
        assertEquals(0, payrollService.getQueueSize());
        
        payrollService.addToPayrollQueue(1);
        payrollService.addToPayrollQueue(2);
        
        assertEquals(2, payrollService.getQueueSize());
    }

    @Test
    void testSalaryCalculationWithDifferentPositions() {
        // Test manager allowances (10%)
        testEmployee.setPosition("Manager");
        testEmployee.setSalary(new BigDecimal("100000"));
        
        Payroll payroll = new Payroll(1, "2024-01", testEmployee.getSalary());
        BigDecimal allowances = testEmployee.getSalary().multiply(new BigDecimal("0.10"));
        payroll.setAllowances(allowances);
        
        BigDecimal deductions = testEmployee.getSalary().multiply(new BigDecimal("0.03"));
        payroll.setDeductions(deductions);
        
        payroll.calculateSalary();
        
        assertEquals(0, new BigDecimal("107000.00").compareTo(payroll.getGrossSalary()));
        assertEquals(0, new BigDecimal("16050.00").compareTo(payroll.getTaxDeduction()));
        assertEquals(0, new BigDecimal("90950.00").compareTo(payroll.getNetSalary()));
    }

    @Test
    void testBatchProcessing() {
        payrollService.addToPayrollQueue(1);
        payrollService.addToPayrollQueue(2);
        payrollService.addToPayrollQueue(3);
        
        assertEquals(3, payrollService.getQueueSize());
        
        // Simulate batch processing
        payrollService.batchProcessPayroll("2024-01");
        
        assertEquals(0, payrollService.getQueueSize());
    }
}