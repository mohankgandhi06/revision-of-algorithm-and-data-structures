package com.revision.lambda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CFunctionalInterface {
    public static void main(String[] args) {
        Employee john = new Employee("John", 25);
        Employee ron = new Employee("Ron", 20);
        Employee jill = new Employee("Jill", 12);
        Employee randy = new Employee("Randy", 8);
        Employee milton = new Employee("Milton", 30);
        Employee roover = new Employee("Roover", 7);
        Employee nicholas = new Employee("Roger", 15);
        Employee steve = new Employee("Steve", 18);
        Employee pepe = new Employee("Pepe", 11);
        Employee menolas = new Employee("Menolas", 25);

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

        System.out.println("For each loop: ");
        employees.forEach(employee -> {
            System.out.println(employee.getName() + " " + employee.getAge());
        });

        System.out.println("\nVoters List: ");
        System.out.println("Normal for loop");
        for (Employee employee : employees) {
            if (employee.getAge() >= 18) {
                System.out.println(employee.getName() + " aged " + employee.getAge());
            }
        }

        System.out.println("\nFor each loop");
        employees.forEach(employee -> {
            if (employee.getAge() >= 18) {
                System.out.println(employee.getName() + " aged " + employee.getAge());
            }
        });

        System.out.println("\nPredicates");
        printEmployeeByAge(employees, "Employee aged till 17", employee -> employee.getAge() < 18);
        printEmployeeByAge(employees, "Voter's List: ", employee -> employee.getAge() >= 18);

        Predicate<Employee> toddler = employee -> employee.getAge() < 3;
        Predicate<Employee> youngster = employee -> employee.getAge() > 3 && employee.getAge() < 14;
        Predicate<Employee> teen = employee -> employee.getAge() > 14 && employee.getAge() < 21;
        Predicate<Employee> adult = employee -> employee.getAge() > 21;

        List<Employee>[] demographic = new List[ 4 ];
        for (int i = 0; i < demographic.length; i++) {
            demographic[ i ] = new ArrayList<>();
        }
        performDemographicAnalysis(employees, demographic, toddler, youngster, teen, adult);

        System.out.println("Youngsters: ");
        for (Employee employee : demographic[ 1 ]) {
            System.out.println(employee.getName() + " aged " + employee.getAge());
        }

        System.out.println("\nTraditional Random Number Generation");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000));
        }

        System.out.println("\nUsing Supplier");
        Random randomOne = new Random();
        Supplier<Integer> supplier = () -> randomOne.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(supplier.get());
        }
    }

    private static void performDemographicAnalysis(List<Employee> employees, List<Employee>[] demographic, Predicate<Employee> toddler, Predicate<Employee> youngster, Predicate<Employee> teen, Predicate<Employee> adult) {
        for (Employee employee : employees) {
            if (toddler.test(employee)) demographic[ 0 ].add(employee);
            else if (youngster.test(employee)) demographic[ 1 ].add(employee);
            else if (teen.test(employee)) demographic[ 2 ].add(employee);
            else if (adult.test(employee)) demographic[ 3 ].add(employee);
        }
    }

    private static void printEmployeeByAge(List<Employee> employees, String message, Predicate<Employee> predicate) {
        System.out.println(message);
        System.out.println("___________________________________________");
        for (Employee employee : employees) {
            if (predicate.test(employee)) System.out.println(employee.getName() + " aged " + employee.getAge());
        }
        System.out.println();
    }
}