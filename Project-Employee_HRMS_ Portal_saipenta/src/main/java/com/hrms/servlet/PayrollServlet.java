package com.hrms.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.model.Payroll;
import com.hrms.service.PayrollService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/payroll/*")
public class PayrollServlet extends HttpServlet {
    private PayrollService payrollService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        payrollService = new PayrollService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String empIdParam = request.getParameter("empId");
        String payPeriod = request.getParameter("payPeriod");

        try {
            if (pathInfo != null && pathInfo.equals("/slip")) {
                // Generate salary slip
                if (empIdParam != null && payPeriod != null) {
                    int empId = Integer.parseInt(empIdParam);
                    Payroll payroll = payrollService.generateSalarySlip(empId, payPeriod);
                    if (payroll != null) {
                        objectMapper.writeValue(response.getWriter(), payroll);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"Payroll not found\"}");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"empId and payPeriod parameters required\"}");
                }
            } else if (payPeriod != null) {
                // Get payroll by period
                List<Payroll> payrolls = payrollService.getPayrollByPeriod(payPeriod);
                objectMapper.writeValue(response.getWriter(), payrolls);
            } else {
                // Get all payroll records
                List<Payroll> payrolls = payrollService.getAllPayrolls();
                objectMapper.writeValue(response.getWriter(), payrolls);
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

        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo != null && pathInfo.equals("/process")) {
                // Process payroll for employee
                String empIdParam = request.getParameter("empId");
                String payPeriod = request.getParameter("payPeriod");
                
                if (empIdParam != null && payPeriod != null) {
                    int empId = Integer.parseInt(empIdParam);
                    Payroll payroll = payrollService.processPayroll(empId, payPeriod);
                    if (payroll != null) {
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        objectMapper.writeValue(response.getWriter(), payroll);
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"Failed to process payroll\"}");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"empId and payPeriod parameters required\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Invalid endpoint\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}