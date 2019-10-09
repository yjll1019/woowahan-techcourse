package nextstep.mvc.tobe.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextstep.mvc.asis.Controller;
import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.mvc.tobe.exception.ManualMappingFailedException;

public class ManualHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    public ModelAndView handle(Object handler, HttpServletRequest req, HttpServletResponse resp) {
        try {
            String viewName = ((Controller) handler).execute(req, resp);
            return new ModelAndView(new JspView(viewName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ManualMappingFailedException();
    }
}
