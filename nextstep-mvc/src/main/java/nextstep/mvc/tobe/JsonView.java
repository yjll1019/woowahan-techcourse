package nextstep.mvc.tobe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import nextstep.mvc.tobe.exception.NotFoundModelException;
import nextstep.web.support.MediaType;

public class JsonView implements View {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (model.size() == 0) {
            return;
        }

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Object responseData = convertJsonFromModel(model);
        writeResponse(response.getWriter(), responseData);
    }

    private void writeResponse(PrintWriter printer, Object model) throws IOException {
        String responseData = mapper.writeValueAsString(model);

        printer.write(responseData);
    }

    public Object convertJsonFromModel(Map<String, ?> model) {
        if (model.size() == 1) {
            return getValue(model);
        }

        return model;
    }

    private Object getValue(Map<String, ?> model) {
        return model.values()
                .stream()
                .findFirst()
                .orElseThrow(NotFoundModelException::new);
    }
}
