# MySQL Tables for HRMS System

## Required Tables

### 1. **departments**
- **Purpose**: Store department information with allowance/deduction rates
- **Columns**: id, name, allowance_rate, deduction_rate, created_at
- **Sample Data**: IT, HR, Finance, Marketing departments

### 2. **employees** 
- **Purpose**: Store employee personal and job information
- **Columns**: id, name, email, department_id, salary, join_date, created_at, updated_at
- **Relationships**: Foreign key to departments table

### 3. **payroll**
- **Purpose**: Store calculated payroll records
- **Columns**: id, employee_id, month, basic_salary, allowances, deductions, overtime, total, created_at
- **Relationships**: Foreign key to employees table
- **Constraints**: Unique constraint on (employee_id, month)

### 4. **attendance**
- **Purpose**: Track daily attendance records
- **Columns**: id, employee_id, date, check_in, check_out, hours_worked, created_at
- **Relationships**: Foreign key to employees table
- **Constraints**: Unique constraint on (employee_id, date)

## Setup Instructions

### Step 1: Create Database
```sql
CREATE DATABASE hrms;
USE hrms;
```

### Step 2: Run Schema File
```bash
# In MySQL Workbench or command line
mysql -u root -p hrms < mysql-tables.sql
```

### Step 3: Verify Tables
```sql
SHOW TABLES;
-- Should show: attendance, departments, employees, payroll
```

### Step 4: Check Sample Data
```sql
SELECT * FROM departments;
SELECT * FROM employees;
```

## Table Relationships

```
departments (1) ──── (many) employees
employees (1) ──── (many) payroll
employees (1) ──── (many) attendance
```

## Key Features

### Foreign Key Constraints:
- `employees.department_id` → `departments.id`
- `payroll.employee_id` → `employees.id`
- `attendance.employee_id` → `employees.id`

### Unique Constraints:
- `employees.email` - Unique email addresses
- `payroll(employee_id, month)` - One payroll per employee per month
- `attendance(employee_id, date)` - One attendance record per employee per day

### Auto-Generated Fields:
- All tables have `id` as AUTO_INCREMENT PRIMARY KEY
- Timestamp fields for audit trail

## Sample Queries

### Get Employee with Department:
```sql
SELECT e.name, e.email, d.name as department 
FROM employees e 
JOIN departments d ON e.department_id = d.id;
```

### Monthly Payroll Report:
```sql
SELECT e.name, p.month, p.basic_salary, p.total 
FROM payroll p 
JOIN employees e ON p.employee_id = e.id 
WHERE p.month = '2024-01';
```

### Attendance Summary:
```sql
SELECT e.name, COUNT(a.id) as days_present, AVG(a.hours_worked) as avg_hours
FROM employees e 
LEFT JOIN attendance a ON e.id = a.employee_id 
GROUP BY e.id, e.name;
```