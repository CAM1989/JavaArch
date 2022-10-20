package ru.gb.Lesson2;

import ru.gb.Lesson2.domain.HttpRequest;

import java.util.List;

public interface RequestParser {

    HttpRequest parse(List<String> rawRequest);
}
