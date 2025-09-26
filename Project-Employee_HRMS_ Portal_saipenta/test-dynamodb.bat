@echo off
echo Testing DynamoDB Local connection...

REM Check if DynamoDB Local is running
curl -s http://localhost:8000 > nul 2>&1
if %errorlevel% == 0 (
    echo ✅ DynamoDB Local is running on port 8000
) else (
    echo ❌ DynamoDB Local is NOT running
    echo Starting DynamoDB Local...
    start "DynamoDB Local" java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 8000
    timeout /t 3 > nul
)

REM List tables
echo.
echo Checking existing tables...
aws dynamodb list-tables --endpoint-url http://localhost:8000 --region us-east-1 --output table 2>nul
if %errorlevel% neq 0 (
    echo Note: AWS CLI not configured or not installed
)

pause