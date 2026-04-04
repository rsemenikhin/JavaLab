package ru.mpei.lab4;

/**
 * Department entity.
 */
public class Department {
    private final int id;
    private final String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns department id.
     *
     * @return department id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns department name.
     *
     * @return department name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
