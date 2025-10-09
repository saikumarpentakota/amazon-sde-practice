<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leave Management - HRMS Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="../../index.jsp">
                <i class="fas fa-users"></i> HRMS Portal
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="employees.jsp">Employees</a>
                <a class="nav-link" href="payroll.jsp">Payroll</a>
                <a class="nav-link" href="attendance.jsp">Attendance</a>
                <a class="nav-link active" href="leave.jsp">Leave</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h2>Leave Management</h2>
                <div class="alert alert-info">
                    <i class="fas fa-info-circle"></i> Leave requests are stored in DynamoDB for scalability and high availability.
                </div>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Submit Leave Request</h5>
                    </div>
                    <div class="card-body">
                        <form id="leaveRequestForm">
                            <div class="mb-3">
                                <label for="empId" class="form-label">Employee ID</label>
                                <input type="text" class="form-control" id="empId" name="empId" required>
                            </div>
                            <div class="mb-3">
                                <label for="leaveDate" class="form-label">Leave Date</label>
                                <input type="date" class="form-control" id="leaveDate" name="leaveDate" required>
                            </div>
                            <div class="mb-3">
                                <label for="reason" class="form-label">Reason</label>
                                <textarea class="form-control" id="reason" name="reason" rows="3" 
                                         placeholder="Please provide reason for leave..." required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-paper-plane"></i> Submit Request
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Leave Request Actions</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label for="requestId" class="form-label">Request ID</label>
                            <input type="text" class="form-control" id="requestId" 
                                   placeholder="Enter request ID for approval/rejection">
                        </div>
                        <div class="d-grid gap-2">
                            <button class="btn btn-success" onclick="approveLeave()">
                                <i class="fas fa-check"></i> Approve Leave
                            </button>
                            <button class="btn btn-danger" onclick="rejectLeave()">
                                <i class="fas fa-times"></i> Reject Leave
                            </button>
                        </div>
                        <hr>
                        <div class="mb-3">
                            <label for="searchEmpId" class="form-label">Search Leave History</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="searchEmpId" 
                                       placeholder="Employee ID">
                                <button class="btn btn-outline-secondary" onclick="searchLeaveHistory()">
                                    <i class="fas fa-search"></i> Search
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5>Leave Requests</h5>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Request ID</th>
                                        <th>Employee ID</th>
                                        <th>Leave Date</th>
                                        <th>Reason</th>
                                        <th>Status</th>
                                        <th>Request Date</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="leaveTableBody">
                                    <tr>
                                        <td colspan="7" class="text-center">
                                            Search for an employee to view leave requests
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Leave Details Modal -->
    <div class="modal fade" id="leaveDetailsModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Leave Request Details</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body" id="leaveDetailsContent">
                    <!-- Leave details will be loaded here -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success me-2" id="modalApproveBtn" onclick="approveFromModal()">
                        <i class="fas fa-check"></i> Approve
                    </button>
                    <button type="button" class="btn btn-danger" id="modalRejectBtn" onclick="rejectFromModal()">
                        <i class="fas fa-times"></i> Reject
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        let currentRequestId = null;

        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('leaveRequestForm').addEventListener('submit', function(e) {
                e.preventDefault();
                submitLeaveRequest();
            });
        });

        function submitLeaveRequest() {
            const form = document.getElementById('leaveRequestForm');
            const formData = new FormData(form);
            
            fetch('/api/leave', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert('Error: ' + data.error);
                } else {
                    alert('Leave request submitted successfully!\nRequest ID: ' + data.requestId);
                    form.reset();
                }
            })
            .catch(error => {
                alert('Error submitting leave request. Please check if DynamoDB is running.');
                console.error('Error:', error);
            });
        }

        function approveLeave() {
            const requestId = document.getElementById('requestId').value;
            if (!requestId) {
                alert('Please enter a request ID');
                return;
            }
            
            updateLeaveStatus(requestId, 'approve');
        }

        function rejectLeave() {
            const requestId = document.getElementById('requestId').value;
            if (!requestId) {
                alert('Please enter a request ID');
                return;
            }
            
            updateLeaveStatus(requestId, 'reject');
        }

        function updateLeaveStatus(requestId, action) {
            fetch(`/api/leave/${action}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `requestId=${requestId}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert('Error: ' + data.error);
                } else {
                    alert(data.message);
                    document.getElementById('requestId').value = '';
                    // Refresh the current search if any
                    const searchEmpId = document.getElementById('searchEmpId').value;
                    if (searchEmpId) {
                        searchLeaveHistory();
                    }
                }
            })
            .catch(error => {
                alert('Error updating leave status');
                console.error('Error:', error);
            });
        }

        function searchLeaveHistory() {
            const empId = document.getElementById('searchEmpId').value;
            if (!empId) {
                alert('Please enter an employee ID');
                return;
            }
            
            fetch(`/api/leave?empId=${empId}`)
                .then(response => response.json())
                .then(data => {
                    displayLeaveRequests(data);
                })
                .catch(error => {
                    console.error('Error searching leave history:', error);
                    alert('Error searching leave history. Please check if DynamoDB is running.');
                });
        }

        function displayLeaveRequests(data) {
            const tbody = document.getElementById('leaveTableBody');
            tbody.innerHTML = '';
            
            if (data.length === 0) {
                tbody.innerHTML = '<tr><td colspan="7" class="text-center">No leave requests found</td></tr>';
                return;
            }
            
            data.forEach(request => {
                const row = tbody.insertRow();
                const statusBadge = getStatusBadge(request.status);
                const requestDate = request.requestDate ? new Date(request.requestDate).toLocaleDateString() : '-';
                
                row.innerHTML = `
                    <td><code>${request.requestId}</code></td>
                    <td>${request.empId}</td>
                    <td>${request.leaveDate}</td>
                    <td>${request.reason}</td>
                    <td>${statusBadge}</td>
                    <td>${requestDate}</td>
                    <td>
                        <button class="btn btn-sm btn-outline-primary" onclick="viewLeaveDetails('${request.requestId}')">
                            <i class="fas fa-eye"></i> View
                        </button>
                        ${request.status === 'PENDING' ? `
                            <button class="btn btn-sm btn-outline-success ms-1" onclick="quickApprove('${request.requestId}')">
                                <i class="fas fa-check"></i>
                            </button>
                            <button class="btn btn-sm btn-outline-danger ms-1" onclick="quickReject('${request.requestId}')">
                                <i class="fas fa-times"></i>
                            </button>
                        ` : ''}
                    </td>
                `;
            });
        }

        function getStatusBadge(status) {
            const badges = {
                'PENDING': '<span class="badge bg-warning">Pending</span>',
                'APPROVED': '<span class="badge bg-success">Approved</span>',
                'REJECTED': '<span class="badge bg-danger">Rejected</span>'
            };
            return badges[status] || '<span class="badge bg-secondary">' + status + '</span>';
        }

        function viewLeaveDetails(requestId) {
            fetch(`/api/leave/${requestId}`)
                .then(response => response.json())
                .then(request => {
                    if (request.error) {
                        alert('Error: ' + request.error);
                        return;
                    }
                    
                    currentRequestId = requestId;
                    const requestDate = request.requestDate ? new Date(request.requestDate).toLocaleString() : 'Not available';
                    
                    const content = `
                        <div class="row">
                            <div class="col-6"><strong>Request ID:</strong></div>
                            <div class="col-6"><code>${request.requestId}</code></div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-6"><strong>Employee ID:</strong></div>
                            <div class="col-6">${request.empId}</div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-6"><strong>Leave Date:</strong></div>
                            <div class="col-6">${request.leaveDate}</div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-6"><strong>Status:</strong></div>
                            <div class="col-6">${getStatusBadge(request.status)}</div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-6"><strong>Request Date:</strong></div>
                            <div class="col-6">${requestDate}</div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12"><strong>Reason:</strong></div>
                            <div class="col-12 mt-1">
                                <div class="border p-2 bg-light rounded">${request.reason}</div>
                            </div>
                        </div>
                    `;
                    
                    document.getElementById('leaveDetailsContent').innerHTML = content;
                    
                    // Show/hide action buttons based on status
                    const approveBtn = document.getElementById('modalApproveBtn');
                    const rejectBtn = document.getElementById('modalRejectBtn');
                    
                    if (request.status === 'PENDING') {
                        approveBtn.style.display = 'inline-block';
                        rejectBtn.style.display = 'inline-block';
                    } else {
                        approveBtn.style.display = 'none';
                        rejectBtn.style.display = 'none';
                    }
                    
                    new bootstrap.Modal(document.getElementById('leaveDetailsModal')).show();
                })
                .catch(error => {
                    alert('Error loading leave details');
                    console.error('Error:', error);
                });
        }

        function quickApprove(requestId) {
            if (confirm('Are you sure you want to approve this leave request?')) {
                updateLeaveStatus(requestId, 'approve');
            }
        }

        function quickReject(requestId) {
            if (confirm('Are you sure you want to reject this leave request?')) {
                updateLeaveStatus(requestId, 'reject');
            }
        }

        function approveFromModal() {
            if (currentRequestId) {
                updateLeaveStatus(currentRequestId, 'approve');
                bootstrap.Modal.getInstance(document.getElementById('leaveDetailsModal')).hide();
            }
        }

        function rejectFromModal() {
            if (currentRequestId) {
                updateLeaveStatus(currentRequestId, 'reject');
                bootstrap.Modal.getInstance(document.getElementById('leaveDetailsModal')).hide();
            }
        }
    </script>
</body>
</html>