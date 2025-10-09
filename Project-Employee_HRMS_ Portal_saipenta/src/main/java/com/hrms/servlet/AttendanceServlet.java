package com.hrms.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.model.Attendance;
import com.hrms.service.AttendanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/api/attendance/*")
public class AttendanceServlet extends HttpServlet {
    private AttendanceService attendanceService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        attendanceService = new AttendanceService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String empIdParam = request.getParameter("empId");
        String dateParam = request.getParameter("date");
        String monthParam = request.getParameter("month");

        try {
            if (pathInfo != null && pathInfo.equals("/today")) {
                // Get today's attendance
                List<Attendance> attendances = attendanceService.getTodayAttendance();
                objectMapper.writeValue(response.getWriter(), attendances);
            } else if (pathInfo != null && pathInfo.equals("/report")) {
                // Get monthly report
                if (empIdParam != null && monthParam != null) {
                    int empId = Integer.parseInt(empIdParam);
                    List<Attendance> attendances = attendanceService.getMonthlyReport(empId, monthParam);
                    objectMapper.writeValue(response.getWriter(), attendances);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"empId and month parameters required\"}");
                }
            } else if (empIdParam != null) {
                // Get attendance by employee
                int empId = Integer.parseInt(empIdParam);
                List<Attendance> attendances = attendanceService.getAttendanceByEmployee(empId);
                objectMapper.writeValue(response.getWriter(), attendances);
            } else if (dateParam != null) {
                // Get attendance by date
                Date date = Date.valueOf(dateParam);
                List<Attendance> attendances = attendanceService.getAttendanceByDate(date);
                objectMapper.writeValue(response.getWriter(), attendances);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"empId or date parameter required\"}");
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
            String empIdParam = request.getParameter("empId");
            String dateParam = request.getParameter("date");
            String status = request.getParameter("status");
            String checkInTimeParam = request.getParameter("checkInTime");
            String checkOutTimeParam = request.getParameter("checkOutTime");

            if (empIdParam != null && dateParam != null && status != null) {
                int empId = Integer.parseInt(empIdParam);
                Date attendanceDate = Date.valueOf(dateParam);
                
                boolean success;
                if ((checkInTimeParam != null && !checkInTimeParam.trim().isEmpty()) || 
                    (checkOutTimeParam != null && !checkOutTimeParam.trim().isEmpty())) {
                    Time checkInTime = (checkInTimeParam != null && !checkInTimeParam.trim().isEmpty()) ? 
                        Time.valueOf(checkInTimeParam + ":00") : null;
                    Time checkOutTime = (checkOutTimeParam != null && !checkOutTimeParam.trim().isEmpty()) ? 
                        Time.valueOf(checkOutTimeParam + ":00") : null;
                    success = attendanceService.markAttendanceWithTimes(empId, attendanceDate, status, checkInTime, checkOutTime);
                } else {
                    success = attendanceService.markAttendance(empId, attendanceDate, status);
                }

                if (success) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                    response.getWriter().write("{\"message\":\"Attendance marked successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"Failed to mark attendance\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"empId, date, and status parameters required\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            String errorMsg = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
            response.getWriter().write("{\"error\":\"" + errorMsg + "\"}");
        }
    }
}