package ru.mpei.lab4;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CsvPersonReaderTest {
    @Test
    public void shouldReadPeopleFromCsvFile() throws IOException, CsvValidationException {
        CsvPersonReader reader = new CsvPersonReader();

        List<Person> people = reader.readPeople();

        assertFalse(people.isEmpty());

        Person firstPerson = people.get(0);
        assertNotNull(firstPerson.getId());
        assertNotNull(firstPerson.getName());
        assertNotNull(firstPerson.getGender());
        assertNotNull(firstPerson.getDepartment());
        assertNotNull(firstPerson.getSalary());
        assertNotNull(firstPerson.getBirthDate());
    }
}
