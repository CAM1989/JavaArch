package ru.gb.Lesson2;

import ru.gb.Lesson2.config.Config;
import ru.gb.Lesson2.config.ConfigFromFile;
import ru.gb.Lesson2.handler.MethodHandler;
import ru.gb.Lesson2.handler.MethodHandlerFactory;
import ru.gb.Lesson2.handler.RequestHandler;
import ru.gb.Lesson2.logger.Logger;
import ru.gb.Lesson2.logger.LoggerFactory;
import ru.gb.Lesson2.service.SocketService;
import ru.gb.Lesson2.service.SocketServiceFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = LoggerFactory.create("ServerLog.txt");
    public static void main(String[] args) {
        Config config = new ConfigFromFile("./../../../../server.properties");
        MethodHandler handler = MethodHandlerFactory.create(config);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                new Thread(new RequestHandler(RequestParserImpl.createRequestParser(),
                        socketService,handler)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}