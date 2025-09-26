package org.example.controller;

import com.google.gson.Gson;
import org.example.entity.Payroll;
import org.example.repository.PayrollDynamoRepository;
import org.example.service.PayrollService;
import org.example.service.MonthlyPayrollService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/api/payroll/*")
public class PayrollController extends HttpServlet {
    
    private PayrollService payrollService = new PayrollService();
    private PayrollDynamoRepository payrollRepository = new PayrollDynamoRepository();
    private MonthlyPayrollService monthlyPayrollService = new MonthlyPayrollService();
    private Gson gson = new Gson();
    
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        
        try {
            String pathInfo = req.getPathInfo();
            
            if ("/calculate".equals(pathInfo) || "/generate".equals(pathInfo)) {
                String employeeId = req.getParameter("employeeId");
                String month = req.getParameter("month");
                String overtimeStr = req.getParameter("overtime");
                BigDecimal overtime = overtimeStr != null ? new BigDecimal(overtimeStr) : BigDecimal.ZERO;
                
                Payroll result = payrollService.calculatePayroll(employeeId, month, overtime);
                resp.getWriter().write("{\"payroll\":" + gson.toJson(result) + "}");
            }
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        
        try {
            String pathInfo = req.getPathInfo();
            
            if (pathInfo.startsWith("/employee/")) {
                String employeeId = pathInfo.substring(10);
                resp.getWriter().write(gson.toJson(payrollRepository.findByEmployeeId(employeeId)));
            } else if (pathInfo.startsWith("/month/")) {
                String month = pathInfo.substring(7);
                resp.getWriter().write(gson.toJson(monthlyPayrollService.getMonthlyPayrollWithEmployeeDetails(month)));
            }
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}