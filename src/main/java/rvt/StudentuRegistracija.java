package rvt;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class StudentuRegistracija {

    private static final String FILE_NAME = "data/students.csv";
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadStudents();

        while (true) {
            System.out.println("\n1 - register");
            System.out.println("2 - show");
            System.out.println("3 - exit");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1": register(); break;
                    case "2": showAll(); break;
                    case "3": return;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void register() throws Exception {

        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        validateName(firstName);

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        validateName(lastName);

        System.out.print("Email: ");
        String email = scanner.nextLine();
        validateEmail(email);

        System.out.print("Personal code: ");
        String personalCode = scanner.nextLine();
        validatePersonalCode(personalCode);

        String date = LocalDateTime.now().toString();

        students.add(new Student(firstName, lastName, email, personalCode, date));
        saveStudents();

        System.out.println("Saved!");
    }

    private static void showAll() {
        for (Student s : students) {
            System.out.println(s.firstName + " " + s.lastName + " | " + s.email);
        }
    }

    private static void validateName(String name) throws Exception {
        if (!Pattern.matches("^[A-Za-z]{3,}$", name)) {
            throw new Exception("Invalid name");
        }
    }

    private static void validateEmail(String email) throws Exception {
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
            throw new Exception("Invalid email");
        }
    }

    private static void validatePersonalCode(String code) throws Exception {
        if (!Pattern.matches("^\\d{6}-\\d{5}$", code)) {
            throw new Exception("Invalid personal code");
        }
    }

    private static void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                students.add(new Student(p[0], p[1], p[2], p[3], p[4]));
            }
        } catch (IOException ignored) {}
    }

    private static void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.firstName + "," + s.lastName + "," + s.email + "," + s.personalCode + "," + s.date);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("File error");
        }
    }

    static class Student {
        String firstName, lastName, email, personalCode, date;
        Student(String f, String l, String e, String p, String d) {
            firstName = f;
            lastName = l;
            email = e;
            personalCode = p;
            date = d;
        }
    }
}
