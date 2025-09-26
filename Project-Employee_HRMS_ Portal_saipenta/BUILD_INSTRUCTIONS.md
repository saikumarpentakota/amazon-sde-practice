# Build Instructions

## Using IntelliJ IDEA (Recommended)

### Method 1: Maven Tool Window
1. **View** → **Tool Windows** → **Maven**
2. **HRMSPROJECT** → **Lifecycle** → **clean** (double-click)
3. **HRMSPROJECT** → **Lifecycle** → **compile** (double-click)
4. **HRMSPROJECT** → **Lifecycle** → **package** (double-click)

### Method 2: Build Menu
1. **Build** → **Build Project**
2. **Build** → **Build Artifacts** → **HRMSPROJECT:war** → **Build**

### Method 3: Terminal in IntelliJ
1. **View** → **Tool Windows** → **Terminal**
2. Use IntelliJ's embedded Maven: `./mvnw clean package` (if wrapper exists)

## Manual Build (No Maven)

### Compile Java Files
```cmd
javac -cp "lib/*" -d target/classes src/main/java/org/example/**/*.java
```

### Create WAR
```cmd
jar -cvf target/HRMSPROJECT.war -C src/main/webapp . -C target/classes .
```

## Result
- WAR file: `target/HRMSPROJECT-1.0-SNAPSHOT.war`
- Deploy to Tomcat webapps folder