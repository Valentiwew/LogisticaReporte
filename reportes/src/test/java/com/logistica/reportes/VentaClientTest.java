package com.logistica.reportes;
import com.logistica.reportes.service.ProductoVentaClient;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

public class VentaClientTest {

    private MockWebServer mockWebServer;
    private ProductoVentaClient ventaClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        ventaClient = new ProductoVentaClient(webClient);

    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testObtenerVentas() {
        String mockJson = "[{\"id\":1,\"idUsuario\":3,\"total\":1500.0,\"fecha\":\"2024-07-02\"}]";

    mockWebServer.enqueue(new MockResponse()
            .setBody(mockJson)
            .addHeader("Content-Type", "application/json"));

    StepVerifier.create(ventaClient.getAllProductos())
            .expectNext(mockJson)
            .verifyComplete();
    }
}
