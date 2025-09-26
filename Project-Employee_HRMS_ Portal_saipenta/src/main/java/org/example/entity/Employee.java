package org.example.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private String id;
    private String name;
    private String email;
    private String departmentId;
    private BigDecimal salary;
    private LocalDate joinDate;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    

    
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
}