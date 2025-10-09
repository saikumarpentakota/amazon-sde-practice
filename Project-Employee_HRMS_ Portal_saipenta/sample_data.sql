-- Sample Data for HRMS Database

USE hrms_db;

-- Insert Departments
INSERT INTO departments (dept_name, dept_head) VALUES
('Human Resources', 'Sarah Johnson'),
('Information Technology', 'Michael Chen'),
('Finance', 'Robert Davis'),
('Marketing', 'Emily Wilson'),
('Operations', 'David Brown');

-- Insert Employees
INSERT INTO employees (emp_code, first_name, last_name, email, phone, hire_date, dept_id, position, salary) VALUES
('EMP001', 'John', 'Smith', 'john.smith@company.com', '555-0101', '2023-01-15', 2, 'Software Developer', 75000.00),
('EMP002', 'Jane', 'Doe', 'jane.doe@company.com', '555-0102', '2023-02-01', 1, 'HR Manager', 65000.00),
('EMP003', 'Mike', 'Johnson', 'mike.johnson@company.com', '555-0103', '2023-01-20', 3, 'Financial Analyst', 60000.00),
('EMP004', 'Lisa', 'Anderson', 'lisa.anderson@company.com', '555-0104', '2023-03-10', 4, 'Marketing Specialist', 55000.00),
('EMP005', 'Tom', 'Wilson', 'tom.wilson@company.com', '555-0105', '2023-02-15', 5, 'Operations Manager', 70000.00),
('EMP006', 'Anna', 'Garcia', 'anna.garcia@company.com', '555-0106', '2023-04-01', 2, 'Senior Developer', 85000.00),
('EMP007', 'Chris', 'Martinez', 'chris.martinez@company.com', '555-0107', '2023-03-20', 1, 'HR Assistant', 45000.00),
('EMP008', 'Rachel', 'Taylor', 'rachel.taylor@company.com', '555-0108', '2023-05-15', 3, 'Accountant', 58000.00);

-- Insert Payroll Records
INSERT INTO payroll (emp_id, pay_period, basic_salary, allowances, deductions, gross_salary, tax_deduction, net_salary) VALUES
(1, '2024-01', 75000.00, 5000.00, 2000.00, 80000.00, 12000.00, 68000.00),
(2, '2024-01', 65000.00, 3000.00, 1500.00, 68000.00, 10200.00, 57800.00),
(3, '2024-01', 60000.00, 2500.00, 1200.00, 62500.00, 9375.00, 53125.00),
(4, '2024-01', 55000.00, 2000.00, 1000.00, 57000.00, 8550.00, 48450.00),
(5, '2024-01', 70000.00, 4000.00, 1800.00, 74000.00, 11100.00, 62900.00),
(6, '2024-01', 85000.00, 6000.00, 2500.00, 91000.00, 13650.00, 77350.00),
(7, '2024-01', 45000.00, 1500.00, 800.00, 46500.00, 6975.00, 39525.00),
(8, '2024-01', 58000.00, 2200.00, 1100.00, 60200.00, 9030.00, 51170.00);

-- Insert Attendance Records (Last 30 days sample)
INSERT INTO attendance (emp_id, attendance_date, status, check_in_time, check_out_time) VALUES
(1, '2024-01-01', 'PRESENT', '09:00:00', '17:30:00'),
(1, '2024-01-02', 'PRESENT', '09:15:00', '17:45:00'),
(1, '2024-01-03', 'LATE', '09:45:00', '17:30:00'),
(2, '2024-01-01', 'PRESENT', '08:45:00', '17:15:00'),
(2, '2024-01-02', 'PRESENT', '09:00:00', '17:30:00'),
(2, '2024-01-03', 'ABSENT', NULL, NULL),
(3, '2024-01-01', 'PRESENT', '09:10:00', '17:40:00'),
(3, '2024-01-02', 'PRESENT', '09:05:00', '17:35:00'),
(3, '2024-01-03', 'PRESENT', '08:55:00', '17:25:00'),
(4, '2024-01-01', 'PRESENT', '09:20:00', '17:50:00'),
(4, '2024-01-02', 'HALF_DAY', '09:00:00', '13:00:00'),
(4, '2024-01-03', 'PRESENT', '09:10:00', '17:40:00'),
(5, '2024-01-01', 'PRESENT', '08:30:00', '18:00:00'),
(5, '2024-01-02', 'PRESENT', '08:45:00', '17:45:00'),
(5, '2024-01-03', 'PRESENT', '09:00:00', '17:30:00');