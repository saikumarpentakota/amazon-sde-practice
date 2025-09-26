# Manual Upload to GitHub

## Repository: https://github.com/saikumarpentakota/amazon-sde-practice.git

### Option 1: Use Git Commands
Run `GIT_COMMANDS.bat` or execute these commands:

```bash
git init
git remote add origin https://github.com/saikumarpentakota/amazon-sde-practice.git
git add .
git commit -m "Add HRMS Project - 10 Day Implementation"
git branch -M main
git push -u origin main
```

### Option 2: GitHub Web Upload
1. Go to https://github.com/saikumarpentakota/amazon-sde-practice
2. Click "Add file" > "Upload files"
3. Drag and drop these folders/files:
   - `src/` (entire folder)
   - `docs/`
   - `pom.xml`
   - `README.md`
   - `schema.sql`
   - `REQUIREMENTS.md`
   - `DESIGN.md`
   - `build.bat`
   - `Jenkinsfile`
   - All `.md` files

### Files to Skip
- `target/` folder
- `.idea/` folder
- `*.class` files
- `*.log` files