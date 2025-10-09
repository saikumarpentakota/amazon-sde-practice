<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HRMS Portal - Dashboard</title>
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
                <a class="nav-link" href="attendance.jsp">Attendance</a>
                <a class="nav-link" href="leave.jsp">Leave</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h1 class="mb-4">HRMS Dashboard</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 mb-4">
                <div class="card bg-primary text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="card-title">Employees</h4>
                                <p class="card-text" id="employeeCount">Loading...</p>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-users fa-2x"></i>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="employees.jsp" class="text-white">View Details <i class="fas fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>

            <div class="col-md-3 mb-4">
                <div class="card bg-success text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="card-title">Payroll</h4>
                                <p class="card-text">Monthly Processing</p>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-dollar-sign fa-2x"></i>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="payroll.jsp" class="text-white">View Details <i class="fas fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>

            <div class="col-md-3 mb-4">
                <div class="card bg-info text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="card-title">Attendance</h4>
                                <p class="card-text" id="todayAttendance">Loading...</p>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-clock fa-2x"></i>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="attendance.jsp" class="text-white">View Details <i class="fas fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>

            <div class="col-md-3 mb-4">
                <div class="card bg-warning text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="card-title">Leave Requests</h4>
                                <p class="card-text">Pending Approval</p>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-calendar-alt fa-2x"></i>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="leave.jsp" class="text-white">View Details <i class="fas fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5>Quick Actions</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-3 mb-2">
                                <button class="btn btn-outline-primary w-100" onclick="location.href='employees.jsp'">
                                    <i class="fas fa-user-plus"></i> Add Employee
                                </button>
                            </div>
                            <div class="col-md-3 mb-2">
                                <button class="btn btn-outline-success w-100" onclick="processBatchPayroll()">
                                    <i class="fas fa-cogs"></i> Process Payroll
                                </button>
                            </div>
                            <div class="col-md-3 mb-2">
                                <button class="btn btn-outline-info w-100" onclick="location.href='attendance.jsp'">
                                    <i class="fas fa-check"></i> Mark Attendance
                                </button>
                            </div>
                            <div class="col-md-3 mb-2">
                                <button class="btn btn-outline-warning w-100" onclick="location.href='leave.jsp'">
                                    <i class="fas fa-calendar-plus"></i> Request Leave
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Load dashboard data
        document.addEventListener('DOMContentLoaded', function() {
            loadEmployeeCount();
            loadTodayAttendance();
        });

        function loadEmployeeCount() {
            fetch('/hrms-portal/api/employees')
                .then(response => response.json())
                .then(data => {
                    document.getElementById('employeeCount').textContent = data.length + ' Active';
                })
                .catch(error => {
                    document.getElementById('employeeCount').textContent = 'Error loading';
                });
        }

        function loadTodayAttendance() {
            fetch('/hrms-portal/api/attendance/today')
                .then(response => response.json())
                .then(data => {
                    document.getElementById('todayAttendance').textContent = data.length + ' Present';
                })
                .catch(error => {
                    document.getElementById('todayAttendance').textContent = 'Error loading';
                });
        }

        function processBatchPayroll() {
            const payPeriod = prompt('Enter pay period (YYYY-MM):');
            if (payPeriod) {
                fetch('/hrms-portal/api/batch/process', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'payPeriod=' + encodeURIComponent(payPeriod)
                })
                .then(response => response.json())
                .then(data => {
                    alert(data.message || 'Batch processing completed');
                })
                .catch(error => {
                    alert('Error processing batch payroll');
                });
            }
        }
    </script>
</body>
</html>