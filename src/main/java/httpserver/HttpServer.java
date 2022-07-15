package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * HTTP сервер на сырых TCP сокетах
 */
public class HttpServer {
    private final ServerSocket serverSocket;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final static int PORT = 8080;

    public HttpServer() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void run() throws IOException {
        while (true) {
            logger.info("Server started");
            Socket client = serverSocket.accept();
            logger.info("Client accepted");
            new Thread(new RequestRouter(client)).start();
        }
    }
}
