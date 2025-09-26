package org.example.service;

import org.example.dto.MonthlyPayrollDTO;
import org.example.entity.Employee;
import org.example.entity.Payroll;
import org.example.repository.EmployeeDynamoRepository;
import org.example.repository.PayrollDynamoRepository;

import java.util.ArrayList;
import java.util.List;

public class MonthlyPayrollService {
    
    private PayrollDynamoRepository payrollRepository = new PayrollDynamoRepository();
    private EmployeeDynamoRepository employeeRepository = new EmployeeDynamoRepository();
    
    public List<MonthlyPayrollDTO> getMonthlyPayrollWithEmployeeDetails(String month) {
        List<MonthlyPayrollDTO> result = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        
        for (Employee employee : employees) {
            List<Payroll> payrolls = payrollRepository.findByEmployeeId(employee.getId());
            
            for (Payroll payroll : payrolls) {
                if (month.equals(payroll.getMonth())) {
                    MonthlyPayrollDTO dto = new MonthlyPayrollDTO();
                    dto.setPayrollId(payroll.getId());
                    dto.setEmployeeId(employee.getId());
                    dto.setEmployeeName(employee.getName());
                    dto.setEmployeeEmail(employee.getEmail());
                    dto.setMonth(payroll.getMonth());
                    dto.setBasicSalary(payroll.getBasicSalary());
                    dto.setOvertime(payroll.getOvertime());
                    dto.setTotal(payroll.getTotal());
                    result.add(dto);
                }
            }
        }
        
        return result;
    }
}