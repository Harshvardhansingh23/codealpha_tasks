import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double[] grades;
    private int numSubjects;

    public Student(String name, int numSubjects) {
        this.name = name;
        this.numSubjects = numSubjects;
        this.grades = new double[numSubjects];
    }

    public void inputGrades(Scanner scanner) {
        System.out.println("Enter grades for " + name + ":");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
        }
    }

    public double getAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / numSubjects;
    }

    public double getHighest() {
        double max = grades[0];
        for (double grade : grades) {
            if (grade > max) max = grade;
        }
        return max;
    }

    public double getLowest() {
        double min = grades[0];
        for (double grade : grades) {
            if (grade < min) min = grade;
        }
        return min;
    }

    public void displaySummary() {
        System.out.println("Student: " + name);
        System.out.println("Average: " + getAverage());
        System.out.println("Highest: " + getHighest());
        System.out.println("Lowest: " + getLowest());
        System.out.println();
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        System.out.print("Enter number of subjects per student: ");
        int numSubjects = scanner.nextInt();

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.next();
            Student student = new Student(name, numSubjects);
            student.inputGrades(scanner);
            students.add(student);
        }

        System.out.println("\nSummary Report:");
        for (Student student : students) {
            student.displaySummary();
        }

        scanner.close();
    }
}