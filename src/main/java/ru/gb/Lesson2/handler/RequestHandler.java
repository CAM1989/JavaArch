package ru.gb.Lesson2.handler;

import ru.gb.Lesson2.RequestParser;
import ru.gb.Lesson2.domain.HttpRequest;
import ru.gb.Lesson2.service.SocketService;
import ru.gb.Lesson2.logger.Logger;
import ru.gb.Lesson2.logger.LoggerFactory;

import java.io.*;
import java.util.List;

public class RequestHandler implements Runnable {
    private RequestParser requestParser;
    private static final Logger logger = LoggerFactory.create("RequestHandlerLog.txt");

    private final SocketService socketService;
    private MethodHandler handler;

    public RequestHandler(RequestParser requestParser, SocketService socketService, MethodHandler handler) {
        this.requestParser = requestParser;
        this.socketService = socketService;
        this.handler = handler;
    }

    @Override
    public void run() {
        List<String> requests = socketService.readRequest();
        HttpRequest request = requestParser.parse(requests);
        handler.handle(request);
        try{socketService.close();}
        catch (IOException e){
            throw new IllegalStateException(e);
        }

        logger.info("Client disconnected!");
    }
}