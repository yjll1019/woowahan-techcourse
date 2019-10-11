package nextstep.mvc.tobe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nextstep.web.support.MediaType;

public class JsonView implements View {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> modelToJson = convertJsonFromModel(model);

        writeResponse(response, modelToJson);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    private void writeResponse(HttpServletResponse response, List<String> modelToJson) throws IOException {
        for (String data : modelToJson) {
            response.getWriter().write(data);
        }
    }

    public List<String> convertJsonFromModel(Map<String, ?> model) throws JsonProcessingException {
        List<String> modelToJson = new ArrayList<>();

        for (String key : model.keySet()) {
            modelToJson.add(mapper.writeValueAsString(model.get(key)));
        }

        return modelToJson;
    }
}
