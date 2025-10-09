package com.hrms.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.service.PayrollService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/batch/*")
public class BatchServlet extends HttpServlet {
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

        try {
            if (pathInfo != null && pathInfo.equals("/status")) {
                // Get queue status
                Map<String, Object> status = new HashMap<>();
                status.put("queueSize", payrollService.getQueueSize());
                status.put("message", "Batch processing queue status");
                
                objectMapper.writeValue(response.getWriter(), status);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Invalid endpoint\"}");
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
            if (pathInfo != null && pathInfo.equals("/add")) {
                // Add employee to payroll queue
                String empIdParam = request.getParameter("empId");
                if (empIdParam != null) {
                    int empId = Integer.parseInt(empIdParam);
                    payrollService.addToPayrollQueue(empId);
                    
                    Map<String, Object> result = new HashMap<>();
                    result.put("message", "Employee added to payroll queue");
                    result.put("empId", empId);
                    result.put("queueSize", payrollService.getQueueSize());
                    
                    objectMapper.writeValue(response.getWriter(), result);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"empId parameter required\"}");
                }
            } else if (pathInfo != null && pathInfo.equals("/process")) {
                // Process entire payroll queue
                String payPeriod = request.getParameter("payPeriod");
                if (payPeriod != null) {
                    int initialQueueSize = payrollService.getQueueSize();
                    payrollService.batchProcessPayroll(payPeriod);
                    
                    Map<String, Object> result = new HashMap<>();
                    result.put("message", "Batch payroll processing completed");
                    result.put("processedCount", initialQueueSize);
                    result.put("payPeriod", payPeriod);
                    result.put("remainingQueueSize", payrollService.getQueueSize());
                    
                    objectMapper.writeValue(response.getWriter(), result);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"payPeriod parameter required\"}");
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