package com.revision.lambda;

import java.util.*;

public class BSorter {
    public static void main(String[] args) {
        Employee john = new Employee("John", 25);
        Employee ron = new Employee("Ron", 20);
        Employee jill = new Employee("Jill", 22);
        Employee randy = new Employee("Randy", 8);
        Employee milton = new Employee("Milton", 30);
        Employee roover = new Employee("Roover", 7);
        Employee nicholas = new Employee("Nicholas", 15);

        List<Employee> employees = new LinkedList<>();
        employees.add(john);
        employees.add(ron);
        employees.add(jill);
        employees.add(randy);
        employees.add(milton);
        employees.add(roover);
        employees.add(nicholas);

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        System.out.println("Sorted by name");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " is aged " + employee.getAge());
        }

        Collections.sort(employees, (Employee employee1, Employee employee2) -> {
            if (employee1.getAge() > employee2.getAge()) return 1;
            else if (employee1.getAge() == employee2.getAge()) return 0;
            return -1;
        });

        System.out.println("\nSorted by age");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " is aged " + employee.getAge());
        }

        System.out.println("\nFilter by using Interface implementation");
        EmployeeFilter employeeSorter = new EmployeeFilter();
        String startsWith = "m";
        List<Employee> result = employeeSorter.filter(employees, startsWith);
        System.out.println("Employees whose name starts with " + startsWith.toUpperCase());
        if (result.size() == 0) {
            System.out.println("No matches found...");
        } else {
            for (Employee employee : result) {
                System.out.println(employee.getName() + " is aged " + employee.getAge());
            }
        }

        System.out.println("\nFor each loop: ");
        employees.forEach(employee -> {
            System.out.println(employee.getName() + " " + employee.getAge());
        });
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class EmployeeFilter implements Filter {
    public List<Employee> filter(List<Employee> list, String regex) {
        List<Employee> filteredList = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getName().toLowerCase().startsWith(regex.toLowerCase())) filteredList.add(employee);
        }
        return filteredList;
    }
}

@FunctionalInterface
interface Filter {
    public List<Employee> filter(List<Employee> list, String regex);
}