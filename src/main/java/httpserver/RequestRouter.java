package httpserver;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class RequestRouter implements Runnable {
    private static final File pageGet = new File("htmlsources", "get.html");
    private static final File pagePost = new File("htmlsources", "post.html");

    private final Socket client;

    private final HttpRequest request;
    private final HttpResponse response;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    RequestRouter(Socket client) throws IOException {
        this.client = client;
        request = new HttpRequest(client);
        response = new HttpResponse(client);
    }

    public void run() {
        try {
            request.update();
            switch (request.getMethod()) {
                case "GET" -> get();
                case "POST" -> post();
                default -> badRequest();
            }
        } catch (Exception exception) {
            logger.log(Level.WARNING, exception.getMessage(), exception);
        } finally {
            try {
                client.close();
            } catch (Exception exception) {
                logger.log(Level.WARNING, exception.getMessage(), exception);
            }
        }
        logger.info("Client processing finished");
    }

    private void badRequest() throws IOException {
        response.setStatusCode(HttpStatusCode.BAD_REQUEST);
        response.send();
    }

    private void post() throws IOException {
        response.setStatusCode(HttpStatusCode.OK);
        response.send(pagePost);
    }

    private void get() throws IOException {
        response.setStatusCode(HttpStatusCode.OK);
        response.send(pageGet);
    }
}