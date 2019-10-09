package slipp;

import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import nextstep.mvc.DispatcherServlet;
import nextstep.mvc.tobe.AnnotationHandlerMapping;
import nextstep.mvc.tobe.adapter.AnnotationHandlerAdapter;
import nextstep.mvc.tobe.adapter.ManualHandlerAdapter;
import nextstep.web.WebApplicationInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlippWebApplicationInitializer implements WebApplicationInitializer {
    private static final Logger log = LoggerFactory.getLogger(SlippWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(
                Arrays.asList(new ManualHandlerMapping(), new AnnotationHandlerMapping()),
                Arrays.asList(new ManualHandlerAdapter(), new AnnotationHandlerAdapter()));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        log.info("Start MyWebApplication Initializer");
    }
}