package reflection;

import annotation.Controller;
import annotation.Repository;
import annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ReflectionsTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionsTest.class);

    @Test
    public void showAnnotationClass() throws Exception {
        Reflections reflections = new Reflections("examples");

        Set<Class<?>> controllerClazz = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> clazz : controllerClazz) {
            logger.debug("@Controller : " + clazz.getName());
        }

        Set<Class<?>> serviceClazz = reflections.getTypesAnnotatedWith(Service.class);
        for (Class<?> clazz : serviceClazz) {
            logger.debug("@Service : " + clazz.getName());
        }

        Set<Class<?>> repositoryClazz = reflections.getTypesAnnotatedWith(Repository.class);
        for (Class<?> clazz : repositoryClazz) {
            logger.debug("@Repository : " + clazz.getName());
        }
    }
}
