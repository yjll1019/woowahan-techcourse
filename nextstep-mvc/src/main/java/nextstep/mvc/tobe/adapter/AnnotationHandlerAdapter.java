package nextstep.mvc.tobe.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextstep.mvc.tobe.HandlerExecution;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.mvc.tobe.exception.AnnotationMappingFailedException;

public class AnnotationHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object object) {
        return object instanceof HandlerExecution;
    }

    @Override
    public ModelAndView handle(Object handler, HttpServletRequest req,
                               HttpServletResponse resp) {
        try {
            return ((HandlerExecution) handler).handle(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new AnnotationMappingFailedException();
    }
}
