package com.hrms.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Employee {
    private int empId;
    private String empCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private int deptId;
    private String position;
    private BigDecimal salary;
    private String status;

    public Employee() {}

    public Employee(String empCode, String firstName, String lastName, String email, 
                   String phone, Date hireDate, int deptId, String position, BigDecimal salary) {
        this.empCode = empCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.deptId = deptId;
        this.position = position;
        this.salary = salary;
        this.status = "ACTIVE";
    }


    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getEmpCode() { return empCode; }
    public void setEmpCode(String empCode) { this.empCode = empCode; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}