package ru.gb.Lesson2;

import java.util.List;

public interface RequestParser {

    default String parse(List<String> rawRequest) {
        String [] parts = rawRequest.get(0).split(" ");
        return parts[1];
    }

}
