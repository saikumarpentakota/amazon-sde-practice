-- HRMS MySQL Database Schema
-- Run these commands in MySQL Workbench or command line

-- Create database
CREATE DATABASE IF NOT EXISTS hrms;
USE hrms;

-- 1. Departments table
CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    allowance_rate DECIMAL(5,4) DEFAULT 0.0000,
    deduction_rate DECIMAL(5,4) DEFAULT 0.0000,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Employees table
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department_id BIGINT NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    join_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- 3. Payroll table
CREATE TABLE payroll (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    month VARCHAR(7) NOT NULL,
    basic_salary DECIMAL(10,2) NOT NULL,
    allowances DECIMAL(10,2) DEFAULT 0.00,
    deductions DECIMAL(10,2) DEFAULT 0.00,
    overtime DECIMAL(10,2) DEFAULT 0.00,
    total DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    UNIQUE KEY unique_employee_month (employee_id, month)
);

-- 4. Attendance table
CREATE TABLE attendance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    date DATE NOT NULL,
    check_in TIME NOT NULL,
    check_out TIME,
    hours_worked DECIMAL(4,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    UNIQUE KEY unique_employee_date (employee_id, date)
);

-- Insert sample departments
INSERT INTO departments (name, allowance_rate, deduction_rate) VALUES
('IT', 0.2000, 0.1000),
('HR', 0.1500, 0.0800),
('Finance', 0.1800, 0.1200),
('Marketing', 0.1600, 0.0900);

-- Insert sample employees
INSERT INTO employees (name, email, department_id, salary, join_date) VALUES
('John Doe', 'john@company.com', 1, 50000.00, '2024-01-15'),
('Jane Smith', 'jane@company.com', 2, 45000.00, '2024-02-01'),
('Mike Johnson', 'mike@company.com', 3, 55000.00, '2024-01-10'),
('Sarah Wilson', 'sarah@company.com', 4, 48000.00, '2024-03-01');

-- Verify tables created
SHOW TABLES;

-- Check table structures
DESCRIBE employees;
DESCRIBE departments;
DESCRIBE payroll;
DESCRIBE attendance;