package ru.gb.Lesson1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Client {
    ServerSocket serverSocket;
    String WWW;

    public Client(ServerSocket serverSocket, String WWW) {
        this.serverSocket = serverSocket;
        this.WWW = WWW;
    }

    public void connect() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            log.info("New client connected!");
            Handler handler = new Handler(socket, WWW);
            new Thread(handler::request).start();
        }
    }
}
