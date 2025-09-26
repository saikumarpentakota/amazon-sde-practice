# HRMS Data Storage Information

## Current Applications

### 1. HRMSApplication.java (In-Memory)
- **Storage**: RAM only
- **Persistence**: NO - data lost on exit
- **Location**: Java List objects
- **Use**: Testing/Demo only

### 2. HRMSApplicationDB.java (Database)
- **Storage**: MySQL Database
- **Persistence**: YES - data saved permanently
- **Location**: 
  - Employees: `hrms.employees` table
  - Payroll: `hrms.payroll` table
  - Attendance: `hrms.attendance` table

### 3. Web Application (Production)
- **Storage**: DynamoDB + MySQL
- **Persistence**: YES - cloud storage
- **Location**:
  - Employees: DynamoDB `Employees` table
  - Payroll: DynamoDB `Payroll` table
  - Attendance: MySQL `attendance` table

## Recommendation
**Use HRMSApplicationDB.java for permanent data storage**

## Check Your Data
- **MySQL**: Use SQL Workbench to view tables
- **DynamoDB**: Use AWS CLI or DynamoDB console
- **In-Memory**: Data only exists while program runs