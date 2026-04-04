# Лабораторная работа 4

Проект читает CSV-файл с информацией о людях и преобразует его в список объектов `Person`.

## Сущности

### Person

Поля:

- `id`
- `name`
- `gender`
- `department`
- `salary`
- `birthDate`

### Department

Поля:

- `id`
- `name`

## Что делает программа

- читает файл `foreign_names.csv`
- получает данные о людях из CSV
- создает объекты `Person`
- создает объекты `Department`
- возвращает список `List<Person>`

CSV-файл лежит в `src/main/resources`.

## Структура проекта

- `src/main/java` - основной код
- `src/main/resources` - CSV-файл
- `src/test/java` - unit-тесты
- `pom.xml` - Maven-конфигурация

## Запуск тестов

```bash
mvn test
```

## Запуск программы

```bash
mvn exec:java "-Dexec.mainClass=ru.mpei.lab4.App"
```
