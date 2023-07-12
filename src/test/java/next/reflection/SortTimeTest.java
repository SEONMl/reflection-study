package next.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortTimeTest {

    private final int SIZE = 30000;
    int[] arr;

    @ElapsedTime
    void bubbleSortTest() {
        arr = new int[SIZE];

        for (int i = 0; i < SIZE; i++){
            arr[i] = (int)(Math.random() * 100);
        }

        for (int i = 0; i < SIZE - 1; i++) {
            for(int j = 0;j<SIZE - i - 1;j++){
                if(arr[i] > arr[i+1])
                {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
    }

    @ElapsedTime
    void ArraySortTest() {
        arr = new int[SIZE];

        for (int i = 0; i < SIZE; i++){
            arr[i] = (int)(Math.random() * 100);
        }

        Arrays.sort(arr);
    }

    public static void main(String[] args) throws Exception {
        printTime(SortTimeTest.class.getName());
    }

    static void printTime(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        for (Method method : Arrays.asList(clazz.getDeclaredMethods())) {

            if(method.isAnnotationPresent(ElapsedTime.class)){
                System.out.println(method.getName()+"메소드 수행 시간을 측정합니다.");
                long startTime = System.currentTimeMillis();
                try{
                    method.invoke(clazz.getConstructor().newInstance());
                }catch(Exception e){
                    System.out.println(e.toString());
                }

                long endTime = System.currentTimeMillis();
                long takenTime = endTime - startTime;
                System.out.println("측정 시간 : "+takenTime +" milliseconds");
            }

        }
    }
}
