# Лабораторная работа 5

Проект демонстрирует простое внедрение зависимостей через reflection.

## Что нужно сделать

В лабораторной реализованы:

- аннотация `@AutoInjectable`
- класс `Injector`
- чтение настроек из `injector.properties`
- внедрение зависимостей в поля, помеченные аннотацией
- пример работы на классе `SomeBean`
- unit-тест для проверки внедрения зависимостей

## Как работает Injector

`Injector` принимает объект, просматривает его поля через reflection и ищет поля с аннотацией `@AutoInjectable`.

Для каждого такого поля он:

- определяет тип поля
- ищет реализацию этого типа в `injector.properties`
- создает объект реализации
- записывает его в поле

Файл настроек находится здесь:

```text
src/main/resources/injector.properties
```

Пример настроек:

```properties
ru.mpei.lab5.SomeInterface=ru.mpei.lab5.SomeImpl
ru.mpei.lab5.SomeOtherInterface=ru.mpei.lab5.SODoer
```

## Пример запуска

В `App` создается объект `SomeBean`, затем зависимости внедряются через `Injector`:

```java
SomeBean bean = new Injector().inject(new SomeBean());
bean.foo();
```

Ожидаемый вывод:

```text
A
C
```

Если изменить реализацию для `SomeInterface` в `injector.properties` на `OtherImpl`, то вместо `A` будет выведено `B`.

## Структура проекта

- `src/main/java` - основной код
- `src/main/resources` - файл `injector.properties`
- `src/test/java` - unit-тесты
- `pom.xml` - Maven-конфигурация

## Запуск тестов

```bash
mvn test
```

## Запуск программы

```bash
mvn exec:java "-Dexec.mainClass=ru.mpei.lab5.App"
```
