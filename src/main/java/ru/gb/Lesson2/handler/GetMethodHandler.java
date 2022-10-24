package ru.gb.Lesson2.handler;

import ru.gb.Lesson2.domain.HttpRequest;
import ru.gb.Lesson2.domain.HttpResponse;
import ru.gb.Lesson2.service.SocketService;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Handler(order = 0)
public class GetMethodHandler extends MethodHandler {
    private final String www;

    public GetMethodHandler(String method, MethodHandler next, SocketService socketService, String www) {
        super(method, next, socketService);
        this.www = www;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        Path path = Paths.get(www, request.getPath());
        if (!Files.exists(path)) {
            return HttpResponse.createResponseBuilder()
                    .withStatusCode(404)
                    .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                    .build();
        }

        try {
            return HttpResponse.createResponseBuilder()
                    .withStatusCode(200)
                    .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .withBody(Files.newBufferedReader(path))
                    .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}