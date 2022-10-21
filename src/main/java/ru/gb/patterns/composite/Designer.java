package ru.gb.patterns.composite;

import java.util.List;

public class Designer implements Employee{
    private int salary;
    private final String name;
    private List<String> roles;

    public Designer(int salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public List<String> getRoles() {
        return this.roles;
    }
}
