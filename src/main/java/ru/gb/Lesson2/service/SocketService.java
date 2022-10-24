package ru.gb.Lesson2.service;

import ru.gb.Lesson2.ResponseSerializer;
import ru.gb.Lesson2.domain.HttpResponse;

import java.io.*;
import java.util.List;

public interface SocketService extends Closeable {
    List<String> readRequest();
    void writeResponse(HttpResponse response, ResponseSerializer serializer);
}