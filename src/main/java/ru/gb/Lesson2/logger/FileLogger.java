package ru.gb.Lesson2.logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private final String fileName;

    public FileLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void info(String msg) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            System.out.println(msg);
            writer.write(msg);
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}