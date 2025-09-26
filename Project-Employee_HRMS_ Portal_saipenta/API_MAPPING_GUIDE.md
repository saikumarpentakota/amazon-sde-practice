# HRMS API Mapping Guide

## Understanding Servlet Mappings

Your HRMS application doesn't have a physical `/api` folder. Instead, it uses **servlet mappings** defined in `web.xml`.

## How It Works

### web.xml Configuration:
```xml
<servlet-mapping>
    <servlet-name>EmployeeController</servlet-name>
    <url-pattern>/api/employees/*</url-pattern>
</servlet-mapping>
```

### This means:
- **URL**: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees`
- **Maps to**: `EmployeeController.java` servlet class
- **No physical folder** `/api` exists

## Correct API Endpoints

### Employee Management:
- **GET**: `/api/employees` → `EmployeeController.doGet()`
- **POST**: `/api/employees` → `EmployeeController.doPost()`
- **PUT**: `/api/employees/{id}` → `EmployeeController.doPut()`
- **DELETE**: `/api/employees/{id}` → `EmployeeController.doDelete()`

### Payroll Management:
- **POST**: `/api/payroll/calculate` → `PayrollController.doPost()`
- **GET**: `/api/payroll/month/{YYYY-MM}` → `PayrollController.doGet()`

### Attendance Management:
- **POST**: `/api/attendance` → `AttendanceController.doPost()`
- **GET**: `/api/attendance/employee/{id}` → `AttendanceController.doGet()`

### Leave Management:
- **POST**: `/api/leave` → `LeaveController.doPost()`
- **GET**: `/api/leave/{id}` → `LeaveController.doGet()`

### Batch Processing:
- **POST**: `/api/batch/queue-all` → `BatchController.doPost()`
- **POST**: `/api/batch/process` → `BatchController.doPost()`
- **GET**: `/api/batch/queue-size` → `BatchController.doGet()`

## Testing APIs

### Using Postman:
```
Base URL: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT

Employee List: GET /api/employees
Create Employee: POST /api/employees
Calculate Payroll: POST /api/payroll/calculate?employeeId=1&month=2024-01&overtime=5000
```

### Using Browser:
```
http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees
```

## Troubleshooting

### If you get 404 errors:

1. **Check Tomcat deployment**:
   - WAR file in `webapps` folder
   - Tomcat started successfully
   - No deployment errors in logs

2. **Verify servlet mappings**:
   - Check `web.xml` has correct servlet mappings
   - Servlet classes exist in compiled WAR

3. **Test basic connectivity**:
   - Access Tomcat homepage: `http://localhost:8080/`
   - Access app homepage: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/`

4. **Check servlet classes**:
   - `EmployeeController.java` compiled correctly
   - All controller classes in WAR file

## HTML JavaScript Fix

The HTML now uses:
```javascript
const API_BASE = './api';
```

This creates relative URLs like:
- `./api/employees` 
- `./api/payroll/calculate`

Which resolve to:
- `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees`
- `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/payroll/calculate`

## Verification Steps

1. **Deploy WAR** to Tomcat
2. **Start Tomcat** 
3. **Access**: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/`
4. **Test API**: Click "Load All Employees" button
5. **Check Network tab** in browser DevTools for actual API calls