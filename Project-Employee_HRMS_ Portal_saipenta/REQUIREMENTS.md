# HRMS Project Requirements

## Employee CRUD Operations
- Create new employee records
- Read/View employee information
- Update employee details
- Delete employee records

## Payroll Management
- Calculate employee salaries
- Generate payroll reports
- Process monthly payroll
- Track salary history

## Attendance Management
- Record employee check-in/check-out
- Track working hours
- Calculate overtime
- Generate attendance reports

## Core Entities
- Employee: ID, Name, Email, Department, Salary, Join Date
- Department: ID, Name, Description
- Payroll: ID, Employee ID, Month, Basic Salary, Overtime, Total
- Attendance: ID, Employee ID, Date, Check-in, Check-out, Hours

## Technical Requirements
- Java Spring Boot REST APIs
- MySQL Database
- CRUD operations for all entities
- SQL joins for payroll computation