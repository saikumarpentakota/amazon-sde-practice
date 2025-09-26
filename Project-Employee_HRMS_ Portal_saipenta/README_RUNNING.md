# How to Run HRMS Application

## Why Main.java Just Prints and Exits

The `Main.java` is correct - it's a **web application** designed for Tomcat deployment, not standalone execution.

## Options to Run HRMS

### Option 1: Standalone Testing (New)
```bash
# Run the standalone version
java -cp target/classes org.example.HRMSApplication
```

### Option 2: Web Application (Production)
1. **Build WAR**: Build → Build Artifacts → HRMSPROJECT:war
2. **Deploy to Tomcat**: Copy WAR to `webapps/` folder
3. **Start Tomcat**: `startup.bat`
4. **Access**: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/

### Option 3: Test Individual Components
```bash
# Test data creation
java -cp target/classes org.example.util.TestDataCreator

# Create DynamoDB tables
java -cp target/classes org.example.util.DynamoDBTableCreator

# View data
java -cp target/classes org.example.util.DataViewer
```

## API Endpoints (When Deployed)
- `GET /api/employees` - List employees
- `POST /api/employees` - Create employee
- `POST /api/payroll/calculate` - Calculate payroll
- `GET /api/payroll/month/2024-01` - Monthly report

## Prerequisites for Web Version
- ✅ DynamoDB Local running (port 8000)
- ✅ MySQL running with HRMS database
- ✅ Tomcat server installed