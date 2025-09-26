# HRMS PlantUML Documentation

This directory contains comprehensive PlantUML diagrams for the HRMS (Human Resource Management System) project.

## Diagrams Overview

### 1. Class Diagram (`class-diagram.puml`)
**Purpose**: Shows the complete object-oriented structure of the HRMS system
**Includes**:
- Entity classes (Employee, Payroll, Attendance, LeaveRequest, Department)
- Repository layer (DynamoDB and MySQL repositories)
- Service layer (PayrollService, EnhancedPayrollService, PayrollBatchService)
- Controller layer (REST API controllers)
- Relationships and dependencies between classes

### 2. Architecture Diagram (`architecture-diagram.puml`)
**Purpose**: Illustrates the high-level system architecture and component interactions
**Includes**:
- Presentation layer (Web Browser, REST clients, Console apps)
- API Gateway (Tomcat Server, Servlet Container)
- Business logic layers (Controllers, Services, Repositories)
- Data storage (MySQL Database, DynamoDB)
- Queue system and testing framework
- CI/CD pipeline components

### 3. Sequence Diagram (`sequence-diagram.puml`)
**Purpose**: Details the flow of payroll generation processes
**Includes**:
- Single employee payroll calculation flow
- Batch payroll processing with queue operations
- Interactions between User, Controllers, Services, and Databases
- Step-by-step process execution

### 4. Database ERD (`database-erd.puml`)
**Purpose**: Shows the database schema and entity relationships
**Includes**:
- MySQL tables (employees, departments, payroll, attendance)
- DynamoDB tables (Employees, Payroll, LeaveRequests)
- Primary keys, foreign keys, and column definitions
- Relationships between entities
- Cross-database synchronization notes

### 5. Deployment Diagram (`deployment-diagram.puml`)
**Purpose**: Represents the physical deployment architecture
**Includes**:
- Development environment (IntelliJ IDEA, Maven, Tests)
- CI/CD pipeline (Jenkins, Git, Build artifacts)
- Application server (Tomcat with WAR deployment)
- Database servers (MySQL and DynamoDB Local)
- Client applications and monitoring systems

## How to Use These Diagrams

### Online Rendering
1. Copy the PlantUML code from any `.puml` file
2. Paste it into [PlantUML Online Server](http://www.plantuml.com/plantuml/uml/)
3. View the rendered diagram

### Local Rendering
1. Install PlantUML extension in your IDE
2. Open any `.puml` file
3. Use the preview feature to render diagrams

### Export Options
- PNG/SVG for documentation
- PDF for presentations
- ASCII art for text-based documentation

## Diagram Relationships

```
class-diagram.puml ──┐
                    ├── Shows static structure
architecture-diagram.puml ──┤
                    ├── Shows runtime architecture
sequence-diagram.puml ──┤
                    ├── Shows dynamic behavior
database-erd.puml ──┤
                    ├── Shows data structure
deployment-diagram.puml ──┘
                    └── Shows physical deployment
```

## Key Features Illustrated

### Technical Architecture
- **Layered Architecture**: Clear separation of concerns
- **Hybrid Database**: MySQL + DynamoDB integration
- **RESTful APIs**: Complete CRUD operations
- **Queue Processing**: Batch operations with ConcurrentLinkedQueue
- **CI/CD Pipeline**: Automated build and deployment

### Business Processes
- **Employee Management**: Complete lifecycle management
- **Payroll Processing**: Department-based salary computation
- **Attendance Tracking**: Time-based attendance recording
- **Leave Management**: Request submission and approval workflow
- **Batch Operations**: Scalable payroll generation

### Data Flow
- **Request Processing**: Client → Controller → Service → Repository → Database
- **Response Generation**: Database → Repository → Service → Controller → Client
- **Queue Operations**: Service → Queue → Batch Processing → Database

## Integration Points

The diagrams show how different components integrate:
- **Web APIs** with **Database repositories**
- **MySQL** and **DynamoDB** hybrid storage
- **Queue system** with **Batch processing**
- **Jenkins CI/CD** with **Application deployment**
- **Testing framework** with **Business logic**

These diagrams provide a complete visual documentation of the HRMS system architecture, making it easier to understand, maintain, and extend the application.