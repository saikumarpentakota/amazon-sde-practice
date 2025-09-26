# HRMS Design Document

## Class Diagram

```
Employee
- id: Long
- name: String
- email: String
- departmentId: Long
- salary: BigDecimal
- joinDate: LocalDate

Department
- id: Long
- name: String
- description: String

Payroll
- id: Long
- employeeId: Long
- month: String
- basicSalary: BigDecimal
- overtime: BigDecimal
- total: BigDecimal
```

## DynamoDB Schema

### Employees Table
```
Table Name: Employees
Primary Key: id (String)
Attributes:
- id: String (UUID)
- name: String
- email: String
- departmentId: String
- salary: Number
- joinDate: String (ISO date)
```

### Payroll Table
```
Table Name: Payroll
Primary Key: id (String)
Global Secondary Index: employeeId-month-index
Attributes:
- id: String (UUID)
- employeeId: String
- month: String (YYYY-MM)
- basicSalary: Number
- overtime: Number
- total: Number
```

### Attendance Table (MySQL)
```sql
CREATE TABLE attendance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    check_in TIME NOT NULL,
    check_out TIME,
    hours_worked DECIMAL(4,2) DEFAULT 0
);
```