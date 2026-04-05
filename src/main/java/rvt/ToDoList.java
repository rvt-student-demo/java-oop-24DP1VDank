package rvt;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToDoList {

    ArrayList<Task> tasks;
    private final String FILE_NAME = "C:\\Users\\a240310vd\\Documents\\java-oop-24DP1VDank\\data\\todo.csv";

    public static void main(String[] args) {
        ToDoList list = new ToDoList();

        ToDoList todoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(todoList, scanner);
        ui.start();
    }

    public ToDoList() {
        tasks = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (Scanner fileReader = new Scanner(file)) {
            if (fileReader.hasNextLine()) {
                fileReader.nextLine();
            }

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",", 2);

                int id = Integer.parseInt(parts[0]);
                String text = parts[1];

                tasks.add(new Task(id, text));
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    public void add(String taskText) {
        int id = getLastId() + 1;
        Task task = new Task(id, taskText);
        tasks.add(task);

        boolean fileExists = new File(FILE_NAME).exists();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            if (!fileExists) {
                writer.write("id,task\n");
            }
            writer.write(id + "," + taskText + "\n");
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i).getTask());
        }
    }

    public void remove(int number) {
        tasks.remove(number - 1);
        updateFile();
    }

    public int getLastId() {
        if (tasks.isEmpty()) {
            return 0;
        }
        return tasks.get(tasks.size() - 1).getId();
    }

    private boolean updateFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("id,task\n");
            for (Task task : tasks) {
                writer.write(task.getId() + "," + task.getTask() + "\n");
            }
            return true;
        } catch (IOException e) {
            return false;
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
                System.out.print("Which one is removed? ");
                int number = Integer.valueOf(scanner.nextLine());
                todoList.remove(number);
            }
        }
    }
}

class Task {
    int id;
    String task;

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }
}