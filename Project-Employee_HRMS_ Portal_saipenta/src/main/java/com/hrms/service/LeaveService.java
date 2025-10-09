package com.hrms.service;

import com.hrms.dao.LeaveRequestDAO;
import com.hrms.model.LeaveRequest;

import java.util.List;

public class LeaveService {
    private final LeaveRequestDAO leaveDAO;

    public LeaveService() {
        this.leaveDAO = new LeaveRequestDAO();
    }

    public boolean submitLeaveRequest(LeaveRequest leaveRequest) {
        if (leaveRequest == null || 
            leaveRequest.getEmpId() == null || 
            leaveRequest.getLeaveDate() == null || 
            leaveRequest.getReason() == null) {
            return false;
        }
        
        return leaveDAO.save(leaveRequest);
    }

    public boolean approveLeave(String requestId) {
        return leaveDAO.updateStatus(requestId, "APPROVED");
    }

    public boolean rejectLeave(String requestId) {
        return leaveDAO.updateStatus(requestId, "REJECTED");
    }

    public List<LeaveRequest> getLeaveHistory(String empId) {
        return leaveDAO.findByEmpId(empId);
    }

    public LeaveRequest getLeaveRequest(String requestId) {
        return leaveDAO.findById(requestId);
    }

    public boolean validateLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequest != null &&
               leaveRequest.getEmpId() != null && !leaveRequest.getEmpId().trim().isEmpty() &&
               leaveRequest.getLeaveDate() != null && !leaveRequest.getLeaveDate().trim().isEmpty() &&
               leaveRequest.getReason() != null && !leaveRequest.getReason().trim().isEmpty();
    }
}