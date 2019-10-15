package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

class UserFormControllerTest {
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
    @DisplayName("회원가입 페이지로 이동")
    void move_user_form() {
        webTestClient.get()
                .uri("/users/form")
                .exchange()
                .expectStatus().isOk();
    }
}