package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Payroll;
import org.example.repository.EmployeeDynamoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

public class PayrollBatchService {
    
    private Queue<String> payrollQueue = new ConcurrentLinkedQueue<>();
    private EnhancedPayrollService payrollService = new EnhancedPayrollService();
    private EmployeeDynamoRepository employeeRepository = new EmployeeDynamoRepository();
    
    public void addToQueue(String employeeId) {
        payrollQueue.offer(employeeId);
    }
    
    public List<Payroll> processBatch(String month) {
        List<Payroll> generatedPayrolls = new ArrayList<>();
        
        while (!payrollQueue.isEmpty()) {
            String employeeId = payrollQueue.poll();
            Payroll payroll = payrollService.generatePayroll(employeeId, month, BigDecimal.ZERO);
            if (payroll != null) {
                generatedPayrolls.add(payroll);
            }
        }
        
        return generatedPayrolls;
    }
    
    public void addAllEmployeesToQueue() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee emp : employees) {
            addToQueue(emp.getId());
        }
    }
    
    public int getQueueSize() {
        return payrollQueue.size();
    }
}