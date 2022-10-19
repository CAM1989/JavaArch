package ru.gb.Lesson2.domain;

import ru.gb.Lesson2.SocketService;

public class HttpResponse {

    private SocketService socketService;
    private String statusCode;

    private static   String WWW = "D:\\Java\\Arch\\JavaArch\\src\\main\\java\\ru\\gb\\www\\index.html";

    private String file;

    public HttpResponse( String statusCode, String www, String file) {

        this.statusCode = statusCode;
        this.WWW = www;
        this.file = file;
    }

    public HttpResponse(SocketService socketService) {
        this.socketService = socketService;
    }

    public static   String getWWW() {
        return WWW;
    }

    public String getFile() {
        return file = ("HTTP/1.1 200 OK\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "\n");
    }

    public SocketService getSocketService() {
        return socketService = socketService;
    }

    public String getStatusCode() {
        return statusCode = ("HTTP/1.1 404 NOT_FOUND\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "\n");
    }
}

