# HRMS Portal - Requirements Document

## Functional Requirements

### 1. Employee Management
- Create, Read, Update, Delete employee records
- Employee profile with personal and professional details
- Department assignment and management

### 2. Payroll Management
- Salary computation with gross, deductions, and net salary
- Payroll processing for individual and batch employees
- Salary slip generation
- Monthly payroll reports

### 3. Attendance Tracking
- Daily attendance marking (Present/Absent/Late)
- Monthly attendance summary
- Attendance reports by employee and department

### 4. Leave Management (DynamoDB)
- Leave request submission
- Leave approval/rejection workflow
- Leave balance tracking
- Leave history and reports

### 5. Batch Processing
- Queue-based batch payroll processing
- Bulk employee operations
- Status tracking for batch jobs

## Technical Requirements

### Backend
- Java EE with Servlets and JSP
- JDBC for MySQL database operations
- AWS SDK for DynamoDB integration
- Maven for build management

### Frontend
- JSP pages with Bootstrap CSS
- HTML forms for data entry
- JavaScript for client-side validation

### Database
- MySQL for core HRMS data
- DynamoDB for leave requests
- Proper foreign key relationships

### Testing
- JUnit 5 for unit testing

### DevOps
- Jenkins pipeline for automation
- Deployable WAR file for Tomcat

## API Endpoints

- `/api/employees` - Employee CRUD operations
- `/api/payroll` - Payroll management
- `/api/attendance` - Attendance tracking
- `/api/leave` - Leave management (DynamoDB)
- `/api/batch` - Batch processing operations

## Security Requirements
- Input validation and sanitization
- SQL injection prevention
- Secure database connections
- Error handling and logging