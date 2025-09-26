@echo off
echo Building HRMS Project...

echo Compiling Java classes...
javac -cp "src/main/java;target/classes;%USERPROFILE%\.m2\repository\*" -d target/classes src/main/java/org/example/**/*.java

echo Creating WAR structure...
mkdir target\HRMSPROJECT-1.0-SNAPSHOT
mkdir target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF
mkdir target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF\classes
mkdir target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF\lib

echo Copying files...
xcopy target\classes target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF\classes /E /I /Y
xcopy src\main\webapp target\HRMSPROJECT-1.0-SNAPSHOT /E /I /Y
copy src\main\resources\* target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF\classes\ 2>nul

echo Copying dependencies...
copy "%USERPROFILE%\.m2\repository\mysql\mysql-connector-java\8.0.33\*.jar" target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF\lib\ 2>nul
copy "%USERPROFILE%\.m2\repository\com\google\code\gson\gson\2.10.1\*.jar" target\HRMSPROJECT-1.0-SNAPSHOT\WEB-INF\lib\ 2>nul

echo Creating WAR file...
cd target\HRMSPROJECT-1.0-SNAPSHOT
jar -cvf ..\HRMSPROJECT-1.0-SNAPSHOT.war *
cd ..\..

echo Build complete! WAR file: target\HRMSPROJECT-1.0-SNAPSHOT.war
pause