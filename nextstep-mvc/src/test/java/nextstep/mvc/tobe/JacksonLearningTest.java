package nextstep.mvc.tobe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JacksonLearningTest {
    private ObjectMapper objectMapper;
    private Car actualCar;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        actualCar = new Car("Black", "Sonata");
    }

    @Test
    void convert_one_json_to_object() throws IOException {
        String jsonOfCar = objectMapper.writeValueAsString(actualCar);

        Car expected = objectMapper.readValue(jsonOfCar, Car.class);
        assertThat(actualCar).isEqualTo(expected);
    }

    @Test
    void convert_over_two_json_to_list() throws IOException {
        Car actualCar2 = new Car("Grey", "Sonata");
        List<Car> actualModels = new ArrayList<>();

        actualModels.add(actualCar);
        actualModels.add(actualCar2);

        String jsonOfCars = objectMapper.writeValueAsString(actualModels);

        List<Car> actualCars = objectMapper.readValue(jsonOfCars, new TypeReference<List<Car>>() {
        });

        assertThat(actualModels).isEqualTo(actualCars);
    }

    @Test
    void convert_over_two_json_to_map() throws IOException {
        Map<String, Object> actualModels = new HashMap<>();

        actualModels.put("userId", "javagiji");
        actualModels.put("car", actualCar);

        String jsonOfModels = objectMapper.writeValueAsString(actualModels);
        System.out.println(actualModels);

        Map<String, Object> expectedModels = objectMapper.readValue(jsonOfModels,
                new TypeReference<Map<String, Object>>() {
                });

        Car expectedCar = objectMapper.convertValue(expectedModels.get("car"), Car.class);

        assertThat(actualModels.get("userId")).isEqualTo(expectedModels.get("userId"));
        assertThat(actualModels.get("car")).isEqualTo(expectedCar);
    }
}