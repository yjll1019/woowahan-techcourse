package nextstep.mvc.tobe;

import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import nextstep.mvc.HandlerMapping;
import nextstep.mvc.tobe.exception.DuplicateRequestMappingException;
import nextstep.mvc.tobe.scanner.ControllerScanner;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import org.reflections.ReflectionUtils;

public class AnnotationHandlerMapping implements HandlerMapping {
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void initialize() {
        Set<Class<?>> controllerClazz = ControllerScanner.scanController(basePackage);

        for (Class<?> clazz : controllerClazz) {
            appendHandlerExecutions(clazz);
        }
    }

    @Override
    public boolean isSupports(HttpServletRequest request) {
        return handlerExecutions.keySet()
                .stream()
                .anyMatch(key -> key.isSameUrl(request.getRequestURI()));
    }


    private void appendHandlerExecutions(final Class<?> clazz) {
        Set<Method> methods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));

        for (Method method : methods) {
            List<HandlerKey> handlerKeys = createHandlerKeys(method);

            for (HandlerKey key : handlerKeys) {
                HandlerExecution handlerExecution
                        = (request, response) -> (ModelAndView) method.invoke(clazz.newInstance(), request, response);

                checkDuplicateRequestMapping(key);
                handlerExecutions.put(key, handlerExecution);
            }
        }
    }

    private List<HandlerKey> createHandlerKeys(final Method method) {
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        String url = annotation.value();
        RequestMethod[] requestMethods = annotation.method();

        if (doseNotExistsRequestMethod(requestMethods)) {
            return addAllRequestMethod(url);
        }

        List<HandlerKey> handlerKeys = new ArrayList<>();
        for (RequestMethod requestMethod : requestMethods) {
            handlerKeys.add(new HandlerKey(url, requestMethod));
        }
        return handlerKeys;
    }

    private boolean doseNotExistsRequestMethod(RequestMethod[] requestMethods) {
        return requestMethods.length == 0;
    }

    private List<HandlerKey> addAllRequestMethod(String url) {
        List<HandlerKey> handlerKeys = new ArrayList<>();
        Arrays.stream(RequestMethod.values())
                .forEach(method -> handlerKeys.add(new HandlerKey(url, method)));
        return handlerKeys;
    }

    private void checkDuplicateRequestMapping(final HandlerKey handlerKey) {
        if (handlerExecutions.containsKey(handlerKey)) {
            throw new DuplicateRequestMappingException();
        }
    }

    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        return handlerExecutions.get(new HandlerKey(requestURI, RequestMethod.valueOf(method)));
    }
}
