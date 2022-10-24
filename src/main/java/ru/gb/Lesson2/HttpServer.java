package ru.gb.Lesson2;

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
    private static final String WWW = "D:/Java/Arch/JavaArch/src/main/java/ru/gb/www/index.html";
    private static final Logger logger = LoggerFactory.create("ServerLog.txt");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                new Thread(new RequestHandler(RequestParserImpl.createRequestParser(),
                        socketService,
                        MethodHandlerFactory.create(socketService,
                                WWW))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}