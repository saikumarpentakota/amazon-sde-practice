package org.example.entity;

import java.math.BigDecimal;

public class Department {
    private String id;
    private String name;
    private BigDecimal allowanceRate;
    private BigDecimal deductionRate;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getAllowanceRate() { return allowanceRate; }
    public void setAllowanceRate(BigDecimal allowanceRate) { this.allowanceRate = allowanceRate; }
    
    public BigDecimal getDeductionRate() { return deductionRate; }
    public void setDeductionRate(BigDecimal deductionRate) { this.deductionRate = deductionRate; }
}