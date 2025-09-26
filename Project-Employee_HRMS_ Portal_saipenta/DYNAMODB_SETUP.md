# DynamoDB Setup Guide

## Problem
DynamoDB tables were created before but now not working because:
1. DynamoDB Local is not running
2. Current app uses in-memory storage, not DynamoDB

## Solution

### Step 1: Start DynamoDB Local
```bash
# Download DynamoDB Local first (if not already done)
# https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

# Start DynamoDB Local
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 8000
```

### Step 2: Create Tables (if needed)
```bash
# Run table creator
java -cp target/classes org.example.util.DynamoDBTableCreator
```

### Step 3: Use DynamoDB Application
```bash
# Run DynamoDB-connected app
java -cp target/classes org.example.HRMSApplicationDynamoDB
```

## Applications Available

### 1. HRMSApplication.java
- **Storage**: In-memory (RAM only)
- **Persistence**: NO

### 2. HRMSApplicationDB.java  
- **Storage**: MySQL Database
- **Persistence**: YES

### 3. HRMSApplicationDynamoDB.java (NEW)
- **Storage**: DynamoDB Local
- **Persistence**: YES (while DynamoDB Local runs)

## Check DynamoDB Tables
```bash
# List tables
aws dynamodb list-tables --endpoint-url http://localhost:8000 --region us-east-1

# Scan employees table
aws dynamodb scan --table-name Employees --endpoint-url http://localhost:8000 --region us-east-1
```

## Recommendation
Use **HRMSApplicationDynamoDB.java** to work with your existing DynamoDB data.