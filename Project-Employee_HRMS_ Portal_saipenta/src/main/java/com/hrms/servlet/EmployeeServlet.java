package com.hrms.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/api/employees/*")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all employees
                List<Employee> employees = employeeService.getAllEmployees();
                objectMapper.writeValue(response.getWriter(), employees);
            } else {
                // Get specific employee
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    int empId = Integer.parseInt(pathParts[1]);
                    Employee employee = employeeService.getEmployee(empId);
                    if (employee != null) {
                        objectMapper.writeValue(response.getWriter(), employee);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"Employee not found\"}");
                    }
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Employee employee = createEmployeeFromRequest(request);
            
            if (employeeService.validateEmployee(employee)) {
                boolean success = employeeService.createEmployee(employee);
                if (success) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                    objectMapper.writeValue(response.getWriter(), employee);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"Failed to create employee\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Invalid employee data\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo != null) {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    int empId = Integer.parseInt(pathParts[1]);
                    Employee employee = createEmployeeFromRequest(request);
                    employee.setEmpId(empId);
                    
                    if (employeeService.validateEmployee(employee)) {
                        boolean success = employeeService.updateEmployee(employee);
                        if (success) {
                            objectMapper.writeValue(response.getWriter(), employee);
                        } else {
                            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                            response.getWriter().write("{\"error\":\"Employee not found or update failed\"}");
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"Invalid employee data\"}");
                    }
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo != null) {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    int empId = Integer.parseInt(pathParts[1]);
                    boolean success = employeeService.deleteEmployee(empId);
                    if (success) {
                        response.getWriter().write("{\"message\":\"Employee deleted successfully\"}");
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"Employee not found\"}");
                    }
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private Employee createEmployeeFromRequest(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setEmpCode(request.getParameter("empCode"));
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setEmail(request.getParameter("email"));
        employee.setPhone(request.getParameter("phone"));
        
        String hireDateStr = request.getParameter("hireDate");
        if (hireDateStr != null) {
            employee.setHireDate(Date.valueOf(hireDateStr));
        }
        
        String deptIdStr = request.getParameter("deptId");
        if (deptIdStr != null) {
            employee.setDeptId(Integer.parseInt(deptIdStr));
        }
        
        employee.setPosition(request.getParameter("position"));
        
        String salaryStr = request.getParameter("salary");
        if (salaryStr != null) {
            employee.setSalary(new BigDecimal(salaryStr));
        }
        
        String status = request.getParameter("status");
        employee.setStatus(status != null ? status : "ACTIVE");
        
        return employee;
    }
}