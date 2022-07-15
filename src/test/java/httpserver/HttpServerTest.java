package httpserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpServerTest {

    @Test
    void run() throws IOException {
        new HttpServer().run();
    }
}