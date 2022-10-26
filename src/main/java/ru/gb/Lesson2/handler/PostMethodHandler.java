package ru.gb.Lesson2.handler;

import ru.gb.Lesson2.config.Config;
import ru.gb.Lesson2.domain.HttpCode;
import ru.gb.Lesson2.domain.HttpRequest;
import ru.gb.Lesson2.domain.HttpResponse;

import java.io.StringReader;
import java.util.Map;

@Handler(order = 1)
public class PostMethodHandler extends MethodHandler {
    private final Config config;

    public PostMethodHandler(String method, MethodHandler next, Config config) {
        super(method, next);
        this.config = config;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createResponseBuilder()
                .withStatusCode(HttpCode.NOT_FOUND)
                .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                .withBody(new StringReader("<h1>Метод POST</h1>\n"))
                .build();
    }
}