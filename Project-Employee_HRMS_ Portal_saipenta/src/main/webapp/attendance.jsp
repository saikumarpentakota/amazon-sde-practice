<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance Management - HRMS Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <i class="fas fa-users"></i> HRMS Portal
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="employees.jsp">Employees</a>
                <a class="nav-link" href="payroll.jsp">Payroll</a>
                <a class="nav-link active" href="attendance.jsp">Attendance</a>
                <a class="nav-link" href="leave.jsp">Leave</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h2>Attendance Management</h2>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Mark Attendance</h5>
                    </div>
                    <div class="card-body">
                        <form id="attendanceForm">
                            <div class="mb-3">
                                <label for="empId" class="form-label">Employee ID</label>
                                <input type="number" class="form-control" id="empId" name="empId" required>
                            </div>
                            <div class="mb-3">
                                <label for="attendanceDate" class="form-label">Date</label>
                                <input type="date" class="form-control" id="attendanceDate" name="date" required>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-control" id="status" name="status" required>
                                    <option value="PRESENT">Present</option>
                                    <option value="ABSENT">Absent</option>
                                    <option value="LATE">Late</option>
                                    <option value="HALF_DAY">Half Day</option>
                                </select>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="checkInTime" class="form-label">Check In Time</label>
                                    <input type="time" class="form-control" id="checkInTime" name="checkInTime">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="checkOutTime" class="form-label">Check Out Time</label>
                                    <input type="time" class="form-control" id="checkOutTime" name="checkOutTime">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-success">
                                <i class="fas fa-check"></i> Mark Attendance
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Today's Summary</h5>
                    </div>
                    <div class="card-body">
                        <div class="row text-center">
                            <div class="col-3">
                                <div class="text-success">
                                    <i class="fas fa-check-circle fa-2x"></i>
                                    <p class="mt-2 mb-0"><strong id="presentCount">0</strong></p>
                                    <small>Present</small>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="text-danger">
                                    <i class="fas fa-times-circle fa-2x"></i>
                                    <p class="mt-2 mb-0"><strong id="absentCount">0</strong></p>
                                    <small>Absent</small>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="text-warning">
                                    <i class="fas fa-clock fa-2x"></i>
                                    <p class="mt-2 mb-0"><strong id="lateCount">0</strong></p>
                                    <small>Late</small>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="text-info">
                                    <i class="fas fa-user-clock fa-2x"></i>
                                    <p class="mt-2 mb-0"><strong id="halfDayCount">0</strong></p>
                                    <small>Half Day</small>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <button class="btn btn-outline-primary w-100" onclick="loadTodayAttendance()">
                            <i class="fas fa-refresh"></i> Refresh Summary
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5>Attendance Records</h5>
                        <div>
                            <input type="number" class="form-control d-inline-block me-2" id="filterEmpId" 
                                   placeholder="Employee ID" style="width: 120px;">
                            <input type="text" class="form-control d-inline-block me-2" id="filterMonth" 
                                   placeholder="Month (YYYY-MM)" style="width: 150px;">
                            <button class="btn btn-outline-secondary" onclick="filterAttendance()">
                                <i class="fas fa-filter"></i> Filter
                            </button>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Employee ID</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th>Check In</th>
                                        <th>Check Out</th>
                                        <th>Remarks</th>
                                    </tr>
                                </thead>
                                <tbody id="attendanceTableBody">
                                    <tr>
                                        <td colspan="6" class="text-center">No attendance records found</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Set today's date as default
            document.getElementById('attendanceDate').value = new Date().toISOString().split('T')[0];
            
            loadTodayAttendance();
            
            document.getElementById('attendanceForm').addEventListener('submit', function(e) {
                e.preventDefault();
                markAttendance();
            });
        });

        function markAttendance() {
            const form = document.getElementById('attendanceForm');
            const formData = new FormData(form);
            const params = new URLSearchParams(formData);
            
            fetch('/hrms-portal/api/attendance', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: params
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert('Error: ' + data.error);
                } else {
                    alert('Attendance marked successfully');
                    form.reset();
                    document.getElementById('attendanceDate').value = new Date().toISOString().split('T')[0];
                    loadTodayAttendance();
                }
            })
            .catch(error => {
                alert('Error marking attendance');
            });
        }

        function loadTodayAttendance() {
            fetch('/hrms-portal/api/attendance/today')
                .then(response => response.json())
                .then(data => {
                    // Count by status
                    const counts = {
                        PRESENT: 0,
                        ABSENT: 0,
                        LATE: 0,
                        HALF_DAY: 0
                    };
                    
                    data.forEach(record => {
                        if (counts.hasOwnProperty(record.status)) {
                            counts[record.status]++;
                        }
                    });
                    
                    document.getElementById('presentCount').textContent = counts.PRESENT;
                    document.getElementById('absentCount').textContent = counts.ABSENT;
                    document.getElementById('lateCount').textContent = counts.LATE;
                    document.getElementById('halfDayCount').textContent = counts.HALF_DAY;
                    
                    // Load today's records in table
                    displayAttendanceRecords(data);
                })
                .catch(error => {
                    console.error('Error loading today\'s attendance:', error);
                });
        }

        function filterAttendance() {
            const empId = document.getElementById('filterEmpId').value;
            const month = document.getElementById('filterMonth').value;
            
            let url = '/hrms-portal/api/attendance';
            const params = new URLSearchParams();
            
            if (empId && month) {
                url = '/hrms-portal/api/attendance/report';
                params.append('empId', empId);
                params.append('month', month);
            } else if (empId) {
                params.append('empId', empId);
            } else if (month) {
                alert('Please provide Employee ID for monthly report');
                return;
            } else {
                loadTodayAttendance();
                return;
            }
            
            fetch(url + '?' + params.toString())
                .then(response => response.json())
                .then(data => {
                    displayAttendanceRecords(data);
                })
                .catch(error => {
                    console.error('Error filtering attendance:', error);
                });
        }

        function displayAttendanceRecords(data) {
            const tbody = document.getElementById('attendanceTableBody');
            tbody.innerHTML = '';
            
            if (data.length === 0) {
                tbody.innerHTML = '<tr><td colspan="6" class="text-center">No attendance records found</td></tr>';
                return;
            }
            
            data.forEach(record => {
                const row = tbody.insertRow();
                const statusBadge = getStatusBadge(record.status);
                
                row.innerHTML = `
                    <td>${record.empId}</td>
                    <td>${record.attendanceDate}</td>
                    <td>${statusBadge}</td>
                    <td>${record.checkInTime || '-'}</td>
                    <td>${record.checkOutTime || '-'}</td>
                    <td>${record.remarks || '-'}</td>
                `;
            });
        }

        function getStatusBadge(status) {
            const badges = {
                'PRESENT': '<span class="badge bg-success">Present</span>',
                'ABSENT': '<span class="badge bg-danger">Absent</span>',
                'LATE': '<span class="badge bg-warning">Late</span>',
                'HALF_DAY': '<span class="badge bg-info">Half Day</span>'
            };
            return badges[status] || '<span class="badge bg-secondary">' + status + '</span>';
        }
    </script>
</body>
</html>