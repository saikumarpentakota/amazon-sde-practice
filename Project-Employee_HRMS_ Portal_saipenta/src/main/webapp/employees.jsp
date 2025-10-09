<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management - HRMS Portal</title>
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
                <a class="nav-link active" href="employees.jsp">Employees</a>
                <a class="nav-link" href="payroll.jsp">Payroll</a>
                <a class="nav-link" href="attendance.jsp">Attendance</a>
                <a class="nav-link" href="leave.jsp">Leave</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h2>Employee Management</h2>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#employeeModal">
                    <i class="fas fa-plus"></i> Add Employee
                </button>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped" id="employeeTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Code</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Position</th>
                                        <th>Salary</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="employeeTableBody">
                                    <tr>
                                        <td colspan="8" class="text-center">Loading employees...</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Employee Modal -->
    <div class="modal fade" id="employeeModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="employeeModalTitle">Add Employee</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form id="employeeForm">
                        <input type="hidden" id="empId" name="empId">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="empCode" class="form-label">Employee Code</label>
                                <input type="text" class="form-control" id="empCode" name="empCode" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName" class="form-label">First Name</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName" class="form-label">Last Name</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="phone" class="form-label">Phone</label>
                                <input type="tel" class="form-control" id="phone" name="phone">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="hireDate" class="form-label">Hire Date</label>
                                <input type="date" class="form-control" id="hireDate" name="hireDate" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="deptId" class="form-label">Department</label>
                                <select class="form-control" id="deptId" name="deptId" required>
                                    <option value="1">Human Resources</option>
                                    <option value="2">Information Technology</option>
                                    <option value="3">Finance</option>
                                    <option value="4">Marketing</option>
                                    <option value="5">Operations</option>
                                </select>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="position" class="form-label">Position</label>
                                <input type="text" class="form-control" id="position" name="position" required>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="salary" class="form-label">Salary</label>
                                <input type="number" class="form-control" id="salary" name="salary" step="0.01" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-control" id="status" name="status">
                                    <option value="ACTIVE">Active</option>
                                    <option value="INACTIVE">Inactive</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="saveEmployee()">Save Employee</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        let currentEmployeeId = null;

        document.addEventListener('DOMContentLoaded', function() {
            loadEmployees();
        });

        function loadEmployees() {
            console.log('Loading employees...');
            fetch('api/employees')
                .then(response => {
                    console.log('Response status:', response.status);
                    return response.json();
                })
                .then(data => {
                    console.log('Employee data:', data);
                    const tbody = document.getElementById('employeeTableBody');
                    tbody.innerHTML = '';
                    
                    if (!data || data.length === 0) {
                        tbody.innerHTML = '<tr><td colspan="8" class="text-center">No employees found</td></tr>';
                        return;
                    }
                    
                    data.forEach(employee => {
                        const row = tbody.insertRow();
                        row.innerHTML = `
                            <td>${employee.empId}</td>
                            <td>${employee.empCode}</td>
                            <td>${employee.firstName} ${employee.lastName}</td>
                            <td>${employee.email}</td>
                            <td>${employee.position}</td>
                            <td>$${employee.salary}</td>
                            <td><span class="badge bg-${employee.status === 'ACTIVE' ? 'success' : 'secondary'}">${employee.status}</span></td>
                            <td>
                                <button class="btn btn-sm btn-outline-primary" onclick="editEmployee(${employee.empId})">
                                    <i class="fas fa-edit"></i>
                                </button>
                                <button class="btn btn-sm btn-outline-danger" onclick="deleteEmployee(${employee.empId})">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </td>
                        `;
                    });
                })
                .catch(error => {
                    console.error('Error loading employees:', error);
                    document.getElementById('employeeTableBody').innerHTML = 
                        '<tr><td colspan="8" class="text-center text-danger">Error loading employees</td></tr>';
                });
        }

        function saveEmployee() {
            const form = document.getElementById('employeeForm');
            const formData = new FormData(form);
            const params = new URLSearchParams(formData);
            
            const method = currentEmployeeId ? 'PUT' : 'POST';
            const url = currentEmployeeId ? `api/employees/${currentEmployeeId}` : 'api/employees';
            
            fetch(url, {
                method: method,
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
                    alert('Employee saved successfully');
                    bootstrap.Modal.getInstance(document.getElementById('employeeModal')).hide();
                    loadEmployees();
                    resetForm();
                }
            })
            .catch(error => {
                alert('Error saving employee');
            });
        }

        function editEmployee(empId) {
            fetch(`api/employees/${empId}`)
                .then(response => response.json())
                .then(employee => {
                    currentEmployeeId = empId;
                    document.getElementById('employeeModalTitle').textContent = 'Edit Employee';
                    
                    document.getElementById('empId').value = employee.empId;
                    document.getElementById('empCode').value = employee.empCode;
                    document.getElementById('firstName').value = employee.firstName;
                    document.getElementById('lastName').value = employee.lastName;
                    document.getElementById('email').value = employee.email;
                    document.getElementById('phone').value = employee.phone || '';
                    document.getElementById('hireDate').value = employee.hireDate;
                    document.getElementById('deptId').value = employee.deptId;
                    document.getElementById('position').value = employee.position;
                    document.getElementById('salary').value = employee.salary;
                    document.getElementById('status').value = employee.status;
                    
                    new bootstrap.Modal(document.getElementById('employeeModal')).show();
                });
        }

        function deleteEmployee(empId) {
            if (confirm('Are you sure you want to delete this employee?')) {
                fetch(`api/employees/${empId}`, {
                    method: 'DELETE'
                })
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert('Error: ' + data.error);
                    } else {
                        alert('Employee deleted successfully');
                        loadEmployees();
                    }
                })
                .catch(error => {
                    alert('Error deleting employee');
                });
            }
        }

        function resetForm() {
            currentEmployeeId = null;
            document.getElementById('employeeForm').reset();
            document.getElementById('employeeModalTitle').textContent = 'Add Employee';
        }

        // Reset form when modal is hidden
        document.getElementById('employeeModal').addEventListener('hidden.bs.modal', resetForm);
    </script>
</body>
</html>