package ua.nazariy.weather.config.connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPConnection extends Connection {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private HttpRequest request;

    public void request(String url) {
        request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
    }

    public HttpResponse<String> response() {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
