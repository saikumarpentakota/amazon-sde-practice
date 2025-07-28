package DAY20;

import java.util.Scanner;

public class Hometask1 {
    private String name;
    private int id;
    private int[] marks;
    private double fees;


    public Hometask1(String name, int id) {
        this.name = name;
        this.id = id;
        this.marks = new int[3];
    }


    public void registrationDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student details:");
        System.out.print("Name: ");
        this.name = scanner.nextLine();
        System.out.print("ID: ");
        this.id = scanner.nextInt();
    }


    public double marksCalc() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter marks for 3 subjects:");
        double total = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
            total += marks[i];
        }
        return total / 3;
    }


    public void feesCalc() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter base fees: ");
        double baseFees = scanner.nextDouble();
        System.out.print("Enter additional charges: ");
        double additionalCharges = scanner.nextDouble();
        this.fees = baseFees + additionalCharges;
    }


    public void displayInfo() {
        System.out.println("\nStudent Information:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Average Marks: " + marksCalc());
        System.out.println("Total Fees: $" + fees);
    }

    public static void main(String[] args) {
        Hometask1 student = new Hometask1("", 0);
        student.registrationDetails();
        student.marksCalc();
        student.feesCalc();
        student.displayInfo();
    }
}


