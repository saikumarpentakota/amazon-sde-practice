# HRMS 404 Error - Deployment Fix Guide

## Problem
HTTP 404 - The requested resource [/HRMSPROJECT] is not available

## Root Causes & Solutions

### 1. WAR File Not Deployed
**Check**: Look in Tomcat `webapps` folder for your WAR file
**Solution**: 
```bash
# Build WAR file
mvn clean package

# Copy to Tomcat webapps
copy target\HRMSPROJECT-1.0-SNAPSHOT.war %CATALINA_HOME%\webapps\
```

### 2. Wrong URL Pattern
**Current URL**: `http://localhost:8080/HRMSPROJECT/api/employees`
**Correct URL**: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees`

### 3. Tomcat Not Started
**Check**: Is Tomcat running on port 8080?
**Solution**: 
```bash
%CATALINA_HOME%\bin\startup.bat
```

### 4. Port Conflict
**Check**: Another service using port 8080
**Solution**: Change Tomcat port in server.xml or stop conflicting service

## Quick Fix Steps

### Step 1: Verify Tomcat Status
```bash
# Check if Tomcat is running
netstat -an | findstr :8080
```

### Step 2: Check WAR Deployment
```bash
# List files in webapps
dir %CATALINA_HOME%\webapps\
```

### Step 3: Correct Postman URLs
Use these exact URLs:

#### Employee APIs:
- GET: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees`
- POST: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees`

#### Payroll APIs:
- POST: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/payroll/calculate?employeeId=1&month=2024-01&overtime=5000`

#### Test API:
- GET: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/`

### Step 4: Alternative - Use Standalone App
If WAR deployment fails, use standalone version:
```bash
java -cp target/classes org.example.HRMSApplicationDynamoDB
```

## Troubleshooting Commands

### Check Tomcat Logs:
```bash
type %CATALINA_HOME%\logs\catalina.out
```

### Verify WAR Contents:
```bash
jar -tf target\HRMSPROJECT-1.0-SNAPSHOT.war | findstr web.xml
```

### Test Tomcat Default Page:
```bash
# Should work if Tomcat is running
http://localhost:8080/
```

## Common URL Patterns

| Service | Correct URL |
|---------|-------------|
| Employee List | `GET http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees` |
| Create Employee | `POST http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees` |
| Calculate Payroll | `POST http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/payroll/calculate` |
| Record Attendance | `POST http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/attendance` |

## Sample Postman Request

### Create Employee:
- **Method**: POST
- **URL**: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees`
- **Headers**: `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "name": "John Doe",
  "email": "john@company.com",
  "departmentId": "1",
  "salary": 50000.00,
  "joinDate": "2024-01-15"
}
```