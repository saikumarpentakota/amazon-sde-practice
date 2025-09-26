package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Payroll;
import org.example.repository.EmployeeDynamoRepository;
import org.example.repository.PayrollDynamoRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class PayrollService {
    
    private EmployeeDynamoRepository employeeRepository = new EmployeeDynamoRepository();
    private PayrollDynamoRepository payrollRepository = new PayrollDynamoRepository();
    
    public Payroll calculatePayroll(String employeeId, String month, BigDecimal overtime) {
        Employee employee = employeeRepository.findById(employeeId);
        if (employee == null) return null;
        
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(employeeId);
        payroll.setMonth(month);
        payroll.setBasicSalary(employee.getSalary());
        payroll.setOvertime(overtime != null ? overtime : BigDecimal.ZERO);
        payroll.setTotal(payroll.getBasicSalary().add(payroll.getOvertime()));
        
        return payrollRepository.save(payroll);
    }
}