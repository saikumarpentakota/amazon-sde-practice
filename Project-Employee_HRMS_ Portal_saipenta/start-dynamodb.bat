@echo off
echo Starting DynamoDB Local...
echo Make sure you have downloaded DynamoDB Local first!
echo.
echo Download from: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html
echo.
echo If already downloaded, run this command in DynamoDB Local folder:
echo java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 8000
echo.
pause