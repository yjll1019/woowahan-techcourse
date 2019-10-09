package nextstep.mvc.tobe.scanner;

import java.util.Set;

import nextstep.web.annotation.Controller;
import org.reflections.Reflections;

public class ControllerScanner {
    public static Set<Class<?>> scanController(Object... basePackage) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(Controller.class);
    }
}
