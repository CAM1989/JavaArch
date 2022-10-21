package ru.gb.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public int getNetSalaries() {
        int netSalary = 0;

        for (Employee employee : employees) {
            netSalary += employee.getSalary();
        }
        return netSalary;
    }
}

