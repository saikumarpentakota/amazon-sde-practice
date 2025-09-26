package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Department;
import org.example.entity.Payroll;
import org.example.repository.EmployeeDynamoRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnhancedPayrollService {
    
    private EmployeeDynamoRepository employeeRepository = new EmployeeDynamoRepository();
    private Map<String, Department> departments = new HashMap<>();
    
    public EnhancedPayrollService() {
        initializeDepartments();
    }
    
    private void initializeDepartments() {
        Department it = new Department();
        it.setId("1");
        it.setName("IT");
        it.setAllowanceRate(new BigDecimal("0.20")); // 20% allowance
        it.setDeductionRate(new BigDecimal("0.10")); // 10% deduction
        departments.put("1", it);
        
        Department hr = new Department();
        hr.setId("2");
        hr.setName("HR");
        hr.setAllowanceRate(new BigDecimal("0.15")); // 15% allowance
        hr.setDeductionRate(new BigDecimal("0.08")); // 8% deduction
        departments.put("2", hr);
        
        Department finance = new Department();
        finance.setId("3");
        finance.setName("Finance");
        finance.setAllowanceRate(new BigDecimal("0.18")); // 18% allowance
        finance.setDeductionRate(new BigDecimal("0.12")); // 12% deduction
        departments.put("3", finance);
    }
    
    public Payroll generatePayroll(String employeeId, String month, BigDecimal overtime) {
        Employee employee = employeeRepository.findById(employeeId);
        if (employee == null) return null;
        
        Department dept = departments.get(employee.getDepartmentId());
        if (dept == null) return null;
        
        // Salary computation logic
        BigDecimal basicSalary = employee.getSalary();
        BigDecimal allowances = basicSalary.multiply(dept.getAllowanceRate());
        BigDecimal deductions = basicSalary.multiply(dept.getDeductionRate());
        BigDecimal total = basicSalary.add(allowances).add(overtime).subtract(deductions);
        
        Payroll payroll = new Payroll();
        payroll.setId(UUID.randomUUID().toString());
        payroll.setEmployeeId(employeeId);
        payroll.setMonth(month);
        payroll.setBasicSalary(basicSalary);
        payroll.setAllowances(allowances);
        payroll.setDeductions(deductions);
        payroll.setOvertime(overtime);
        payroll.setTotal(total);
        
        return payroll;
    }
}