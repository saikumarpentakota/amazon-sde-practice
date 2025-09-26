# Tomcat WAR Deployment Guide

## Step 1: Build WAR File

### Using IntelliJ IDEA:
1. **Open Maven Tool Window**: View → Tool Windows → Maven
2. **Clean Project**: HRMSPROJECT → Lifecycle → clean (double-click)
3. **Package Project**: HRMSPROJECT → Lifecycle → package (double-click)
4. **Check Output**: WAR file created at `target/HRMSPROJECT-1.0-SNAPSHOT.war`

### Alternative - Command Line:
```bash
# If Maven is installed
mvn clean package
```

## Step 2: Install/Setup Tomcat

### Download Tomcat:
1. Go to https://tomcat.apache.org/download-10.cgi
2. Download "Core" → "zip" (Windows)
3. Extract to `C:\apache-tomcat-10.1.x`

### Set Environment Variables:
```bash
# Add to System Environment Variables
CATALINA_HOME = C:\apache-tomcat-10.1.x
JAVA_HOME = C:\Program Files\Java\jdk-17
```

## Step 3: Deploy WAR File

### Method 1: Copy to webapps (Recommended)
```bash
# Copy WAR file to Tomcat webapps directory
copy target\HRMSPROJECT-1.0-SNAPSHOT.war C:\apache-tomcat-10.1.x\webapps\
```

### Method 2: Tomcat Manager (Alternative)
1. Start Tomcat first
2. Go to http://localhost:8080/manager
3. Upload WAR file through web interface

## Step 4: Start Tomcat

### Windows:
```bash
# Navigate to Tomcat bin directory
cd C:\apache-tomcat-10.1.x\bin

# Start Tomcat
startup.bat
```

### Verify Tomcat is Running:
```bash
# Check if port 8080 is in use
netstat -an | findstr :8080
```

## Step 5: Access Your Application

### URLs to Test:
- **Tomcat Homepage**: http://localhost:8080/
- **HRMS Portal**: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/
- **Employee API**: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/api/employees

## Step 6: Verify Deployment

### Check Tomcat Logs:
```bash
# View deployment logs
type C:\apache-tomcat-10.1.x\logs\catalina.out
```

### Check webapps Directory:
```bash
# Should see your WAR file and extracted folder
dir C:\apache-tomcat-10.1.x\webapps\
```

## Troubleshooting

### Common Issues:

#### 1. Port 8080 Already in Use:
```bash
# Find process using port 8080
netstat -ano | findstr :8080

# Kill the process (replace PID)
taskkill /PID <process_id> /F
```

#### 2. WAR Not Deploying:
- Check Tomcat logs for errors
- Ensure WAR file is not corrupted
- Verify Java version compatibility

#### 3. 404 Errors:
- Use correct URL: `http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/`
- Check if WAR extracted properly in webapps

#### 4. Database Connection Issues:
- Ensure MySQL is running
- Check database credentials in `DatabaseConnection.java`
- Start DynamoDB Local on port 8000

## Quick Deployment Script

Create `deploy.bat`:
```batch
@echo off
echo Building WAR file...
mvn clean package

echo Stopping Tomcat...
C:\apache-tomcat-10.1.x\bin\shutdown.bat

echo Deploying WAR...
copy target\HRMSPROJECT-1.0-SNAPSHOT.war C:\apache-tomcat-10.1.x\webapps\

echo Starting Tomcat...
C:\apache-tomcat-10.1.x\bin\startup.bat

echo Deployment complete!
echo Access: http://localhost:8080/HRMSPROJECT-1.0-SNAPSHOT/
pause
```

## Verification Checklist

- [ ] WAR file built successfully
- [ ] Tomcat installed and configured
- [ ] WAR file copied to webapps
- [ ] Tomcat started without errors
- [ ] Application accessible at correct URL
- [ ] Database connections working
- [ ] APIs responding correctly