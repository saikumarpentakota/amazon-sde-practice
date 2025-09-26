# HRMS Deployment Guide

## Prerequisites
1. **Java 17+** installed
2. **MySQL Server** running on localhost:3306
3. **Apache Tomcat 10+** installed
4. **DynamoDB Local** downloaded

## Setup Steps

### 1. Database Setup
```sql
-- Run in MySQL
CREATE DATABASE hrms;
USE hrms;
-- Execute schema.sql file
```

### 2. Start DynamoDB Local
```bash
# Download DynamoDB Local first
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 8000
```

### 3. Build Project
```bash
# Using IntelliJ IDEA:
# 1. Right-click pom.xml → Maven → Reload project
# 2. Build → Build Artifacts → HRMSPROJECT:war → Build

# Or using command line (if Maven installed):
mvn clean package
```

### 4. Deploy to Tomcat
```bash
# Copy WAR file to Tomcat webapps
copy target\HRMSPROJECT-1.0-SNAPSHOT.war %CATALINA_HOME%\webapps\
```

### 5. Start Tomcat
```bash
%CATALINA_HOME%\bin\startup.bat
```

### 6. Create DynamoDB Tables
```bash
# Run table creator
java -cp target/classes org.example.util.DynamoDBTableCreator
```

### 7. Test APIs
```bash
# Test employee creation
curl -X POST http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees \
-H "Content-Type: application/json" \
-d '{"name":"John Doe","email":"john@company.com","departmentId":"1","salary":50000.00,"joinDate":"2024-01-15"}'
```

## API Endpoints
- **Employees**: `/api/employees/*`
- **Payroll**: `/api/payroll/*`
- **Attendance**: `/api/attendance/*`
- **Leave**: `/api/leave/*`
- **Batch**: `/api/batch/*`

## Configuration Files
- **Database**: `src/main/java/org/example/util/DatabaseConnection.java`
- **DynamoDB**: `src/main/java/org/example/util/DynamoDBConfig.java`
- **Web Config**: `src/main/webapp/WEB-INF/web.xml`

## Troubleshooting
1. **Port conflicts**: Change DynamoDB port in DynamoDBConfig.java
2. **Database connection**: Update credentials in DatabaseConnection.java
3. **Tomcat issues**: Check logs in %CATALINA_HOME%/logs/