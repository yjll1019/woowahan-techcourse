package nextstep.mvc;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    void initialize();

    boolean isSupports(HttpServletRequest request);

    Object getHandler(HttpServletRequest request);
}
