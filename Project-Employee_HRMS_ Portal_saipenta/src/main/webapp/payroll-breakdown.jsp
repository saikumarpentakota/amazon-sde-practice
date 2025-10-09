<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payroll Detailed Breakdown - HRMS Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel=\"stylesheet\">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
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
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>Payroll Detailed Breakdown</h2>
                    <a href="payroll.jsp" class="btn btn-outline-secondary">
                        <i class="fas fa-arrow-left"></i> Back to Payroll
                    </a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h5>Salary Breakdown Details</h5>
                    </div>
                    <div class="card-body" id="breakdownContent">
                        <div class="text-center">
                            <div class="spinner-border" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                            <p class="mt-2">Loading breakdown details...</p>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h5>Quick Actions</h5>
                    </div>
                    <div class="card-body">
                        <button class="btn btn-primary w-100 mb-2" onclick="printBreakdown()">
                            <i class="fas fa-print"></i> Print Breakdown
                        </button>
                        <button class="btn btn-success w-100 mb-2" onclick="downloadPDF()">
                            <i class="fas fa-download"></i> Download PDF
                        </button>
                        <button class="btn btn-info w-100" onclick="emailBreakdown()">
                            <i class="fas fa-envelope"></i> Email to Employee
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const urlParams = new URLSearchParams(window.location.search);
            const empId = urlParams.get('empId');
            const payPeriod = urlParams.get('payPeriod');
            
            if (empId && payPeriod) {
                loadDetailedBreakdown(empId, payPeriod);
            } else {
                document.getElementById('breakdownContent').innerHTML = 
                    '<div class="alert alert-warning">Missing employee ID or pay period parameters.</div>';
            }
        });

        function loadDetailedBreakdown(empId, payPeriod) {
            fetch(`/api/payroll/breakdown?empId=${empId}&payPeriod=${payPeriod}`)
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        document.getElementById('breakdownContent').innerHTML = 
                            `<div class="alert alert-danger">Error: ${data.error}</div>`;
                        return;
                    }
                    
                    const content = `
                        <div class="breakdown-details">
                            <div class="row mb-4">
                                <div class="col-6">
                                    <h6>Employee Information</h6>
                                    <p><strong>Employee ID:</strong> ${data.empId}</p>
                                    <p><strong>Pay Period:</strong> ${data.payPeriod}</p>
                                    <p><strong>Processed Date:</strong> ${new Date(data.processedDate).toLocaleDateString()}</p>
                                </div>
                                <div class="col-6">
                                    <h6>Summary</h6>
                                    <p><strong>Gross Salary:</strong> $${data.grossSalary}</p>
                                    <p><strong>Total Deductions:</strong> $${(parseFloat(data.deductions) + parseFloat(data.taxDeduction)).toFixed(2)}</p>
                                    <p><strong>Net Salary:</strong> <span class="text-success">$${data.netSalary}</span></p>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="card border-success">
                                        <div class="card-header bg-success text-white">
                                            <h6 class="mb-0">Earnings Breakdown</h6>
                                        </div>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <span>Basic Salary:</span>
                                                <strong>$${data.basicSalary}</strong>
                                            </div>
                                            <div class="d-flex justify-content-between">
                                                <span>Allowances:</span>
                                                <strong>$${data.allowances}</strong>
                                            </div>
                                            <hr>
                                            <div class="d-flex justify-content-between">
                                                <strong>Total Earnings:</strong>
                                                <strong class="text-success">$${data.grossSalary}</strong>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">
                                    <div class="card border-danger">
                                        <div class="card-header bg-danger text-white">
                                            <h6 class="mb-0">Deductions Breakdown</h6>
                                        </div>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <span>Other Deductions:</span>
                                                <strong>$${data.deductions}</strong>
                                            </div>
                                            <div class="d-flex justify-content-between">
                                                <span>Tax Deduction (15%):</span>
                                                <strong>$${data.taxDeduction}</strong>
                                            </div>
                                            <hr>
                                            <div class="d-flex justify-content-between">
                                                <strong>Total Deductions:</strong>
                                                <strong class="text-danger">$${(parseFloat(data.deductions) + parseFloat(data.taxDeduction)).toFixed(2)}</strong>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row mt-4">
                                <div class="col-12">
                                    <div class="card border-primary">
                                        <div class="card-header bg-primary text-white">
                                            <h6 class="mb-0">Net Salary Calculation</h6>
                                        </div>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between mb-2">
                                                <span>Gross Salary:</span>
                                                <span>$${data.grossSalary}</span>
                                            </div>
                                            <div class="d-flex justify-content-between mb-2">
                                                <span>(-) Total Deductions:</span>
                                                <span>$${(parseFloat(data.deductions) + parseFloat(data.taxDeduction)).toFixed(2)}</span>
                                            </div>
                                            <hr>
                                            <div class="d-flex justify-content-between">
                                                <h5><strong>Net Salary:</strong></h5>
                                                <h5><strong class="text-primary">$${data.netSalary}</strong></h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `;
                    
                    document.getElementById('breakdownContent').innerHTML = content;
                })
                .catch(error => {
                    document.getElementById('breakdownContent').innerHTML = 
                        '<div class="alert alert-danger">Error loading breakdown details.</div>';
                });
        }

        function printBreakdown() {
            window.print();
        }

        function downloadPDF() {
            alert('PDF download functionality would be implemented here');
        }

        function emailBreakdown() {
            alert('Email functionality would be implemented here');
        }
    </script>
</body>
</html>