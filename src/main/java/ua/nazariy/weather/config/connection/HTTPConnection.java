package ua.nazariy.weather.config.connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class HTTPConnection extends Connection {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private HttpResponse<String> response;

    public void request(String url){
        request(url, new HashMap<>());
    }

    public void request(String url, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().GET().uri(URI.create(url));
        headers.forEach(requestBuilder::setHeader);
        try {
            response = httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse<String> response() {
        return response;
    }
}
