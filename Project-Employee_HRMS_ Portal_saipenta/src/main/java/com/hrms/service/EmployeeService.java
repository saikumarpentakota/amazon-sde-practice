package com.hrms.service;

import com.hrms.dao.EmployeeDAO;
import com.hrms.model.Employee;

import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public boolean createEmployee(Employee employee) {
        if (employee == null || employee.getEmpCode() == null || employee.getEmpCode().trim().isEmpty()) {
            return false;
        }
        return employeeDAO.save(employee);
    }

    public Employee getEmployee(int empId) {
        return employeeDAO.findById(empId);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    public boolean updateEmployee(Employee employee) {
        if (employee == null || employee.getEmpId() <= 0) {
            return false;
        }
        return employeeDAO.update(employee);
    }

    public boolean deleteEmployee(int empId) {
        if (empId <= 0) {
            return false;
        }
        return employeeDAO.delete(empId);
    }

    public boolean validateEmployee(Employee employee) {
        return employee != null &&
               employee.getEmpCode() != null && !employee.getEmpCode().trim().isEmpty() &&
               employee.getFirstName() != null && !employee.getFirstName().trim().isEmpty() &&
               employee.getLastName() != null && !employee.getLastName().trim().isEmpty() &&
               employee.getEmail() != null && employee.getEmail().contains("@") &&
               employee.getSalary() != null && employee.getSalary().doubleValue() > 0;
    }
}