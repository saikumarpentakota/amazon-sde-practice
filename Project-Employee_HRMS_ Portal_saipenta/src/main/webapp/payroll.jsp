<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payroll Management - HRMS Portal</title>
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
                <a class="nav-link active" href="payroll.jsp">Payroll</a>
                <a class="nav-link" href="attendance.jsp">Attendance</a>
                <a class="nav-link" href="leave.jsp">Leave</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h2>Payroll Management</h2>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Process Individual Payroll</h5>
                    </div>
                    <div class="card-body">
                        <form id="individualPayrollForm">
                            <div class="mb-3">
                                <label for="empId" class="form-label">Employee ID</label>
                                <input type="number" class="form-control" id="empId" name="empId" required>
                            </div>
                            <div class="mb-3">
                                <label for="payPeriod" class="form-label">Pay Period (YYYY-MM)</label>
                                <input type="text" class="form-control" id="payPeriod" name="payPeriod" 
                                       placeholder="2024-01" pattern="[0-9]{4}-[0-9]{2}" required>
                            </div>
                            <button type="submit" class="btn btn-success">
                                <i class="fas fa-cogs"></i> Process Payroll
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Batch Processing Queue</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label for="batchEmpId" class="form-label">Add Employee to Queue</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="batchEmpId" placeholder="Employee ID">
                                <button class="btn btn-outline-primary" onclick="addToQueue()">
                                    <i class="fas fa-plus"></i> Add
                                </button>
                            </div>
                        </div>
                        <div class="mb-3">
                            <p>Queue Size: <span id="queueSize" class="badge bg-info">0</span></p>
                        </div>
                        <div class="mb-3">
                            <label for="batchPayPeriod" class="form-label">Pay Period</label>
                            <input type="text" class="form-control" id="batchPayPeriod" 
                                   placeholder="2024-01" pattern="[0-9]{4}-[0-9]{2}">
                        </div>
                        <button class="btn btn-warning" onclick="processBatch()">
                            <i class="fas fa-play"></i> Process Batch
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5>Payroll Records</h5>
                        <div>
                            <input type="text" class="form-control d-inline-block" id="filterPeriod" 
                                   placeholder="Filter by period (YYYY-MM)" style="width: 200px;">
                            <button class="btn btn-outline-secondary ms-2" onclick="filterPayroll()">
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
                                        <th>Pay Period</th>
                                        <th>Basic Salary</th>
                                        <th>Allowances</th>
                                        <th>Deductions</th>
                                        <th>Gross Salary</th>
                                        <th>Tax</th>
                                        <th>Net Salary</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="payrollTableBody">
                                    <tr>
                                        <td colspan="9" class="text-center">No payroll records found</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Salary Slip Modal -->
    <div class="modal fade" id="salarySlipModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Salary Slip</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body" id="salarySlipContent">
                    <!-- Salary slip content will be loaded here -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="printSalarySlip()">
                        <i class="fas fa-print"></i> Print
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            loadQueueStatus();
            
            document.getElementById('individualPayrollForm').addEventListener('submit', function(e) {
                e.preventDefault();
                processIndividualPayroll();
            });
        });

        function processIndividualPayroll() {
            const empId = document.getElementById('empId').value;
            const payPeriod = document.getElementById('payPeriod').value;
            
            fetch('/api/payroll/process', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `empId=${empId}&payPeriod=${payPeriod}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert('Error: ' + data.error);
                } else {
                    alert('Payroll processed successfully');
                    document.getElementById('individualPayrollForm').reset();
                    filterPayroll(); // Refresh the table
                }
            })
            .catch(error => {
                alert('Error processing payroll');
            });
        }

        function addToQueue() {
            const empId = document.getElementById('batchEmpId').value;
            if (!empId) {
                alert('Please enter an employee ID');
                return;
            }
            
            fetch('/api/batch/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `empId=${empId}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert('Error: ' + data.error);
                } else {
                    alert('Employee added to queue');
                    document.getElementById('batchEmpId').value = '';
                    loadQueueStatus();
                }
            })
            .catch(error => {
                alert('Error adding to queue');
            });
        }

        function processBatch() {
            const payPeriod = document.getElementById('batchPayPeriod').value;
            if (!payPeriod) {
                alert('Please enter a pay period');
                return;
            }
            
            fetch('/api/batch/process', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `payPeriod=${payPeriod}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert('Error: ' + data.error);
                } else {
                    alert(`Batch processing completed. Processed ${data.processedCount} employees.`);
                    document.getElementById('batchPayPeriod').value = '';
                    loadQueueStatus();
                    filterPayroll();
                }
            })
            .catch(error => {
                alert('Error processing batch');
            });
        }

        function loadQueueStatus() {
            fetch('/api/batch/status')
                .then(response => response.json())
                .then(data => {
                    document.getElementById('queueSize').textContent = data.queueSize;
                })
                .catch(error => {
                    console.error('Error loading queue status:', error);
                });
        }

        function filterPayroll() {
            const payPeriod = document.getElementById('filterPeriod').value;
            if (!payPeriod) {
                alert('Please enter a pay period to filter');
                return;
            }
            
            fetch(`/api/payroll?payPeriod=${payPeriod}`)
                .then(response => response.json())
                .then(data => {
                    const tbody = document.getElementById('payrollTableBody');
                    tbody.innerHTML = '';
                    
                    if (data.length === 0) {
                        tbody.innerHTML = '<tr><td colspan="9" class="text-center">No payroll records found</td></tr>';
                        return;
                    }
                    
                    data.forEach(payroll => {
                        const row = tbody.insertRow();
                        row.innerHTML = `
                            <td>${payroll.empId}</td>
                            <td>${payroll.payPeriod}</td>
                            <td>$${payroll.basicSalary}</td>
                            <td>$${payroll.allowances}</td>
                            <td>$${payroll.deductions}</td>
                            <td>$${payroll.grossSalary}</td>
                            <td>$${payroll.taxDeduction}</td>
                            <td><strong>$${payroll.netSalary}</strong></td>
                            <td>
                                <button class="btn btn-sm btn-outline-primary" 
                                        onclick="viewSalarySlip(${payroll.empId}, '${payroll.payPeriod}')">
                                    <i class="fas fa-file-alt"></i> View Slip
                                </button>
                                <button class="btn btn-sm btn-outline-success ms-1" 
                                        onclick="redirectToDetailedBreakdown(${payroll.empId}, '${payroll.payPeriod}')">
                                    <i class="fas fa-chart-line"></i> Detailed Breakdown
                                </button>
                            </td>
                        `;
                    });
                })
                .catch(error => {
                    console.error('Error loading payroll:', error);
                });
        }

        function viewSalarySlip(empId, payPeriod) {
            fetch(`/api/payroll/slip?empId=${empId}&payPeriod=${payPeriod}`)
                .then(response => response.json())
                .then(payroll => {
                    if (payroll.error) {
                        alert('Error: ' + payroll.error);
                        return;
                    }
                    
                    const content = `
                        <div class="salary-slip">
                            <div class="text-center mb-4">
                                <h4>SALARY SLIP</h4>
                                <p>Pay Period: ${payroll.payPeriod}</p>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <p><strong>Employee ID:</strong> ${payroll.empId}</p>
                                    <p><strong>Pay Period:</strong> ${payroll.payPeriod}</p>
                                </div>
                                <div class="col-6">
                                    <p><strong>Processed Date:</strong> ${new Date(payroll.processedDate).toLocaleDateString()}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-6">
                                    <h6>Earnings</h6>
                                    <p>Basic Salary: $${payroll.basicSalary}</p>
                                    <p>Allowances: $${payroll.allowances}</p>
                                    <hr>
                                    <p><strong>Gross Salary: $${payroll.grossSalary}</strong></p>
                                </div>
                                <div class="col-6">
                                    <h6>Deductions</h6>
                                    <p>Other Deductions: $${payroll.deductions}</p>
                                    <p>Tax Deduction: $${payroll.taxDeduction}</p>
                                    <hr>
                                    <p><strong>Total Deductions: $${(parseFloat(payroll.deductions) + parseFloat(payroll.taxDeduction)).toFixed(2)}</strong></p>
                                </div>
                            </div>
                            <hr>
                            <div class="text-center">
                                <h5><strong>Net Salary: $${payroll.netSalary}</strong></h5>
                            </div>
                        </div>
                    `;
                    
                    document.getElementById('salarySlipContent').innerHTML = content;
                    new bootstrap.Modal(document.getElementById('salarySlipModal')).show();
                })
                .catch(error => {
                    alert('Error loading salary slip');
                });
        }

        function printSalarySlip() {
            const content = document.getElementById('salarySlipContent').innerHTML;
            const printWindow = window.open('', '_blank');
            printWindow.document.write(`
                <html>
                    <head>
                        <title>Salary Slip</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
                    </head>
                    <body class="container mt-4">
                        ${content}
                    </body>
                </html>
            `);
            printWindow.document.close();
            printWindow.print();
        }

        function redirectToDetailedBreakdown(empId, payPeriod) {
            // Automatically redirect to detailed breakdown page
            window.location.href = `payroll-breakdown.jsp?empId=${empId}&payPeriod=${payPeriod}`;
        }
    </script>
</body>
</html>