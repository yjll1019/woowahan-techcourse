package slipp.controller;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseCookie;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateFormUserControllerTest {
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
    @DisplayName("로그인 시 회원 정보 수정 페이지로 이동")
    void update_user() {
        byte[] responseBody = webTestClient.get()
                .uri("/users/updateForm?userId=admin")
                .cookie("JSESSIONID", getCookie().getValue())
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult()
                .getResponseBody();

        String body = new String(responseBody);

        assertThat(body).contains("자바지기");
        assertThat(body).contains("admin@slipp.net");
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