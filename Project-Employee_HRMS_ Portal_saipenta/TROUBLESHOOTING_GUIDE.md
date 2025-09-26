# HRMS Employee Loading Troubleshooting Guide

## Problem: Cannot load employees at http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/

## Step-by-Step Diagnosis

### 1. Check Basic Connectivity
- **Access Tomcat**: http://localhost:8080/ (should show Tomcat homepage)
- **Access App**: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/ (should show HRMS portal)

### 2. Test API Endpoint Directly
Open browser and go to:
```
http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees
```

**Expected Results:**
- **200 OK**: JSON array of employees (success)
- **404 Not Found**: Servlet mapping issue
- **500 Internal Error**: Database/DynamoDB connection issue
- **Connection refused**: Tomcat not running

### 3. Check Tomcat Deployment
```bash
# Check if WAR is deployed
dir C:\apache-tomcat-10.1.x\webapps\
# Should see: HRMSPROJECT-1.0-SNAPSHOT.war and HRMSPROJECT-1.0-SNAPSHOT folder

# Check Tomcat logs
type C:\apache-tomcat-10.1.x\logs\catalina.out
```

### 4. Verify Prerequisites

#### DynamoDB Local:
```bash
# Start DynamoDB Local
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 8000

# Test DynamoDB
aws dynamodb list-tables --endpoint-url http://localhost:8000 --region us-east-1
```

#### MySQL Database:
```sql
-- Check database exists
SHOW DATABASES;
USE hrms;
SHOW TABLES;
SELECT * FROM employees;
```

### 5. Common Issues & Solutions

#### Issue 1: 404 Not Found
**Cause**: Servlet not mapped correctly
**Solution**: 
- Check `web.xml` has correct servlet mappings
- Verify WAR contains compiled servlet classes
- Rebuild and redeploy WAR

#### Issue 2: 500 Internal Server Error
**Cause**: Database connection failure
**Solution**:
- Start DynamoDB Local on port 8000
- Check MySQL credentials in `DatabaseConnection.java`
- Verify database tables exist

#### Issue 3: Empty Response []
**Cause**: No data in database
**Solution**:
- Run `mysql-tables.sql` to create sample data
- Use `TestDataCreator.java` to add test employees
- Check DynamoDB tables have data

#### Issue 4: CORS/Network Error
**Cause**: Browser security or network issue
**Solution**:
- Use same origin (access from deployed app, not file://)
- Check browser console for detailed errors
- Disable browser security for testing

### 6. Debug Steps in HTML

The updated HTML includes:
- **Test API Connection** button for manual testing
- **Console logging** for detailed debugging
- **Better error messages** with specific status codes
- **Auto-test on page load** to identify issues immediately

### 7. Manual Testing Commands

#### Test Employee API:
```bash
# GET employees
curl http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees

# POST new employee
curl -X POST http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees \
-H "Content-Type: application/json" \
-d '{"name":"Test User","email":"test@test.com","departmentId":"1","salary":50000,"joinDate":"2024-01-01"}'
```

### 8. Quick Fix Checklist

- [ ] Tomcat running on port 8080
- [ ] WAR file deployed in webapps
- [ ] DynamoDB Local running on port 8000
- [ ] MySQL running with hrms database
- [ ] No firewall blocking ports
- [ ] Browser accessing correct URL
- [ ] Check browser console for JavaScript errors
- [ ] Check Tomcat logs for deployment errors

### 9. Alternative Testing

If web interface fails, test with standalone apps:
```bash
# Test DynamoDB connection
java -cp target/classes org.example.HRMSApplicationDynamoDB

# Test MySQL connection  
java -cp target/classes org.example.HRMSApplicationDB
```

### 10. Expected Browser Console Output

When working correctly:
```
Page loaded, testing API...
Testing API at: ./api/employees
Response status: 200
Response URL: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees
Employee data received: [{"id":"emp-1","name":"John Doe",...}]
```

When failing:
```
API test failed: TypeError: Failed to fetch
Cannot connect to API: Failed to fetch
```