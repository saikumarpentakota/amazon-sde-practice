<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HRMS Test Page</title>
</head>
<body>
    <h1>HRMS Portal Test</h1>
    <p>If you can see this, the deployment is working!</p>
    <p>Current time: <%= new java.util.Date() %></p>
    <hr>
    <h3>Navigation Links:</h3>
    <ul>
        <li><a href="index.jsp">Dashboard</a></li>
        <li><a href="employees.jsp">Employees</a></li>
        <li><a href="payroll.jsp">Payroll</a></li>
        <li><a href="attendance.jsp">Attendance</a></li>
        <li><a href="leave.jsp">Leave</a></li>
    </ul>
</body>
</html>