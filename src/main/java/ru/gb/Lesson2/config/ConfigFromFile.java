package ru.gb.Lesson2.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile implements Config {
    private final String www;
    private final int port;

    public ConfigFromFile(String www) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(www));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.www = properties.getProperty("www");
        this.port = Integer.parseInt(properties.getProperty("port"));
    }

    @Override
    public String getWww() {
        return this.www;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
