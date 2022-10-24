package ru.gb.Lesson2.service;

import java.net.Socket;

public class SocketServiceFactory {

    public static SocketService createSocketService(Socket socket){
        return new SocketServiceImpl(socket);
    }
}