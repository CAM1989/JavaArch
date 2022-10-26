package ru.gb.Lesson2;

import ru.gb.Lesson2.domain.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ResponseSerializerImpl implements ResponseSerializer {
    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 ");
        builder.append(httpResponse.getStatusCode().getStatus()).append(" ").append(httpResponse.getStatusCode().getName()).append("\n");
        for (Map.Entry<String, String> header : httpResponse.getHeaders().entrySet()) {
            builder.append(header.getKey()).append(": ").append(header.getValue()).append("\n\n");
        }
        BufferedReader reader = new BufferedReader(httpResponse.getBody());
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}