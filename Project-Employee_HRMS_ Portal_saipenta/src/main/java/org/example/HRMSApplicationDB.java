package org.example;

import org.example.entity.Employee;
import org.example.entity.Payroll;
import org.example.entity.Attendance;
import org.example.repository.EmployeeRepository;
import org.example.repository.PayrollRepository;
import org.example.repository.AttendanceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class HRMSApplicationDB {
    
    private static EmployeeRepository employeeRepo = new EmployeeRepository();
    private static PayrollRepository payrollRepo = new PayrollRepository();
    private static AttendanceRepository attendanceRepo = new AttendanceRepository();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("\n=== HRMS Database Application ===");
        
        while (true) {
            showMenu();
            int choice = getChoice();
            
            try {
                switch (choice) {
                    case 1: createEmployee(); break;
                    case 2: viewEmployees(); break;
                    case 3: calculatePayroll(); break;
                    case 4: viewPayrolls(); break;
                    case 5: recordAttendance(); break;
                    case 6: viewAttendance(); break;
                    case 7: 
                        System.out.println("Thank you for using HRMS!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("\n=== HRMS MENU ===");
        System.out.println("1. Create Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Calculate Payroll");
        System.out.println("4. View Payrolls");
        System.out.println("5. Record Attendance");
        System.out.println("6. View Attendance");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void createEmployee() throws Exception {
        System.out.println("\n--- Create Employee ---");
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Department ID: ");
        Long deptId = Long.parseLong(scanner.nextLine());
        
        System.out.print("Enter Salary: ");
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartmentId(String.valueOf(deptId));
        employee.setSalary(salary);
        employee.setJoinDate(LocalDate.now());
        
        Employee saved = employeeRepo.save(employee);
        System.out.println("Employee created with ID: " + saved.getId());
    }
    
    private static void viewEmployees() throws Exception {
        System.out.println("\n--- All Employees ---");
        List<Employee> employees = employeeRepo.findAll();
        
        if (employees.isEmpty()) {
            System.out.println("No employees found in database.");
            return;
        }
        
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + 
                             ", Email: " + emp.getEmail() + ", Salary: $" + emp.getSalary());
        }
    }
    
    private static void calculatePayroll() throws Exception {
        System.out.println("\n--- Calculate Payroll ---");
        
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        
        Employee employee = employeeRepo.findById(empId);
        if (employee == null) {
            System.out.println("Employee not found in database!");
            return;
        }
        
        System.out.print("Enter Month (YYYY-MM): ");
        String month = scanner.nextLine();
        
        System.out.print("Enter Overtime Amount: ");
        BigDecimal overtime = new BigDecimal(scanner.nextLine());
        
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(empId);
        payroll.setMonth(month);
        payroll.setBasicSalary(employee.getSalary());
        payroll.setOvertime(overtime);
        payroll.setTotal(employee.getSalary().add(overtime));
        
        Payroll saved = payrollRepo.save(payroll);
        
        System.out.println("\nPayroll Calculated and Saved:");
        System.out.println("Employee: " + employee.getName());
        System.out.println("Basic Salary: $" + saved.getBasicSalary());
        System.out.println("Overtime: $" + saved.getOvertime());
        System.out.println("Total: $" + saved.getTotal());
    }
    
    private static void viewPayrolls() throws Exception {
        System.out.println("\n--- All Payrolls ---");
        List<Payroll> payrolls = payrollRepo.findAll();
        
        if (payrolls.isEmpty()) {
            System.out.println("No payrolls found in database.");
            return;
        }
        
        for (Payroll payroll : payrolls) {
            Employee emp = employeeRepo.findById(payroll.getEmployeeId());
            String empName = emp != null ? emp.getName() : "Unknown";
            System.out.println("Employee: " + empName + ", Month: " + payroll.getMonth() + 
                             ", Total: $" + payroll.getTotal());
        }
    }
    
    private static void recordAttendance() throws Exception {
        System.out.println("\n--- Record Attendance ---");
        
        System.out.print("Enter Employee ID: ");
        Long empId = Long.parseLong(scanner.nextLine());
        
        Employee employee = employeeRepo.findById(String.valueOf(empId));
        if (employee == null) {
            System.out.println("Employee not found in database!");
            return;
        }
        
        System.out.print("Enter Check-in Time (HH:MM): ");
        LocalTime checkIn = LocalTime.parse(scanner.nextLine());
        
        System.out.print("Enter Check-out Time (HH:MM): ");
        LocalTime checkOut = LocalTime.parse(scanner.nextLine());
        
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(empId);
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(checkIn);
        attendance.setCheckOut(checkOut);
        
        double hours = java.time.Duration.between(checkIn, checkOut).toMinutes() / 60.0;
        attendance.setHoursWorked(hours);
        
        Attendance saved = attendanceRepo.save(attendance);
        System.out.println("Attendance recorded! Hours worked: " + hours);
    }
    
    private static void viewAttendance() throws Exception {
        System.out.println("\n--- Attendance Records ---");
        
        System.out.print("Enter Employee ID (or press Enter for all): ");
        String input = scanner.nextLine();
        
        if (input.trim().isEmpty()) {
            System.out.println("Feature not implemented for all employees.");
            return;
        }
        
        Long empId = Long.parseLong(input);
        List<Attendance> attendances = attendanceRepo.findByEmployeeId(empId);
        
        if (attendances.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }
        
        for (Attendance att : attendances) {
            System.out.println("Date: " + att.getDate() + 
                             ", Check-in: " + att.getCheckIn() +
                             ", Check-out: " + att.getCheckOut() +
                             ", Hours: " + att.getHoursWorked());
        }
    }
}