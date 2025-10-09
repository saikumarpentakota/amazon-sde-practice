package com.hrms;

import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;
    private Employee validEmployee;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
        validEmployee = new Employee();
        validEmployee.setEmpCode("EMP001");
        validEmployee.setFirstName("John");
        validEmployee.setLastName("Doe");
        validEmployee.setEmail("john.doe@company.com");
        validEmployee.setPhone("555-0101");
        validEmployee.setHireDate(Date.valueOf("2023-01-01"));
        validEmployee.setDeptId(1);
        validEmployee.setPosition("Software Developer");
        validEmployee.setSalary(new BigDecimal("75000"));
        validEmployee.setStatus("ACTIVE");
    }

    @Test
    void testValidateValidEmployee() {
        assertTrue(employeeService.validateEmployee(validEmployee));
    }

    @Test
    void testValidateEmployeeWithNullCode() {
        validEmployee.setEmpCode(null);
        assertFalse(employeeService.validateEmployee(validEmployee));
    }

    @Test
    void testValidateEmployeeWithEmptyCode() {
        validEmployee.setEmpCode("");
        assertFalse(employeeService.validateEmployee(validEmployee));
    }

    @Test
    void testValidateEmployeeWithInvalidEmail() {
        validEmployee.setEmail("invalid-email");
        assertFalse(employeeService.validateEmployee(validEmployee));
    }

    @Test
    void testValidateEmployeeWithNegativeSalary() {
        validEmployee.setSalary(new BigDecimal("-1000"));
        assertFalse(employeeService.validateEmployee(validEmployee));
    }

    @Test
    void testValidateEmployeeWithZeroSalary() {
        validEmployee.setSalary(BigDecimal.ZERO);
        assertFalse(employeeService.validateEmployee(validEmployee));
    }

    @Test
    void testValidateNullEmployee() {
        assertFalse(employeeService.validateEmployee(null));
    }
}