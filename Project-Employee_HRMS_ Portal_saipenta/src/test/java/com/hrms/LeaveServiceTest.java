package com.hrms;

import com.hrms.model.LeaveRequest;
import com.hrms.service.LeaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveServiceTest {
    private LeaveService leaveService;
    private LeaveRequest validLeaveRequest;

    @BeforeEach
    void setUp() {
        leaveService = new LeaveService();
        validLeaveRequest = new LeaveRequest();
        validLeaveRequest.setEmpId("1");
        validLeaveRequest.setLeaveDate("2024-02-15");
        validLeaveRequest.setReason("Personal leave");
        validLeaveRequest.setStatus("PENDING");
    }

    @Test
    void testValidateValidLeaveRequest() {
        assertTrue(leaveService.validateLeaveRequest(validLeaveRequest));
    }

    @Test
    void testValidateLeaveRequestWithNullEmpId() {
        validLeaveRequest.setEmpId(null);
        assertFalse(leaveService.validateLeaveRequest(validLeaveRequest));
    }

    @Test
    void testValidateLeaveRequestWithEmptyEmpId() {
        validLeaveRequest.setEmpId("");
        assertFalse(leaveService.validateLeaveRequest(validLeaveRequest));
    }

    @Test
    void testValidateLeaveRequestWithNullLeaveDate() {
        validLeaveRequest.setLeaveDate(null);
        assertFalse(leaveService.validateLeaveRequest(validLeaveRequest));
    }

    @Test
    void testValidateLeaveRequestWithEmptyReason() {
        validLeaveRequest.setReason("");
        assertFalse(leaveService.validateLeaveRequest(validLeaveRequest));
    }

    @Test
    void testValidateNullLeaveRequest() {
        assertFalse(leaveService.validateLeaveRequest(null));
    }

    @Test
    void testLeaveRequestIdGeneration() {
        LeaveRequest request = new LeaveRequest("1", "2024-02-15", "Test reason");
        assertNotNull(request.getRequestId());
        assertTrue(request.getRequestId().startsWith("LR"));
        assertEquals("PENDING", request.getStatus());
    }
}