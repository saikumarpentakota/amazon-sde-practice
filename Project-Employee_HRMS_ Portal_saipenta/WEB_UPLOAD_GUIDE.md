# GitHub Web Upload Guide

## Step-by-Step Instructions

1. **Go to your repository:**
   https://github.com/saikumarpentakota/amazon-sde-practice

2. **Create new folder for HRMS project:**
   - Click "Create new file"
   - Type: `HRMS-Project/README.md`
   - Add content: `# HRMS Project - 10 Day Implementation`
   - Commit the file

3. **Upload these files to HRMS-Project folder:**

### Essential Files (Upload First):
- `README.md` (main project readme)
- `pom.xml`
- `REQUIREMENTS.md`
- `DESIGN.md`
- `schema.sql`

### Source Code:
- Create folder: `src/main/java/org/example/`
- Upload all `.java` files from your `src/main/java/org/example/` folders:
  - `controller/` folder contents
  - `entity/` folder contents  
  - `repository/` folder contents
  - `service/` folder contents
  - `util/` folder contents

### Web Files:
- Create folder: `src/main/webapp/`
- Upload `index.html`
- Create folder: `src/main/webapp/WEB-INF/`
- Upload `web.xml`

### Documentation:
- Upload all `.md` files
- Upload `build.bat`
- Upload `Jenkinsfile`

## Quick Upload Method:
1. Zip your entire `src/` folder
2. Upload the zip file
3. GitHub will automatically extract it