package com.hrms.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payroll {
    private int payrollId;
    private int empId;
    private String payPeriod;
    private BigDecimal basicSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;
    private BigDecimal grossSalary;
    private BigDecimal taxDeduction;
    private BigDecimal netSalary;
    private Timestamp processedDate;
    private String employeeName;

    public Payroll() {}

    public Payroll(int empId, String payPeriod, BigDecimal basicSalary) {
        this.empId = empId;
        this.payPeriod = payPeriod;
        this.basicSalary = basicSalary;
        this.allowances = BigDecimal.ZERO;
        this.deductions = BigDecimal.ZERO;
        calculateSalary();
    }

    public void calculateSalary() {
        this.grossSalary = basicSalary.add(allowances).subtract(deductions);
        this.taxDeduction = grossSalary.multiply(new BigDecimal("0.15"));
        this.netSalary = grossSalary.subtract(taxDeduction);
    }


    public int getPayrollId() { return payrollId; }
    public void setPayrollId(int payrollId) { this.payrollId = payrollId; }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getPayPeriod() { return payPeriod; }
    public void setPayPeriod(String payPeriod) { this.payPeriod = payPeriod; }

    public BigDecimal getBasicSalary() { return basicSalary; }
    public void setBasicSalary(BigDecimal basicSalary) { this.basicSalary = basicSalary; }

    public BigDecimal getAllowances() { return allowances; }
    public void setAllowances(BigDecimal allowances) { this.allowances = allowances; }

    public BigDecimal getDeductions() { return deductions; }
    public void setDeductions(BigDecimal deductions) { this.deductions = deductions; }

    public BigDecimal getGrossSalary() { return grossSalary; }
    public void setGrossSalary(BigDecimal grossSalary) { this.grossSalary = grossSalary; }

    public BigDecimal getTaxDeduction() { return taxDeduction; }
    public void setTaxDeduction(BigDecimal taxDeduction) { this.taxDeduction = taxDeduction; }

    public BigDecimal getNetSalary() { return netSalary; }
    public void setNetSalary(BigDecimal netSalary) { this.netSalary = netSalary; }

    public Timestamp getProcessedDate() { return processedDate; }
    public void setProcessedDate(Timestamp processedDate) { this.processedDate = processedDate; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
}