package rvt;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    ArrayList<String> tasks;

    public static void main(String[] args) {
        ToDoList list = new ToDoList();
        list.add("read the course material");
        list.add("watch the latest fool us");
        list.add("take it easy");
        list.print();
        list.remove(2);
        list.print();
        list.add("buy raisins");
        list.print();
        list.remove(1);
        list.remove(1);
        list.print();

        ToDoList todoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(todoList, scanner);
        ui.start();
    }

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void add(String task) {
        tasks.add(task);
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    public void remove(int number) {
        tasks.remove(number - 1);
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
