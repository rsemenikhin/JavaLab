package ru.mpei.lab4;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

/**
 * Application entry point.
 */
public class App {
    /**
     * Starts CSV reading demo.
     *
     * @param args command line arguments
     * @throws IOException if file cannot be read
     * @throws CsvValidationException if CSV line is invalid
     */
    public static void main(String[] args) throws IOException, CsvValidationException {
        CsvPersonReader reader = new CsvPersonReader();
        List<Person> people = reader.readPeople();

        System.out.println("Количество людей: " + people.size());
        System.out.println("Первые 10 записей:");

        for (int i = 0; i < people.size() && i < 10; i++) {
            System.out.println(people.get(i));
        }
    }
}
