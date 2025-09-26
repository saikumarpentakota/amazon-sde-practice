package org.example.controller;

import com.google.gson.Gson;
import org.example.entity.Attendance;
import org.example.repository.AttendanceRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;

@WebServlet("/api/attendance/*")
public class AttendanceController extends HttpServlet {
    
    private AttendanceRepository attendanceRepository = new AttendanceRepository();
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
            // Handle form data from frontend
            String employeeIdStr = req.getParameter("employeeId");
            String checkInStr = req.getParameter("checkIn");
            String checkOutStr = req.getParameter("checkOut");
            
            if (employeeIdStr == null || checkInStr == null || checkOutStr == null) {
                // Try JSON if form data not available
                Attendance attendance = gson.fromJson(req.getReader(), Attendance.class);
                if (attendance.getCheckIn() != null && attendance.getCheckOut() != null) {
                    Duration duration = Duration.between(attendance.getCheckIn(), attendance.getCheckOut());
                    attendance.setHoursWorked(duration.toMinutes() / 60.0);
                }
                Attendance saved = attendanceRepository.save(attendance);
                resp.getWriter().write(gson.toJson(saved));
            } else {
                // Handle form data
                Attendance attendance = new Attendance();
                attendance.setEmployeeId(Long.parseLong(employeeIdStr));
                attendance.setCheckIn(LocalTime.parse(checkInStr));
                attendance.setCheckOut(LocalTime.parse(checkOutStr));
                attendance.setDate(java.time.LocalDate.now());
                
                Duration duration = Duration.between(attendance.getCheckIn(), attendance.getCheckOut());
                attendance.setHoursWorked(duration.toMinutes() / 60.0);
                
                Attendance saved = attendanceRepository.save(attendance);
                resp.getWriter().write(gson.toJson(saved));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"Database error: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"Invalid request: " + e.getMessage() + "\"}");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        
        try {
            String pathInfo = req.getPathInfo();
            
            if (pathInfo.startsWith("/employee/")) {
                Long employeeId = Long.parseLong(pathInfo.substring(10));
                resp.getWriter().write(gson.toJson(attendanceRepository.findByEmployeeId(employeeId)));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"Database error: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"Invalid request: " + e.getMessage() + "\"}");
        }
    }
}