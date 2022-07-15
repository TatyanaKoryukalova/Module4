package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest {
    private final String protocolVersion;
    private String method;
    private final BufferedReader reader;

    private static final String DEFAULT_PROTOCOL_VERSION = "HTTP/1.1";

    public HttpRequest(String protocolVersion, Socket client) throws IOException {
        this.protocolVersion = protocolVersion;
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public HttpRequest(Socket client) throws IOException {
        this(DEFAULT_PROTOCOL_VERSION, client);
    }

    public void update() throws IOException {
        parse(reader.readLine());
    }

    private void parse(String request) {
        String[] params = request.split(" ");
        method = params[0];
    }

    public String getProtocolVersion(){
        return protocolVersion;
    }

    public String getMethod() throws IOException {
        return method;
    }
}
