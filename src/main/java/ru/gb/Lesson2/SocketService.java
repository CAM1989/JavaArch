package ru.gb.Lesson2;

import ru.gb.Lesson2.domain.HttpResponse;
import ru.gb.Lesson2.logger.Logger;
import ru.gb.Lesson2.logger.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SocketService implements Closeable {

    private static final Logger logger = LoggerFactory.create();

    private final Socket socket;

    public SocketService(Socket socket) {
        this.socket = socket;
    }

    public List<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready());

            List<String> request = new ArrayList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeResponse(HttpResponse response, ResponseSerializer serializer) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            String responseString = serializer.serialize(response);
            output.print(responseString);
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}