package com.hrms.service;

import com.hrms.dao.EmployeeDAO;
import com.hrms.dao.PayrollDAO;
import com.hrms.model.Employee;
import com.hrms.model.Payroll;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PayrollService {
    private final PayrollDAO payrollDAO;
    private final EmployeeDAO employeeDAO;
    private final Queue<Integer> payrollQueue;

    public PayrollService() {
        this.payrollDAO = new PayrollDAO();
        this.employeeDAO = new EmployeeDAO();
        this.payrollQueue = new LinkedList<>();
    }

    public Payroll processPayroll(int empId, String payPeriod) {
        Employee employee = employeeDAO.findById(empId);
        if (employee == null) {
            return null;
        }

        // Check if payroll already exists for this period
        Payroll existing = payrollDAO.findByEmpIdAndPeriod(empId, payPeriod);
        if (existing != null) {
            return existing;
        }

        // Create new payroll
        Payroll payroll = new Payroll(empId, payPeriod, employee.getSalary());
        
        // Calculate allowances based on position
        BigDecimal allowances = calculateAllowances(employee);
        payroll.setAllowances(allowances);
        
        // Calculate deductions
        BigDecimal deductions = calculateDeductions(employee);
        payroll.setDeductions(deductions);
        
        // Recalculate salary with allowances and deductions
        payroll.calculateSalary();
        
        if (payrollDAO.save(payroll)) {
            return payroll;
        }
        return null;
    }

    public Payroll generateSalarySlip(int empId, String payPeriod) {
        return payrollDAO.findByEmpIdAndPeriod(empId, payPeriod);
    }

    public List<Payroll> getPayrollByPeriod(String payPeriod) {
        return payrollDAO.findByPeriod(payPeriod);
    }

    public List<Payroll> getAllPayrolls() {
        return payrollDAO.findAll();
    }

    public void addToPayrollQueue(int empId) {
        payrollQueue.offer(empId);
    }

    public void batchProcessPayroll(String payPeriod) {
        while (!payrollQueue.isEmpty()) {
            int empId = payrollQueue.poll();
            processPayroll(empId, payPeriod);
        }
    }

    public int getQueueSize() {
        return payrollQueue.size();
    }

    private BigDecimal calculateAllowances(Employee employee) {
        BigDecimal allowances = BigDecimal.ZERO;
        
        // Position-based allowances
        String position = employee.getPosition().toLowerCase();
        if (position.contains("manager")) {
            allowances = employee.getSalary().multiply(new BigDecimal("0.10")); // 10% for managers
        } else if (position.contains("senior")) {
            allowances = employee.getSalary().multiply(new BigDecimal("0.05")); // 5% for senior positions
        } else {
            allowances = employee.getSalary().multiply(new BigDecimal("0.02")); // 2% for others
        }
        
        return allowances;
    }

    private BigDecimal calculateDeductions(Employee employee) {
        // Standard deductions (insurance, etc.)
        return employee.getSalary().multiply(new BigDecimal("0.03")); // 3% standard deduction
    }
}