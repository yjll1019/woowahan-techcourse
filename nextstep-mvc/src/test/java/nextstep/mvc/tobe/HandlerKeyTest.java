package nextstep.mvc.tobe;

import nextstep.web.annotation.RequestMethod;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandlerKeyTest {

    @Test
    void isSameUrl() {
        String requestUrl = "/user";
        HandlerKey handlerKey = new HandlerKey(requestUrl, RequestMethod.GET);

        assertThat(handlerKey.isSameUrl(requestUrl)).isTrue();
    }
}