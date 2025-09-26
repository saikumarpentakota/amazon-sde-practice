package org.example.controller;

import com.google.gson.Gson;
import org.example.entity.Payroll;
import org.example.service.PayrollBatchService;
import org.example.service.EnhancedPayrollService;

import java.util.Map;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/api/payroll/generate")
public class PayrollGenerationController extends HttpServlet {
    
    private PayrollBatchService batchService = new PayrollBatchService();
    private EnhancedPayrollService payrollService = new EnhancedPayrollService();
    private Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        
        try {
            String type = req.getParameter("type");
            String month = req.getParameter("month");
            
            if ("batch".equals(type)) {
                // Batch payroll generation
                batchService.addAllEmployeesToQueue();
                List<Payroll> payrolls = batchService.processBatch(month);
                
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Batch payroll generated successfully");
                response.put("count", payrolls.size());
                response.put("payrolls", payrolls);
                resp.getWriter().write(gson.toJson(response));
                
            } else {
                // Single employee payroll generation
                String employeeId = req.getParameter("employeeId");
                String overtimeStr = req.getParameter("overtime");
                BigDecimal overtime = overtimeStr != null ? new BigDecimal(overtimeStr) : BigDecimal.ZERO;
                
                Payroll payroll = payrollService.generatePayroll(employeeId, month, overtime);
                
                if (payroll != null) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Payroll generated successfully");
                    response.put("payroll", payroll);
                    resp.getWriter().write(gson.toJson(response));
                } else {
                    resp.setStatus(404);
                    resp.getWriter().write("{\"error\":\"Employee not found\"}");
                }
            }
            
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"Payroll generation failed: " + e.getMessage() + "\"}");
        }
    }
}