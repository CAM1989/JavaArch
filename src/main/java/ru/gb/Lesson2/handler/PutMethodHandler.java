package ru.gb.Lesson2.handler;

import ru.gb.Lesson2.domain.HttpRequest;
import ru.gb.Lesson2.domain.HttpResponse;
import ru.gb.Lesson2.service.SocketService;

import java.io.StringReader;
import java.util.Map;

@Handler(order = 2)
public class PutMethodHandler extends MethodHandler {
    private final String www;

    public PutMethodHandler(String method, MethodHandler next, SocketService socketService, String www) {
        super(method, next, socketService);
        this.www = www;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createResponseBuilder()
                .withStatusCode(404)
                .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                .withBody(new StringReader("<h1>Метод PUT</h1>\n"))
                .build();
    }
}