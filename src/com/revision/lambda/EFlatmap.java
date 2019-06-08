package com.revision.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EFlatmap {
    public static void main(String[] args) {
        Employee john = new Employee("John", 25);
        Employee ron = new Employee("Ron", 20);
        Employee jill = new Employee("Jill", 22);
        Employee randy = new Employee("Randy", 8);
        Employee milton = new Employee("Milton", 30);
        Employee roover = new Employee("Roover", 7);
        Employee nicholas = new Employee("Nicholas", 15);

        Department science = new Department("Science");
        science.addEmployee(john);
        science.addEmployee(randy);
        science.addEmployee(roover);
        Department maths = new Department("Maths");
        maths.addEmployee(ron);
        maths.addEmployee(nicholas);
        Department social = new Department("Social");
        social.addEmployee(jill);
        social.addEmployee(milton);

        List<Department> departments = new ArrayList<>();
        departments.add(science);
        departments.add(maths);
        departments.add(social);
        departments.stream().flatMap(department -> department.getEmployees().stream()).forEach(System.out::println);
        Predicate<Department> scienceDepartment = (Department department) -> department.getDeptName().equalsIgnoreCase("Science");
        List<Employee> employeesList = departments.stream().filter(scienceDepartment).flatMap(department -> department.getEmployees().stream()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("\nScience Department");
        for (Employee employee : employeesList) {
            System.out.println(employee.getName());
        }
        System.out.print("\nEmployee from each department");
        departments.stream().peek(department -> System.out.println("\n"+department.getDeptName() + " Department")).flatMap(department -> department.getEmployees().stream()).forEach(System.out::println);
    }
}

class Department {
    private String deptName;
    private List<Employee> employees;

    public Department(String deptName) {
        this.deptName = deptName;
        this.employees = new ArrayList<>();
    }

    public String getDeptName() {
        return deptName;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}