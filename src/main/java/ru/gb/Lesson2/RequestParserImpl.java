package ru.gb.Lesson2;

import ru.gb.Lesson2.domain.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParserImpl implements RequestParser {
    public static RequestParserImpl createRequestParser(){
        return new RequestParserImpl();
    }

    private RequestParserImpl() {
    }

    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String method = rawRequest.get(0).split(" ")[0];

        String path = rawRequest.get(0).split(" ")[1];
        String body;

        Map<String, String> headers = new HashMap<>();
        int count = 2;
        for (int i = 2; i < rawRequest.size(); i++) {
            String[] temp = rawRequest.get(i).split(": ");
            if(temp.length == 1){
                break;
            }
            headers.put(temp[0], temp[1]);
            count++;
        }
        StringBuilder builder = new StringBuilder();
        if(count == rawRequest.size()){
            body = "";
        } else{
            for (int i = count; i < rawRequest.size(); i++) {
                builder.append(rawRequest.get(i)).append("\n");
            }
            body = builder.toString();
        }
        HttpRequest request = HttpRequest.createRequestBuilder()
                .withMethod(method)
                .withPath(path)
                .withHeader(headers)
                .withBody(body)
                .build();

        return request;
    }
}