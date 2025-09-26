package org.example.dto;

import java.math.BigDecimal;

public class MonthlyPayrollDTO {
    private String payrollId;
    private String employeeId;
    private String employeeName;
    private String employeeEmail;
    private String month;
    private BigDecimal basicSalary;
    private BigDecimal overtime;
    private BigDecimal total;

    public String getPayrollId() { return payrollId; }
    public void setPayrollId(String payrollId) { this.payrollId = payrollId; }
    
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    
    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }
    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public BigDecimal getBasicSalary() { return basicSalary; }
    public void setBasicSalary(BigDecimal basicSalary) { this.basicSalary = basicSalary; }
    
    public BigDecimal getOvertime() { return overtime; }
    public void setOvertime(BigDecimal overtime) { this.overtime = overtime; }
    
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}