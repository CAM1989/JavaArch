package ru.gb.Lesson2.domain;

import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    private HttpRequest() {
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
    public static RequestBuilder createRequestBuilder(){
        return new RequestBuilder();
    }

    public static class RequestBuilder {
        private final HttpRequest request = new HttpRequest();

        private RequestBuilder() {
        }

        public RequestBuilder withMethod(String method) {
            this.request.method = method;
            return this;
        }

        public RequestBuilder withPath(String path) {
            this.request.path = path;
            return this;
        }

        public RequestBuilder withHeader(Map<String, String> headers) {
            this.request.headers = headers;
            return this;
        }

        public RequestBuilder withBody(String body) {
            this.request.body = body;
            return this;
        }

        public HttpRequest build(){
            return request;
        }
    }


}