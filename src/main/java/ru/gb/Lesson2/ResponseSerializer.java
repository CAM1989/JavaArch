package ru.gb.Lesson2;

import ru.gb.Lesson2.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
