package httpserver;

import httpserver.exceptions.EmptyHttpStatusCodeException;

import java.io.*;
import java.net.Socket;

public class HttpResponse {
    private final String protocolVersion;
    private String contentType;
    private HttpStatusCode statusCode;

    private static final String DEFAULT_PROTOCOL_VERSION = "HTTP/1.1";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";

    private final BufferedOutputStream writer;

    public HttpResponse(String protocolVersion, String contentType, Socket client) throws IOException {
        this.protocolVersion = protocolVersion;
        this.contentType = contentType;
        writer = new BufferedOutputStream(client.getOutputStream());
    }

    public HttpResponse(Socket client) throws IOException {
        this(DEFAULT_PROTOCOL_VERSION, DEFAULT_CONTENT_TYPE, client);
    }

    private void writeHeaders() throws IOException {
        if (statusCode == null){
            throw new EmptyHttpStatusCodeException("Status code not specified");
        }
        String response = protocolVersion
                + " " + statusCode.getCode()
                + " " + statusCode.getStatus() + '\n'
                + "Content-Type: " + contentType + "\r\n\r\n";
        writer.write(response.getBytes());

    }

    public boolean send(File file) throws IOException {
        writeHeaders();
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = fileReader.readLine()) != null) {
            response.append(line);
        }
        writer.write(response.toString().getBytes());
        writer.flush();
        fileReader.close();
        return true;
    }

    public boolean send(String request) throws IOException {
        writeHeaders();
        writer.write(request.getBytes());
        writer.flush();
        return true;
    }

    public boolean send() throws IOException {
        writeHeaders();
        writer.flush();
        return true;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
