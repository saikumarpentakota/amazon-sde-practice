package com.hrms.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.model.LeaveRequest;
import com.hrms.service.LeaveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/leave/*")
public class LeaveServlet extends HttpServlet {
    private LeaveService leaveService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        leaveService = new LeaveService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String empIdParam = request.getParameter("empId");

        try {
            if (pathInfo != null) {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    // Get specific leave request
                    String requestId = pathParts[1];
                    LeaveRequest leaveRequest = leaveService.getLeaveRequest(requestId);
                    if (leaveRequest != null) {
                        objectMapper.writeValue(response.getWriter(), leaveRequest);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"Leave request not found\"}");
                    }
                }
            } else if (empIdParam != null) {
                // Get leave history by employee
                List<LeaveRequest> leaveRequests = leaveService.getLeaveHistory(empIdParam);
                objectMapper.writeValue(response.getWriter(), leaveRequests);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"empId parameter required\"}");
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
            if (pathInfo == null || pathInfo.equals("/")) {
                // Submit new leave request
                String empId = request.getParameter("empId");
                String leaveDate = request.getParameter("leaveDate");
                String reason = request.getParameter("reason");

                if (empId != null && leaveDate != null && reason != null) {
                    LeaveRequest leaveRequest = new LeaveRequest(empId, leaveDate, reason);
                    
                    if (leaveService.validateLeaveRequest(leaveRequest)) {
                        boolean success = leaveService.submitLeaveRequest(leaveRequest);
                        if (success) {
                            response.setStatus(HttpServletResponse.SC_CREATED);
                            objectMapper.writeValue(response.getWriter(), leaveRequest);
                        } else {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.getWriter().write("{\"error\":\"Failed to submit leave request\"}");
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"Invalid leave request data\"}");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"empId, leaveDate, and reason parameters required\"}");
                }
            } else if (pathInfo.equals("/approve")) {
                // Approve leave request
                String requestId = request.getParameter("requestId");
                if (requestId != null) {
                    boolean success = leaveService.approveLeave(requestId);
                    if (success) {
                        response.getWriter().write("{\"message\":\"Leave request approved\"}");
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"Failed to approve leave request\"}");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"requestId parameter required\"}");
                }
            } else if (pathInfo.equals("/reject")) {
                // Reject leave request
                String requestId = request.getParameter("requestId");
                if (requestId != null) {
                    boolean success = leaveService.rejectLeave(requestId);
                    if (success) {
                        response.getWriter().write("{\"message\":\"Leave request rejected\"}");
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"Failed to reject leave request\"}");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"requestId parameter required\"}");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}