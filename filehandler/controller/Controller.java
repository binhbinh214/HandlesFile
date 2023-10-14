package controller;

import model.FileProcessor;
import model.Person;
import view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Controller {
    private FileProcessor fileProcessor;
    private Menu view;

    public Controller() {
        fileProcessor = new FileProcessor();
        view = new Menu();
    }

    public void run() {
        int choice;

        do {
            view.displayMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter file path: ");
                        String path = getUserInput();
                        System.out.print("Enter minimum salary: ");
                        double minSalary = Double.parseDouble(getUserInput());

                        List<Person> people = fileProcessor.getPerson(path, minSalary);
                        view.displayPersonList(people);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter source file path: ");
                        String sourcePath = getUserInput();
                        System.out.print("Enter destination file path: ");
                        String destinationPath = getUserInput();

                        boolean copied = fileProcessor.copyWordOneTimes(sourcePath, destinationPath);
                        if (copied) {
                            System.out.println("Unique words copied successfully.");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);
    }

    private int getUserChoice() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private String getUserInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}