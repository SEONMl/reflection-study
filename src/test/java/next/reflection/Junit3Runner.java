package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Junit3Runner {

    public static final String TEST = "test";

    @Test
    public void runner() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();

        // 요구사항 2 - test로 시작하는 메소드 실행
        for(Method method : declaredMethods){
            if(method.getName().startsWith(TEST)){
                method.invoke(clazz.getConstructor().newInstance()); // deprecated ?
            }
        }
    }
}
