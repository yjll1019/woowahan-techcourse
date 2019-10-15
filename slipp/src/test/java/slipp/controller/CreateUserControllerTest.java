package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import slipp.dto.UserCreatedDto;

import org.springframework.test.web.reactive.server.WebTestClient;

class CreateUserControllerTest {
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
    @DisplayName("회원가입 테스트")
    void create_user() {
        UserCreatedDto expected =
                new UserCreatedDto("pobi", "password", "포비", "pobi@nextstep.camp");

        webTestClient.post()
                .uri("/users/create")
                .body(Mono.just(expected), UserCreatedDto.class)
                .exchange()
                .expectStatus().isFound()
                .expectHeader().valueMatches("location", "/");
    }
}