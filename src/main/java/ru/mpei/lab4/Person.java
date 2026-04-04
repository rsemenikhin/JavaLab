package ru.mpei.lab4;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Person entity.
 */
public class Person {
    private final long id;
    private final String name;
    private final String gender;
    private final Department department;
    private final BigDecimal salary;
    private final LocalDate birthDate;

    public Person(long id, String name, String gender, Department department, BigDecimal salary, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Returns person id.
     *
     * @return person id
     */
    public long getId() {
        return id;
    }

    /**
     * Returns person name.
     *
     * @return person name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns person gender.
     *
     * @return person gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns person department.
     *
     * @return person department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns person salary.
     *
     * @return person salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Returns person birth date.
     *
     * @return person birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                '}';
    }
}
