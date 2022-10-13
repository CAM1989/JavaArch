package ru.gb.Lesson1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;

@Slf4j
public class Server {
    private static int port = 8080;
    private static String WWW = "D:\\Java\\Arch\\JavaArch\\src\\main\\java\\ru\\gb\\www\\index.html";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server started!");
            Client client = new Client(serverSocket, WWW);
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
