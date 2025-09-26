package org.example.controller;

import com.google.gson.Gson;
import org.example.service.PayrollBatchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/batch/*")
public class BatchController extends HttpServlet {
    
    private PayrollBatchService batchService = new PayrollBatchService();
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
            
            if ("/queue-all".equals(pathInfo)) {
                batchService.addAllEmployeesToQueue();
                resp.getWriter().write("{\"message\":\"All employees added to queue\"}");
            } else if ("/process".equals(pathInfo)) {
                String month = req.getParameter("month");
                batchService.processBatch(month);
                resp.getWriter().write("{\"message\":\"Batch processing completed\"}");
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
            
            if ("/queue-size".equals(pathInfo)) {
                int size = batchService.getQueueSize();
                resp.getWriter().write("{\"queueSize\":" + size + "}");
            }
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}