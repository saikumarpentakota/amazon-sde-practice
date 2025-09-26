@echo off
echo Setting up Git repository for HRMS Project...

git init
git remote add origin https://github.com/saikumarpentakota/amazon-sde-practice.git

echo Adding files...
git add .
git commit -m "Add HRMS Project - 10 Day Implementation with MySQL & DynamoDB"

echo Pushing to GitHub...
git branch -M main
git push -u origin main

echo Done! Project uploaded to GitHub.
pause