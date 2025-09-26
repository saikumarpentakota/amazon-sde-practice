package org.example.controller;

import com.google.gson.Gson;
import org.example.entity.LeaveRequest;
import org.example.repository.LeaveRequestRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/leave/*")
public class LeaveController extends HttpServlet {
    
    private LeaveRequestRepository leaveRepository = new LeaveRequestRepository();
    private Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        
        try {
            LeaveRequest leave = gson.fromJson(req.getReader(), LeaveRequest.class);
            leave.setStatus("PENDING");
            
            LeaveRequest saved = leaveRepository.save(leave);
            resp.getWriter().write(gson.toJson(saved));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"DynamoDB error\"}");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        
        try {
            String pathInfo = req.getPathInfo();
            
            if (pathInfo == null || pathInfo.equals("/")) {
                resp.getWriter().write(gson.toJson(leaveRepository.findAll()));
            } else if (pathInfo.startsWith("/employee/")) {
                Long employeeId = Long.parseLong(pathInfo.substring(10));
                resp.getWriter().write(gson.toJson(leaveRepository.findByEmployeeId(employeeId)));
            } else {
                String id = pathInfo.substring(1);
                LeaveRequest leave = leaveRepository.findById(id);
                resp.getWriter().write(gson.toJson(leave));
            }
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"DynamoDB error\"}");
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        
        try {
            String pathInfo = req.getPathInfo();
            String id = pathInfo.substring(1);
            
            LeaveRequest leave = gson.fromJson(req.getReader(), LeaveRequest.class);
            leave.setId(id);
            
            LeaveRequest updated = leaveRepository.update(leave);
            resp.getWriter().write(gson.toJson(updated));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"DynamoDB error\"}");
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            String id = pathInfo.substring(1);
            
            leaveRepository.deleteById(id);
            resp.setStatus(204);
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"DynamoDB error\"}");
        }
    }
}