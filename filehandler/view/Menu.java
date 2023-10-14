package view;

import model.Person;

import java.util.List;

public class Menu {
    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Find persons with salary greater or equal to a specific amount");
        System.out.println("2. Copy unique words from a text file to a new file");
        System.out.println("3. Exit program");
    }

    public void displayPersonList(List<Person> people) {
        if (people.isEmpty()) {
            System.out.println("No matching records found.");
        } else {
            System.out.println("Matching Persons:");
            for (Person person : people) {
                System.out.println("Name: " + person.getName());
                System.out.println("Address: " + person.getAddress());
                System.out.println("Salary: " + person.getSalary());
                System.out.println();
            }
        }
    }
}