package nextstep.mvc.tobe.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextstep.mvc.tobe.ModelAndView;

public interface HandlerAdapter {
    ModelAndView handle(Object handle, HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse);

    boolean supports(Object handle);
}
