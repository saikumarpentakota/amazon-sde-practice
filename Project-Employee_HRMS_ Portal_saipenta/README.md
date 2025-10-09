# HRMS Portal

Human Resource Management System built with Java EE, JSP, Servlets, JDBC, and Maven.

## Features

- Employee Management with CRUD operations
- Payroll processing with batch operations
- Attendance tracking and reporting
- Leave management with DynamoDB integration
- Modern responsive web interface

## Technology Stack

- **Backend**: Java 11, Servlets, JSP, JDBC
- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap 5
- **Database**: MySQL (primary), AWS DynamoDB (leave requests)
- **Build Tool**: Maven
- **Server**: Apache Tomcat 9+
- **Testing**: JUnit 5
- **CI/CD**: Jenkins

## Project Structure

```
src/
├── main/
│   ├── java/com/hrms/
│   │   ├── model/          # POJO classes
│   │   ├── dao/            # Database access layer
│   │   ├── service/        # Business logic layer
│   │   ├── servlet/        # HTTP controllers
│   │   └── util/           # Utility classes
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── jsp/        # JSP view pages
│       │   └── web.xml     # Web configuration
│       └── index.jsp       # Main dashboard
└── test/
    ├── java/               # Unit and BDD tests
    └── resources/          # Test resources
```

## Setup Instructions

### Prerequisites

1. **Java 11** or higher
2. **Apache Tomcat 9** or higher
3. **MySQL 8.0** or higher
4. **Maven 3.6** or higher
5. **AWS CLI** (for DynamoDB, optional for local testing)

### Database Setup

1. **MySQL Setup**:
   ```sql
   # Create database and user
   CREATE DATABASE hrms_db;
   CREATE USER 'hrms_user'@'localhost' IDENTIFIED BY 'hrms_password';
   GRANT ALL PRIVILEGES ON hrms_db.* TO 'hrms_user'@'localhost';
   FLUSH PRIVILEGES;
   
   # Run schema and sample data
   mysql -u hrms_user -p hrms_db < schema.sql
   mysql -u hrms_user -p hrms_db < sample_data.sql
   ```

2. **Update Database Configuration**:
   Edit `src/main/java/com/hrms/util/DatabaseConnection.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/hrms_db";
   private static final String USERNAME = "hrms_user";
   private static final String PASSWORD = "hrms_password";
   ```

3. **DynamoDB Setup** (Optional for local testing):
   ```bash
   # Install DynamoDB Local
   java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
   
   # Create LeaveRequests table
   aws dynamodb create-table \
     --table-name LeaveRequests \
     --attribute-definitions AttributeName=requestId,AttributeType=S \
     --key-schema AttributeName=requestId,KeyType=HASH \
     --billing-mode PAY_PER_REQUEST \
     --endpoint-url http://localhost:8000
   ```

### Build and Deploy

1. **Clone and Build**:
   ```bash
   git clone <repository-url>
   cd HRMSPORTAL
   mvn clean package
   ```

2. **Deploy to Tomcat**:
   ```bash
   # Copy WAR file to Tomcat webapps directory
   cp target/hrms-portal.war $TOMCAT_HOME/webapps/
   
   # Start Tomcat
   $TOMCAT_HOME/bin/startup.sh
   ```

3. **Access Application**:
   - Main Dashboard: http://localhost:8080/hrms-portal/
   - API Base URL: http://localhost:8080/hrms-portal/api/

## API Endpoints

### Employee Management
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `POST /api/employees` - Create new employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

### Payroll Management
- `POST /api/payroll/process` - Process individual payroll
- `GET /api/payroll/slip` - Get salary slip
- `GET /api/payroll?payPeriod={period}` - Get payroll by period

### Attendance Management
- `POST /api/attendance` - Mark attendance
- `GET /api/attendance/today` - Get today's attendance
- `GET /api/attendance?empId={id}` - Get employee attendance
- `GET /api/attendance/report` - Get monthly report

### Leave Management (DynamoDB)
- `POST /api/leave` - Submit leave request
- `GET /api/leave?empId={id}` - Get leave history
- `POST /api/leave/approve` - Approve leave request
- `POST /api/leave/reject` - Reject leave request

### Batch Processing
- `POST /api/batch/add` - Add employee to payroll queue
- `POST /api/batch/process` - Process batch payroll
- `GET /api/batch/status` - Get queue status

## Testing

### Unit Tests
```bash
mvn test
```

### API Testing
Import `Postman_collection.json` into Postman for comprehensive API testing.

## Jenkins CI/CD

The project includes a `Jenkinsfile` for automated deployment:

1. **Setup Jenkins Pipeline**:
   - Create new Pipeline job
   - Point to repository with Jenkinsfile
   - Configure Maven and JDK tools

2. **Automated Features**:
   - Build and test on every commit
   - Deploy to Tomcat automatically
   - Weekly payroll processing trigger
   - Email notifications

## Architecture Highlights

### MVC Pattern
- **Model**: POJO classes in `com.hrms.model`
- **View**: JSP pages with Bootstrap styling
- **Controller**: Servlets handling HTTP requests

### Data Access Layer
- **DAO Pattern**: Separate DAO classes for each entity
- **Connection Pooling**: Centralized database connection management
- **Hybrid Storage**: MySQL for core data, DynamoDB for leave requests

### Business Logic
- **Service Layer**: Business rules and validation
- **Queue Processing**: LinkedList-based batch processing
- **Salary Calculation**: Automated allowances, deductions, and tax calculation

### Security Features
- **Input Validation**: Server-side validation for all inputs
- **SQL Injection Prevention**: Prepared statements throughout
- **Error Handling**: Comprehensive error handling and logging

## Deployment Notes

### Production Considerations
1. **Database**: Use connection pooling (HikariCP recommended)
2. **Security**: Enable HTTPS and implement authentication
3. **Monitoring**: Add application monitoring and logging
4. **Backup**: Implement database backup strategy

### Performance Optimization
1. **Caching**: Implement Redis for session management
2. **Database**: Add proper indexes and query optimization
3. **Static Resources**: Use CDN for Bootstrap and other assets

## Troubleshooting

### Common Issues

1. **Database Connection Failed**:
   - Verify MySQL is running
   - Check connection parameters in DatabaseConnection.java
   - Ensure database and tables exist

2. **DynamoDB Connection Issues**:
   - Verify DynamoDB Local is running (port 8000)
   - Check AWS credentials configuration
   - Ensure LeaveRequests table exists

3. **Tomcat Deployment Issues**:
   - Check Tomcat logs in `logs/catalina.out`
   - Verify WAR file is properly built
   - Ensure Java version compatibility

### Logs Location
- **Tomcat Logs**: `$TOMCAT_HOME/logs/`
- **Application Logs**: Check console output or configure logging framework

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -am 'Add new feature'`)
4. Push to branch (`git push origin feature/new-feature`)
5. Create Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions:
- Create an issue in the repository
- Email: support@hrms-portal.com
- Documentation: Check the `docs/` directory for detailed guides