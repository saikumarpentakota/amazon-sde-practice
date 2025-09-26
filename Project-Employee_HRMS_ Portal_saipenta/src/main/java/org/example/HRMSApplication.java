package org.example;

import org.example.entity.Employee;
import org.example.entity.Payroll;
import org.example.entity.Attendance;
import org.example.service.PayrollBatchService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HRMSApplication {
    
    private static List<Employee> employees = new ArrayList<>();
    private static List<Payroll> payrolls = new ArrayList<>();
    private static List<Attendance> attendances = new ArrayList<>();
    private static PayrollBatchService batchService = new PayrollBatchService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("\n=== HRMS Interactive Application ===");
        
        while (true) {
            showMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1: createEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: calculatePayroll(); break;
                case 4: viewPayrolls(); break;
                case 5: recordAttendance(); break;
                case 6: viewAttendance(); break;
                case 7: batchProcessing(); break;
                case 8: 
                    System.out.println("Thank you for using HRMS!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
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
        System.out.println("7. Batch Processing");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void createEmployee() {
        System.out.println("\n--- Create Employee ---");
        
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Department ID: ");
        String deptId = scanner.nextLine();
        
        System.out.print("Enter Salary: ");
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartmentId(deptId);
        employee.setSalary(salary);
        employee.setJoinDate(LocalDate.now());
        
        employees.add(employee);
        System.out.println("Employee created successfully!");
    }
    
    private static void viewEmployees() {
        System.out.println("\n--- All Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + 
                             ", Email: " + emp.getEmail() + ", Salary: $" + emp.getSalary());
        }
    }
    
    private static void calculatePayroll() {
        System.out.println("\n--- Calculate Payroll ---");
        
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        
        Employee employee = findEmployee(empId);
        if (employee == null) {
            System.out.println("Employee not found!");
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
        
        payrolls.add(payroll);
        
        System.out.println("\nPayroll Calculated:");
        System.out.println("Employee: " + employee.getName());
        System.out.println("Basic Salary: $" + payroll.getBasicSalary());
        System.out.println("Overtime: $" + payroll.getOvertime());
        System.out.println("Total: $" + payroll.getTotal());
    }
    
    private static void viewPayrolls() {
        System.out.println("\n--- All Payrolls ---");
        if (payrolls.isEmpty()) {
            System.out.println("No payrolls found.");
            return;
        }
        
        for (Payroll payroll : payrolls) {
            Employee emp = findEmployee(payroll.getEmployeeId());
            String empName = emp != null ? emp.getName() : "Unknown";
            System.out.println("Employee: " + empName + ", Month: " + payroll.getMonth() + 
                             ", Total: $" + payroll.getTotal());
        }
    }
    
    private static void recordAttendance() {
        System.out.println("\n--- Record Attendance ---");
        
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        
        if (findEmployee(empId) == null) {
            System.out.println("Employee not found!");
            return;
        }
        
        System.out.print("Enter Check-in Time (HH:MM): ");
        LocalTime checkIn = LocalTime.parse(scanner.nextLine());
        
        System.out.print("Enter Check-out Time (HH:MM): ");
        LocalTime checkOut = LocalTime.parse(scanner.nextLine());
        
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(Long.parseLong(empId.replaceAll("\\D", "")));
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(checkIn);
        attendance.setCheckOut(checkOut);
        
        double hours = java.time.Duration.between(checkIn, checkOut).toMinutes() / 60.0;
        attendance.setHoursWorked(hours);
        
        attendances.add(attendance);
        System.out.println("Attendance recorded! Hours worked: " + hours);
    }
    
    private static void viewAttendance() {
        System.out.println("\n--- Attendance Records ---");
        if (attendances.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }
        
        for (Attendance att : attendances) {
            System.out.println("Employee ID: " + att.getEmployeeId() + 
                             ", Date: " + att.getDate() + 
                             ", Hours: " + att.getHoursWorked());
        }
    }
    
    private static void batchProcessing() {
        System.out.println("\n--- Batch Processing ---");
        System.out.println("1. Add all employees to queue");
        System.out.println("2. View queue size");
        System.out.print("Enter choice: ");
        
        int choice = getChoice();
        if (choice == 1) {
            for (Employee emp : employees) {
                batchService.addToQueue(emp.getId());
            }
            System.out.println("All employees added to queue!");
        } else if (choice == 2) {
            System.out.println("Current queue size: " + batchService.getQueueSize());
        }
    }
    
    private static Employee findEmployee(String id) {
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}