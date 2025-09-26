# HRMS Project - 10 Day Implementation

## Project Structure
- **Day 1**: Requirements documented in `REQUIREMENTS.md`
- **Day 2**: Design and schema in `DESIGN.md` and `schema.sql`
- **Day 3**: REST APIs for Employee CRUD operations
- **Day 4**: Payroll logic with SQL joins
- **Day 5**: Attendance feature with APIs
- **Day 6**: DynamoDB integration for leave requests
- **Day 7**: Queue data structure for batch processing
- **Day 8**: BDD tests with Cucumber
- **Day 9**: Jenkins automation for payroll jobs
- **Day 10**: HR portal demo deployment

## API Endpoints

### Employee CRUD
- GET `/api/employees` - List all employees
- GET `/api/employees/{id}` - Get employee by ID
- POST `/api/employees` - Create new employee
- PUT `/api/employees/{id}` - Update employee
- DELETE `/api/employees/{id}` - Delete employee

### Payroll Operations
- POST `/api/payroll/calculate?employeeId={id}&month={YYYY-MM}&overtime={amount}` - Calculate payroll
- GET `/api/payroll/employee/{id}` - Get employee payroll history
- GET `/api/payroll/month/{YYYY-MM}` - Get monthly payroll with employee details (SQL JOIN)

### Attendance Management
- POST `/api/attendance` - Record attendance
- GET `/api/attendance/employee/{id}` - Get employee attendance

### Leave Requests (DynamoDB)
- POST `/api/leave` - Submit leave request
- GET `/api/leave/{id}` - Get leave request

### Batch Processing
- POST `/api/batch/queue-all` - Add all employees to payroll queue
- POST `/api/batch/process?month={YYYY-MM}` - Process payroll batch
- GET `/api/batch/queue-size` - Get current queue size

## Setup
1. Create MySQL database: `CREATE DATABASE hrms;`
2. Run `schema.sql` to create tables
3. Update database credentials in `DatabaseConnection.java`
4. Build: `mvn clean package`
5. Deploy the generated WAR file to Tomcat or similar servlet container

## Sample Employee JSON
```json
{
  "name": "John Doe",
  "email": "john@company.com",
  "departmentId": 1,
  "salary": 50000.00,
  "joinDate": "2024-01-15"
}
```