package ru.mpei.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    @Test
    public void shouldUseDefaultCount() {
        assertEquals(1000, App.getCount(new String[0]));
    }

    @Test
    public void shouldReadCountFromArgs() {
        assertEquals(2000, App.getCount(new String[]{"2000"}));
    }

    @Test
    public void shouldThrowExceptionForWrongCount() {
        assertThrows(IllegalArgumentException.class, () -> App.getCount(new String[]{"0"}));
    }
}
