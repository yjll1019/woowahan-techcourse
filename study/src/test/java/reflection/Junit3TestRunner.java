package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Junit3TestRunner {

    private static final String TEST_PREFIX = "test";

    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().startsWith(TEST_PREFIX)) {
                method.invoke(clazz.newInstance());
            }
        }
    }
}
