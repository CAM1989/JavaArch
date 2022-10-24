package ru.gb.patterns.composite;

public class Main {
    public static void main(String[] args) {
        Employee john = new Developer(10000, "Ivan Ivanov");
        Employee jane = new Developer(11000, "Eva Ivanov");

        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);
        System.out.println("Net salaries: " + organization.getNetSalaries());
    }
}