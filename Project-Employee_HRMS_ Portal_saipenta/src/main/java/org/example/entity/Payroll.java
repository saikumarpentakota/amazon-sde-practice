package org.example.entity;

import java.math.BigDecimal;

public class Payroll {
    private String id;
    private String employeeId;
    private String month;
    private BigDecimal basicSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;
    private BigDecimal overtime;
    private BigDecimal total;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }


    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public BigDecimal getBasicSalary() { return basicSalary; }
    public void setBasicSalary(BigDecimal basicSalary) { this.basicSalary = basicSalary; }
    
    public BigDecimal getAllowances() { return allowances; }
    public void setAllowances(BigDecimal allowances) { this.allowances = allowances; }
    
    public BigDecimal getDeductions() { return deductions; }
    public void setDeductions(BigDecimal deductions) { this.deductions = deductions; }
    
    public BigDecimal getOvertime() { return overtime; }
    public void setOvertime(BigDecimal overtime) { this.overtime = overtime; }
    
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}