package nextstep.mvc.tobe;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nextstep.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonViewTest {
    private static final Logger logger = LoggerFactory.getLogger(JsonViewTest.class);
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private View view;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        view = new JsonView();
    }

    @Test
    void render_no_element() throws Exception {
        view.render(new HashMap<>(), request, response);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).isBlank();
    }

    @Test
    void render_one_element() throws Exception {
        Map<String, Object> model = new HashMap<>();
        Car expected = new Car("Black", "Sonata");
        model.put("car", expected);

        view.render(model, request, response);

        Car actual = JsonUtils.toObject(response.getContentAsString(), Car.class);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void render_over_two_element() throws Exception {
        Map<String, Object> model = new HashMap<>();
        Car expected = new Car("Black", "Sonata");
        model.put("car", expected);
        model.put("name", "포비");

        view.render(model, request, response);

        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        logger.debug("response body : {}", response.getContentAsString());
    }

    @Test
    @DisplayName("전달하려는 모델의 개수가 1개일 때 value값 반환")
    void return_value() {
        JsonView jsonView = new JsonView();
        Map<String, Object> model = new HashMap<>();
        model.put("name", "포비");

        assertThat(jsonView.convertJsonFromModel(model)).isEqualTo("포비");
    }

    @Test
    @DisplayName("전달하려는 모델의 개수가 2개 이상일 때 map 반환")
    void return_map() {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonView jsonView = new JsonView();
        Car expectedCar = new Car("Black", "Sonata");
        Map<String, Object> expectedModels = new HashMap<>();

        expectedModels.put("car", expectedCar);
        expectedModels.put("name", "포비");

        Object actual = jsonView.convertJsonFromModel(expectedModels);

        Map<String, Object> actualModels = objectMapper.convertValue(
                actual, new TypeReference<Map<String, Object>>() {
                });

        Car actualCar = objectMapper.convertValue(expectedModels.get("car"), Car.class);

        assertThat(actualModels.get("name")).isEqualTo(expectedModels.get("name"));
        assertThat(actualCar).isEqualTo(expectedCar);
    }
}
