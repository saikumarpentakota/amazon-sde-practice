# Upload to GitHub Instructions

## Files to Upload

Upload these key files and folders to your GitHub repository:

### Source Code
- `src/` - All Java source code
- `pom.xml` - Maven configuration
- `README.md` - Project documentation

### Database & Configuration
- `schema.sql` - Database schema
- `mysql-tables.sql` - MySQL table creation
- `REQUIREMENTS.md` - Project requirements
- `DESIGN.md` - System design

### Documentation
- `docs/` - Architecture diagrams
- `BUILD_INSTRUCTIONS.md`
- `DEPLOYMENT_FIX.md`
- `MYSQL_SETUP.md`
- `DYNAMODB_SETUP.md`

### Scripts
- `build.bat` - Build script
- `payroll-job.sh` - Jenkins job script
- `Jenkinsfile` - CI/CD pipeline

## Files to EXCLUDE (already in .gitignore)
- `target/` - Build artifacts
- `.idea/` - IDE files
- `*.class` - Compiled files
- `*.log` - Log files

## GitHub Upload Steps
1. Create new repository on GitHub
2. Upload all files except those in .gitignore
3. Set repository description: "HRMS Project - 10 Day Implementation with MySQL & DynamoDB"
4. Add topics: `java`, `mysql`, `dynamodb`, `hrms`, `servlet`, `maven`