package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntMassTest {

    @Test
    void newContainerShouldBeEmpty() {
        IntMass container = new IntMass();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
        assertEquals("[]", container.toString());
    }

    @Test
    void addShouldStoreElementsInCorrectOrder() {
        IntMass container = new IntMass();

        container.add(10);
        container.add(20);
        container.add(30);

        assertEquals(3, container.size());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
    }

    @Test
    void removeAtShouldRemoveElementAndShiftRemainingElements() {
        IntMass container = new IntMass();

        container.add(10);
        container.add(20);
        container.add(30);
        container.add(40);

        int removedValue = container.removeAt(1);

        assertEquals(20, removedValue);
        assertEquals(3, container.size());
        assertEquals(10, container.get(0));
        assertEquals(30, container.get(1));
        assertEquals(40, container.get(2));
    }


    @Test
    void getShouldThrowExceptionForNegativeIndex() {
        IntMass container = new IntMass();
        container.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
    }

    @Test
    void getShouldThrowExceptionForIndexOutOfRange() {
        IntMass container = new IntMass();
        container.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    void removeAtShouldThrowExceptionForInvalidIndex() {
        IntMass container = new IntMass();

        assertThrows(IndexOutOfBoundsException.class, () -> container.removeAt(0));
    }

    @Test
    void containerShouldGrowWhenCapacityIsExceeded() {
        IntMass container = new IntMass(2);

        container.add(10);
        container.add(20);
        container.add(30);
        container.add(40);

        assertEquals(4, container.size());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
        assertEquals(40, container.get(3));
    }
}