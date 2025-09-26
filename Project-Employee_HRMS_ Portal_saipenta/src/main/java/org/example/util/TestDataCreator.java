package org.example.util;

import org.example.entity.Employee;
import org.example.entity.LeaveRequest;
import org.example.repository.EmployeeDynamoRepository;
import org.example.repository.LeaveRequestRepository;
import org.example.service.PayrollService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestDataCreator {
    
    public static void main(String[] args) {
        System.out.println("Creating test data...");
        
        EmployeeDynamoRepository empRepo = new EmployeeDynamoRepository();
        LeaveRequestRepository leaveRepo = new LeaveRequestRepository();
        PayrollService payrollService = new PayrollService();
        
        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setEmail("john@company.com");
        emp.setDepartmentId("1");
        emp.setSalary(new BigDecimal("50000"));
        emp.setJoinDate(LocalDate.of(2024, 1, 15));
        
        Employee savedEmp = empRepo.save(emp);
        System.out.println("Employee created: " + savedEmp.getId());
        
        payrollService.calculatePayroll(savedEmp.getId(), "2024-01", new BigDecimal("5000"));
        System.out.println("Payroll created for employee: " + savedEmp.getId());
        
        LeaveRequest leave = new LeaveRequest();
        leave.setEmployeeId(1L);
        leave.setLeaveType("SICK");
        leave.setStartDate(LocalDate.of(2024, 1, 20));
        leave.setEndDate(LocalDate.of(2024, 1, 22));
        leave.setReason("Medical appointment");
        
        LeaveRequest savedLeave = leaveRepo.save(leave);
        System.out.println("Leave request created: " + savedLeave.getId());
        
        System.out.println("Test data created successfully!");
    }
}