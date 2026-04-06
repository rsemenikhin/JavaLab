package ru.mpei.lab5;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private static final String PROPERTIES_FILE_NAME = "injector.properties";

    public <T> T inject(T object) {
        Properties properties = loadProperties();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                injectField(object, field, properties);
            }
        }

        return object;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (inputStream == null) {
                throw new IllegalStateException("Properties file not found: " + PROPERTIES_FILE_NAME);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read properties file: " + PROPERTIES_FILE_NAME, e);
        }

        return properties;
    }

    private void injectField(Object object, Field field, Properties properties) {
        String interfaceName = field.getType().getName();
        String implementationName = properties.getProperty(interfaceName);

        if (implementationName == null) {
            throw new IllegalStateException("Implementation not found for: " + interfaceName);
        }

        try {
            Class<?> implementationClass = Class.forName(implementationName);
            Object implementation = implementationClass.getDeclaredConstructor().newInstance();

            field.setAccessible(true);
            field.set(object, implementation);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Cannot inject field: " + field.getName(), e);
        }
    }
}
