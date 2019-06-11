package com.revision.lambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class DFunctionAndStreams {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 25);
        Employee ron = new Employee("Ron Weasley", 20);
        Employee jill = new Employee("Jill Mann", 12);
        Employee randy = new Employee("Randy Orton", 8);
        Employee milton = new Employee("Milton John", 30);
        Employee roover = new Employee("Roover Craft", 7);
        Employee nicholas = new Employee("Roger Stevens", 15);
        Employee steve = new Employee("Steve Hathway", 18);
        Employee pepe = new Employee("Pepe Nicholas", 11);
        Employee menolas = new Employee("Menolas Kostas", 25);

        List<Employee> employees = new LinkedList<>();
        employees.add(john);
        employees.add(ron);
        employees.add(jill);
        employees.add(randy);
        employees.add(milton);
        employees.add(roover);
        employees.add(nicholas);
        employees.add(steve);
        employees.add(pepe);
        employees.add(menolas);

        Function<Employee, String> getLastName = (Employee employee) -> employee.getName().substring(employee.getName().indexOf(' ') + 1);
        Function<Employee, String> getFirstName = (Employee employee) -> employee.getName().substring(0, employee.getName().indexOf(' '));
        printNames(employees, getFirstName);

        System.out.println("\nRandom first/last name");
        Random random = new Random();
        for (Employee employee : employees) {
            if (random.nextBoolean()) {
                printName(employee, getFirstName);
            } else {
                printName(employee, getLastName);
            }
        }

        System.out.println("\nUpper Cased Last name of " + employees.get(2).getName());
        Function<Employee, String> upperCase = (Employee employee) -> employee.getName().toUpperCase();
        Function<String, String> stripFirstName = (String full) -> full.substring(full.indexOf(' ') + 1);
        System.out.println("Approach #1");
        System.out.println(stripFirstName.apply(upperCase.apply(employees.get(2))));

        System.out.println("\nUpper Cased Last name of " + employees.get(3).getName());
        System.out.println("Approach #2");
        Function<Employee, String> combineUpperAndStripFirstName = upperCase.andThen(stripFirstName);
        System.out.println(combineUpperAndStripFirstName.apply(employees.get(3)));

        BiFunction<String, Employee, String> concatAge = (String employeeName, Employee employee) -> {
            return employeeName.concat(" " + employee.getAge());
        };
        System.out.println(concatAge.apply("Manndy", employees.get(3)));

        List<String> bingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G53", "G49", "g60", "G50",
                "I26", "I17", "I29",
                "O71"
        );

        Predicate<String> startsWithG = (String number) -> number.toUpperCase().startsWith("G");
        System.out.println("Bingo Numbers that start with G");
        bingoNumbers.stream().filter(startsWithG).forEach(System.out::println);

        System.out.println("\nBingo Numbers that start with G and are sorted");
        bingoNumbers.stream().filter(startsWithG).sorted(Comparator.naturalOrder()).forEach(System.out::println);

        System.out.println("\nAlternate using map");
        bingoNumbers.stream().map(String::toUpperCase).filter(startsWithG).sorted().forEach(System.out::println);

        System.out.println("\nAlternate using comparator");
        Comparator<String> comparator = (String objOne, String objTwo) -> objOne.compareTo(objTwo);
        bingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(startsWithG)
                .sorted(comparator)
                .forEach(System.out::println);
    }

    private static void printName(Employee employee, Function<Employee, String> nameFunction) {
        System.out.println(nameFunction.apply(employee));
    }

    private static void printNames(List<Employee> employees, Function<Employee, String> nameFunction) {
        employees.forEach(employee -> {
            System.out.println(nameFunction.apply(employee));
        });
    }
}