package next.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import next.optional.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertEquals;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    private Class<Question> clazz;
    private Class<User> userClass;
    private Student student;

    @BeforeEach
    void init() {
        student = new Student();
        clazz = Question.class;
        userClass = User.class;
    }

    // 요구사항 1 - 클래스 정보 출력
    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() throws Exception {
        logger.debug("Classes Name {}", clazz.getName());

        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            logger.debug("Constructor : {}", declaredConstructor);
        }
    }

    @Test
    void showFields() throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            logger.debug("Field : {}", declaredField);
        }
    }

    @Test
    void showMethods() throws Exception {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            logger.debug("Method : {}", declaredMethod);
        }
    }

    // 요구사항 4 - private field에 값 할당
    @Test
    public void privateFieldAndMethodAccess() throws Exception {
        Field nameField = student.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(student, "와우");

        Field ageField = student.getClass().getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(student, 60);

        Method getName = student.getClass().getDeclaredMethod("getName");
        Method getAge = student.getClass().getDeclaredMethod("getAge");
        getName.setAccessible(true);
        getAge.setAccessible(true);

        logger.debug("이름 : {}, 나이 : {}", getName.invoke(student), getAge.invoke(student));
    }

    // 요구사항 5 - 인자를 가진 생성자의 인스턴스 생성
    @Test
    void constructorNeedsParameter() throws Exception {
        String name = "이름";
        int age = 123;
        Constructor<?> constructor = userClass.getConstructor(String.class, Integer.class);
        logger.debug("생성자 : {}", constructor.toString());

        User user = (User)constructor.newInstance(name, age);
        assertEquals(name, user.getName());
        assertThat(age).isEqualTo(user.getAge());

    }

    @Test
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }
}