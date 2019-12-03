package slipp.controller;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import slipp.dto.UserCreatedDto;

import org.springframework.http.ResponseCookie;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest {
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

    @Test
    @DisplayName("요청한 회원이 존재할 때 회원 프로필 페이지로 이동")
    void get_user_profile_success() {
        byte[] responseBody = webTestClient.get()
                .uri("users/profile?userId=admin")
                .cookie("JSESSIONID", getCookie().getValue())
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult()
                .getResponseBody();

        String body = new String(responseBody);

        assertThat(body).contains("자바지기");
        assertThat(body).contains("admin@slipp.net");
    }

    @Test
    @DisplayName("비회원 유저리스트 요청 시 로그인 페이지로 이동")
    void move_list_page_with_not_logined_users() {
        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isFound()
                .expectHeader().valueMatches("location", ".*/users/loginForm");
    }

    @Test
    @DisplayName("회원 유저리스트 요청 시 리스트 페이지로 이동")
    void move_users_with_logined_users() {
        webTestClient.get()
                .uri("/users")
                .cookie("JSESSIONID", getCookie().getValue())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("회원 정보 수정 페이지로 이동")
    void move_update_page() {
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

    @Test
    @DisplayName("회원 정보 수정")
    void update_user() {
        webTestClient.post()
                .uri("/users/update")
                .body(BodyInserters.fromFormData("userId", "admin")
                        .with("password", "password").with("name", "자바지기")
                        .with("email", "admin@slipp.net"))
                .cookie("JSESSIONID", getCookie().getValue())
                .exchange()
                .expectStatus().isFound();
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
