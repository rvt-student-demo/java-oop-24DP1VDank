package rvt;

import java.sql.*;
import java.util.Scanner;

public class ToDoList {

    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public static void main(String[] args) {

        ToDoList todoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(todoList, scanner);
        ui.start();
    }

    public ToDoList() {
        initDatabase();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initDatabase() {

        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "task TEXT NOT NULL)";

        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Database error");
        }
    }

    public void add(String taskText) {

        String sql = "INSERT INTO todo(task) VALUES(?)";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, taskText);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Add error");
        }
    }

    public void print() {

        String sql = "SELECT * FROM todo";

        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ": " +
                                rs.getString("task"));
            }

        } catch (SQLException e) {
            System.out.println("Read error");
        }
    }

    public void remove(int id) {

        String sql = "DELETE FROM todo WHERE id = ?";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete error");
        }
    }
}

class UserInterface {

    private ToDoList todoList;
    private Scanner scanner;

    public UserInterface(ToDoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {

        while (true) {

            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {

                System.out.print("To add: ");
                String task = scanner.nextLine();

                todoList.add(task);

            } else if (command.equals("list")) {

                todoList.print();

            } else if (command.equals("remove")) {

                System.out.print("Id to remove: ");
                int id = Integer.valueOf(scanner.nextLine());

                todoList.remove(id);
            }
        }
    }
}