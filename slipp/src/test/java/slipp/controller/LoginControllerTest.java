package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

class LoginControllerTest {
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
    void login_success() {
        webTestClient.post()
                .uri("/users/login")
                .body(BodyInserters.fromFormData("userId", "admin")
                        .with("password", "password"))
                .exchange()
                .expectStatus().isFound()
                .expectHeader().valueMatches("location", "/");
    }

    @Test
    void login_failed() {
        webTestClient.post()
                .uri("/users/login")
                .body(BodyInserters.fromFormData("userId", "admin")
                        .with("password", "wrong"))
                .exchange()
                .expectStatus().isOk();
    }
}