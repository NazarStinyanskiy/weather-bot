package ua.nazariy.weather.config.connection;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class Connection {
    protected HttpRequest request;

    abstract public boolean connect();
    abstract public void request(String url);
    abstract public HttpResponse response();
}
