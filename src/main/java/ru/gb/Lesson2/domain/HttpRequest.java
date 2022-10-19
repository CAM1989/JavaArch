package ru.gb.Lesson2.domain;

import ru.gb.Lesson2.SocketService;

import java.util.List;
import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private List<String> headers;

    private SocketService socketService;
    private String body;

    public HttpRequest(String method, String path, List<String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = (List<String>) headers;
        this.body = body;
    }

    public HttpRequest(SocketService socketService) {
        this.socketService = socketService;
    }


    public List<String> getHeaders() {
        return headers = socketService.readRequest();
    }
}
