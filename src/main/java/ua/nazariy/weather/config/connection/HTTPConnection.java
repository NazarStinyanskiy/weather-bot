package ua.nazariy.weather.config.connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPConnection extends Connection {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public void request(String url) {
        this.request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
    }

    @Override
    public HttpResponse<String> response() {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
