#!/bin/bash

# Monthly Payroll Automation Script
MONTH=$(date +%Y-%m)
BASE_URL="http://localhost:8080/HRMSPROJECT/api"

echo "Starting payroll processing for month: $MONTH"

# Add all employees to queue
curl -X POST "$BASE_URL/batch/queue-all"

# Process batch
curl -X POST "$BASE_URL/batch/process?month=$MONTH"

echo "Payroll processing completed for month: $MONTH"