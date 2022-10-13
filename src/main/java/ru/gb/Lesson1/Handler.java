package ru.gb.Lesson1;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class Handler {
    private final Socket socket;
    private final String WWW;

    public Handler(Socket socket, String WWW) {
        this.socket = socket;
        this.WWW = WWW;
    }

    public void request() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())
        ) {
            while (!input.ready()) ;
            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            log.info(firstLine);
            while (input.ready()) {
                log.info(input.readLine());
            }
            Path path = Paths.get(WWW, parts[1]);
            Response.create(output, path);
            log.info("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Response {
        private static void create(PrintWriter output, Path path) throws IOException {
            if (!Files.exists(path)) {
                output.println("HTTP/1.1 404 NOT_FOUND");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<h1>Файл не найден!</h1>");
                output.flush();
                return;
            }
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            Files.newBufferedReader(path).transferTo(output);
        }
    }
}
