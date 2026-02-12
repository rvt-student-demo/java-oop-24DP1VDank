
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class StudentuRegistracija {

    private static final String FILE_NAME = "students.csv";
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadStudents();

        while (true) {
            System.out.println("\nChoose action:");
            System.out.println("1 - register");
            System.out.println("2 - show");
            System.out.println("3 - remove");
            System.out.println("4 - edit");
            System.out.println("5 - exit");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1": register(); break;
                    case "2": showAll(); break;
                    case "3": remove(); break;
                    case "4": edit(); break;
                    case "5":
                        System.out.println("Program stopped.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // REGISTER
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

        for (Student s : students) {
            if (s.email.equals(email))
                throw new Exception("Email already exists.");
        }

        System.out.print("Personal code: ");
        String personalCode = scanner.nextLine();
        validatePersonalCode(personalCode);

        for (Student s : students) {
            if (s.personalCode.equals(personalCode))
                throw new Exception("Personal code already exists.");
        }

        String date = LocalDateTime.now().toString();

        students.add(new Student(firstName, lastName, email, personalCode, date));
        saveStudents();

        System.out.println("Student registered successfully.");
    }

    // SHOW
    private static void showAll() {

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-20s | %-15s | %-20s |\n",
                "FirstName", "LastName", "Email", "PersonalCode", "RegistrationDate");
        System.out.println("------------------------------------------------------------------------------------------------");

        for (Student s : students) {
            System.out.printf("| %-10s | %-10s | %-20s | %-15s | %-20s |\n",
                    s.firstName, s.lastName, s.email, s.personalCode, s.registrationDate);
        }

        System.out.println("------------------------------------------------------------------------------------------------");
    }

    // REMOVE
    private static void remove() {
        System.out.print("Enter personal code: ");
        String code = scanner.nextLine();

        students.removeIf(s -> s.personalCode.equals(code));
        saveStudents();

        System.out.println("Student removed.");
    }

    // EDIT
    private static void edit() throws Exception {

        System.out.print("Enter personal code: ");
        String code = scanner.nextLine();

        for (Student s : students) {
            if (s.personalCode.equals(code)) {

                System.out.print("New first name: ");
                String newFirst = scanner.nextLine();
                validateName(newFirst);

                System.out.print("New last name: ");
                String newLast = scanner.nextLine();
                validateName(newLast);

                System.out.print("New email: ");
                String newEmail = scanner.nextLine();
                validateEmail(newEmail);

                s.firstName = newFirst;
                s.lastName = newLast;
                s.email = newEmail;

                saveStudents();
                System.out.println("Student updated.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // VALIDATION
    private static void validateName(String name) throws Exception {
        if (!Pattern.matches("^[A-Za-zĀ-Žā-ž]{3,}$", name)) {
            throw new Exception("Name must contain only letters and be at least 3 characters.");
        }
    }

    private static void validateEmail(String email) throws Exception {
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
            throw new Exception("Invalid email format.");
        }
    }

    private static void validatePersonalCode(String code) throws Exception {
        if (!Pattern.matches("^\\d{6}-\\d{5}$", code)) {
            throw new Exception("Personal code must be in format 010101-12345.");
        }
    }

    // FILE HANDLING
    private static void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (IOException ignored) {}
    }

    private static void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.firstName + "," + s.lastName + "," + s.email + "," + s.personalCode + "," + s.registrationDate);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("File error.");
        }
    }

    // INNER CLASS
    static class Student {
        String firstName;
        String lastName;
        String email;
        String personalCode;
        String registrationDate;

        Student(String f, String l, String e, String p, String d) {
            firstName = f;
            lastName = l;
            email = e;
            personalCode = p;
            registrationDate = d;
        }
    }
}
