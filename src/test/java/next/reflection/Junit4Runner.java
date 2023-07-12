package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Junit4Runner {
    @Test
    public void run() throws Exception {
        Class clazz = Junit4Test.class;
        // 요구사항 3 - @Test 애노테이션 메소드 실행

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method : declaredMethods){
            if(method.isAnnotationPresent(MyTest.class)){
                method.invoke(clazz.getConstructor().newInstance()); // deprecated ?
            }
        }
    }
}


