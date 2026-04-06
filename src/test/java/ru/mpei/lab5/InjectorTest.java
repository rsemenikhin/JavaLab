package ru.mpei.lab5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InjectorTest {
    @Test
    public void shouldInjectDependenciesIntoBean() {
        SomeBean bean = new Injector().inject(new SomeBean());

        assertNotNull(bean.getField1());
        assertNotNull(bean.getField2());
        assertInstanceOf(SomeImpl.class, bean.getField1());
        assertInstanceOf(SODoer.class, bean.getField2());
    }
}
