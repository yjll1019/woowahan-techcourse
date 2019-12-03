package slipp.controller;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseCookie;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

class LogoutControllerTest {
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
    @DisplayName("회원 로그아웃 시 메인 페이지로 이동")
    void logout() {
        webTestClient.get()
                .uri("/users/logout")
                .cookie("JSESSIONID", getCookie().getValue())
                .exchange()
                .expectStatus().isFound()
                .expectHeader().valueMatches("Location", "/");
    }

    private ResponseCookie getCookie() {
        return webTestClient.mutate()
                .responseTimeout(Duration.ofMillis(70000))
                .build()
                .post()
                .uri("/users/login")
                .body(BodyInserters.fromFormData("userId", "admin")
                        .with("password", "password"))
                .exchange()
                .expectStatus().isFound()
                .expectHeader().valueMatches("location", "/")
                .returnResult(String.class)
                .getResponseCookies().get("JSESSIONID").get(0);
    }
}