package ru.gb.patterns.composite;

import java.util.List;

public interface Employee {
    String getName();

    void setSalary(int salary);

    int getSalary();

    List<String> getRoles();
}