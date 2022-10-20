package ru.gb.Lesson2;

import ru.gb.Lesson2.logger.Logger;
import ru.gb.Lesson2.logger.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = LoggerFactory.create("Logs.txt");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}