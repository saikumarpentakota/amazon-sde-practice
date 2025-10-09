package com.hrms.model;

public class LeaveRequest {
    private String requestId;
    private String empId;
    private String leaveDate;
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED
    private String requestDate;

    public LeaveRequest() {}

    public LeaveRequest(String empId, String leaveDate, String reason) {
        this.empId = empId;
        this.leaveDate = leaveDate;
        this.reason = reason;
        this.status = "PENDING";
        this.requestId = generateRequestId();
    }

    private String generateRequestId() {
        return "LR" + System.currentTimeMillis();
    }


    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getLeaveDate() { return leaveDate; }
    public void setLeaveDate(String leaveDate) { this.leaveDate = leaveDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRequestDate() { return requestDate; }
    public void setRequestDate(String requestDate) { this.requestDate = requestDate; }
}