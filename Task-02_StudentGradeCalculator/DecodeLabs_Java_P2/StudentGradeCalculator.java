package DecodeLabs_Java_P2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("       STUDENT GRADE CALCULATOR");
        System.out.println("=========================================");

        System.out.print("Enter Student Name: ");
        String name = input.nextLine();

        System.out.print("Enter Roll Number: ");
        String rollNo = input.nextLine();

        int n = 0;
        double cgpa;

        // Exception handling for number of subjects//
        while (true) {
            try {
                System.out.print("Enter Number of Subjects: ");
                n = input.nextInt();

                if (n <= 0) {
                    System.out.println("Number of subjects must be greater than 0.");
                    continue;
                }

                input.nextLine();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                input.nextLine();
            }
        }

        String[] subjects = new String[n];
        double[] marks = new double[n];

        double total = 0;

        // Input subject names and marks //
        for (int i = 0; i < n; i++) {

            System.out.println("\nSubject " + (i + 1));

            System.out.print("Enter Subject Name: ");
            subjects[i] = input.nextLine();

            while (true) {
                try {
                    System.out.print("Enter Marks (0 - 100): ");
                    marks[i] = input.nextDouble();

                    if (marks[i] < 0 || marks[i] > 100) {
                        System.out.println("Marks must be between 0 and 100.");
                        continue;
                    }

                    input.nextLine();
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter numeric marks.");
                    input.nextLine();
                }
            }

            total += marks[i];
        }

        double percentage = total / n;

        String grade;

        if (percentage >= 90)
            grade = "A+";
        else if (percentage >= 80)
            grade = "A";
        else if (percentage >= 70)
            grade = "B";
        else if (percentage >= 60)
            grade = "C";
        else if (percentage >= 50)
            grade = "D";
        else
            grade = "F";
        if (percentage >= 90) {
            grade = "A+";
            cgpa = 4.0;
        }
        else if (percentage >= 80) {
            grade = "A";
            cgpa = 3.7;
        }
        else if (percentage >= 70) {
            grade = "B";
            cgpa = 3.0;
        }
        else if (percentage >= 60) {
            grade = "C";
            cgpa = 2.0;
        }
        else if (percentage >= 50) {
            grade = "D";
            cgpa = 1.0;
        }
        else {
            grade = "F";
            cgpa = 0.0;
        }
        String remarks;

        if (percentage >= 90)
            remarks = "Excellent";
        else if (percentage >= 80)
            remarks = "Very Good";
        else if (percentage >= 70)
            remarks = "Good";
        else if (percentage >= 60)
            remarks = "Average";
        else
            remarks = "Needs Improvement";

        // Display Report
        System.out.println("\n================================================");
        System.out.println("                 STUDENT REPORT");
        System.out.println("================================================");

        System.out.println("Student Name : " + name);
        System.out.println("Roll Number  : " + rollNo);

        System.out.println("\nSubjects and Marks");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.printf("%-20s : %.2f%n", subjects[i], marks[i]);
        }

        System.out.println("------------------------------------------------");
        System.out.printf("Total Marks         : %.2f%n", total);
        System.out.printf("Average Percentage  : %.2f%%%n", percentage);
        System.out.println("Grade               : " + grade);
        System.out.println("Remarks             : " + remarks);
        System.out.printf("CGPA                : %.2f%n", cgpa);

        if (percentage >= 50)
            System.out.println("Result              : PASS");
        else
            System.out.println("Result              : FAIL");

        System.out.println("================================================");
        System.out.println("                THANK YOU!");
        System.out.println("================================================");

        input.close();
    }
}