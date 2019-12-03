package reflection;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug("class name : " + clazz.getName());

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            logger.debug("field : " + field);
        }

        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            logger.debug("constructor : " + constructor);
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            logger.debug("method : " + method);
        }
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void constructor_with_args() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }

        long questionId = 1L;
        String writer = "writer";
        String title = "title";
        String contents = "contents";
        Date date = new Date();
        int countOfComment = 2;

        Constructor<Question> constructor = clazz.getDeclaredConstructor(String.class, String.class, String.class);
        Question question = constructor.newInstance(writer, title, contents);

        assertThat(question.getWriter()).isEqualTo(writer);
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getContents()).isEqualTo(contents);

        Constructor<Question> fullConstructor = clazz.getDeclaredConstructor(long.class, String.class, String.class, String.class, Date.class, int.class);
        Question questionByFullConstructor = fullConstructor.newInstance(questionId, writer, title, contents, date, countOfComment);

        assertThat(questionByFullConstructor.getQuestionId()).isEqualTo(questionId);
        assertThat(questionByFullConstructor.getWriter()).isEqualTo(writer);
        assertThat(questionByFullConstructor.getTitle()).isEqualTo(title);
        assertThat(questionByFullConstructor.getContents()).isEqualTo(contents);
        assertThat(questionByFullConstructor.getCreatedDate()).isEqualTo(date);
        assertThat(questionByFullConstructor.getCountOfComment()).isEqualTo(countOfComment);
    }

    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());

        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);

        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);

        Student student = new Student();
        String name = "재성";
        int age = 20;

        nameField.set(student, name);
        ageField.set(student, age);

        assertThat(student.getName()).isEqualTo(name);
        assertThat(student.getAge()).isEqualTo(age);
    }
}
