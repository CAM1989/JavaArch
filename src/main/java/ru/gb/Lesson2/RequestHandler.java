package ru.gb.Lesson2;

import ru.gb.Lesson2.domain.HttpRequest;
import ru.gb.Lesson2.domain.HttpResponse;
import ru.gb.Lesson2.logger.ConsoleLogger;
import ru.gb.Lesson2.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable, RequestParser, ResponseSerializer {

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {

        this.socketService = socketService;
    }

    @Override
    public void run(){
        HttpRequest httpRequest = new HttpRequest(socketService);
        HttpResponse httpResponse = new HttpResponse(socketService);

        Path path = Paths.get(HttpResponse.getWWW(), parse(httpRequest.getHeaders()));
        if (!Files.exists(path)) {
            try {
                httpResponse.getSocketService().writeResponse(httpResponse.getStatusCode(), dontAnswer(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            httpResponse.getSocketService().writeResponse(httpResponse.getStatusCode(), answerRequest(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Client disconnected!");
    }
}
