package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

class HomeControllerTest {
    private static final String BASE_URL = "http://localhost";

    private int port;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        this.port = 8080;
        this.webTestClient = WebTestClient
                .bindToServer()
                .baseUrl(BASE_URL + ":" + port)
                .build();
    }

    @Test
    void move_home() {
       byte[] responseBody = webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
               .expectBody().returnResult()
               .getResponseBody();

       String body = new String(responseBody);

       assertThat(body).contains("자바지기");
    }
}