package ru.mpei.lab4;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads people from CSV file.
 */
public class CsvPersonReader {
    private static final String CSV_FILE_NAME = "foreign_names.csv";
    private static final char SEPARATOR = ';';
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Reads people from resource CSV file.
     *
     * @return list of people
     * @throws IOException if file cannot be read
     * @throws CsvValidationException if CSV line is invalid
     */
    public List<Person> readPeople() throws IOException, CsvValidationException {
        List<Person> people = new ArrayList<Person>();
        Map<String, Department> departments = new LinkedHashMap<String, Department>();
        int nextDepartmentId = 1;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CSV_FILE_NAME);
        if (inputStream == null) {
            throw new IOException("File not found: " + CSV_FILE_NAME);
        }

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream), SEPARATOR)) {
            reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null) {
                String departmentName = line[4];
                Department department = departments.get(departmentName);
                if (department == null) {
                    department = new Department(nextDepartmentId, departmentName);
                    departments.put(departmentName, department);
                    nextDepartmentId++;
                }

                Person person = new Person(
                        Long.parseLong(line[0]),
                        line[1],
                        line[2],
                        department,
                        new BigDecimal(line[5]),
                        LocalDate.parse(line[3], DATE_FORMATTER)
                );

                people.add(person);
            }
        }

        return people;
    }
}
