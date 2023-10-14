package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileProcessor {
    public List<Person> getPerson(String path, double money) throws Exception {
        List<Person> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String address = parts[1].trim();
                    double salary = Double.parseDouble(parts[2].trim());

                    if (salary >= money) {
                        people.add(new Person(name, address, salary));
                    }
                }
            }

            if (people.isEmpty()) {
                throw new Exception("No matching records found.");
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Path doesn't exist.");
        } catch (IOException e) {
            throw new Exception("Can't read file.");
        }

        // Sort people by salary
        people.sort(Comparator.comparingDouble(Person::getSalary));
        return people;
    }

    public boolean copyWordOneTimes(String source, String destination) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
                BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {

            String line;
            List<String> uniqueWords = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!uniqueWords.contains(word)) {
                        uniqueWords.add(word);
                        bw.write(word);
                        bw.write(" ");
                    }
                }
            }

            return true;
        } catch (FileNotFoundException e) {
            throw new Exception("Path doesn't exist.");
        } catch (IOException e) {
            throw new Exception("Can't read/write file.");
        }
    }
}