package org.example;

import org.example.entity.Employee;
import org.example.entity.Payroll;
import org.example.repository.EmployeeDynamoRepository;
import org.example.repository.PayrollDynamoRepository;
import org.example.service.PayrollService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HRMSApplicationDynamoDB {
    
    private static EmployeeDynamoRepository employeeRepo = new EmployeeDynamoRepository();
    private static PayrollDynamoRepository payrollRepo = new PayrollDynamoRepository();
    private static PayrollService payrollService = new PayrollService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("\n=== HRMS DynamoDB Application ===");
        System.out.println("Make sure DynamoDB Local is running on port 8000!");
        
        while (true) {
            showMenu();
            int choice = getChoice();
            
            try {
                switch (choice) {
                    case 1: createEmployee(); break;
                    case 2: viewEmployees(); break;
                    case 3: calculatePayroll(); break;
                    case 4: viewPayrolls(); break;
                    case 5: 
                        System.out.println("Thank you for using HRMS!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.err.println("DynamoDB error: " + e.getMessage());
                System.err.println("Is DynamoDB Local running on port 8000?");
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("\n=== HRMS MENU ===");
        System.out.println("1. Create Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Calculate Payroll");
        System.out.println("4. View Payrolls");
        System.out.println("5. Exit");
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
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Department ID: ");
        String deptId = scanner.nextLine();
        
        System.out.print("Enter Salary: ");
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartmentId(deptId);
        employee.setSalary(salary);
        employee.setJoinDate(LocalDate.now());
        
        Employee saved = employeeRepo.save(employee);
        System.out.println("Employee created with ID: " + saved.getId());
    }
    
    private static void viewEmployees() {
        System.out.println("\n--- All Employees ---");
        List<Employee> employees = employeeRepo.findAll();
        
        if (employees.isEmpty()) {
            System.out.println("No employees found in DynamoDB.");
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
        
        Employee employee = employeeRepo.findById(empId);
        if (employee == null) {
            System.out.println("Employee not found in DynamoDB!");
            return;
        }
        
        System.out.print("Enter Month (YYYY-MM): ");
        String month = scanner.nextLine();
        
        System.out.print("Enter Overtime Amount: ");
        BigDecimal overtime = new BigDecimal(scanner.nextLine());
        
        Payroll payroll = payrollService.calculatePayroll(empId, month, overtime);
        
        System.out.println("\nPayroll Calculated and Saved to DynamoDB:");
        System.out.println("Employee: " + employee.getName());
        System.out.println("Basic Salary: $" + payroll.getBasicSalary());
        System.out.println("Overtime: $" + payroll.getOvertime());
        System.out.println("Total: $" + payroll.getTotal());
    }
    
    private static void viewPayrolls() {
        System.out.println("\n--- All Payrolls ---");
        
        System.out.print("Enter Employee ID (to view payrolls): ");
        String empId = scanner.nextLine();
        
        List<Payroll> payrolls = payrollRepo.findByEmployeeId(empId);
        
        if (payrolls.isEmpty()) {
            System.out.println("No payrolls found for employee: " + empId);
            return;
        }
        
        Employee emp = employeeRepo.findById(empId);
        String empName = emp != null ? emp.getName() : "Unknown";
        
        System.out.println("Payrolls for " + empName + ":");
        for (Payroll payroll : payrolls) {
            System.out.println("Month: " + payroll.getMonth() + ", Total: $" + payroll.getTotal());
        }
    }
}