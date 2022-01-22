package com.geekbrains.lector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp2 {
    public static void main(String[] args) throws Exception {
        List<Method> executionList = (List<Method>) Arrays.stream(TestClass.class.getDeclaredMethods())
            .filter(m -> m.isAnnotationPresent(MyAnnotation.class))
                .sorted((o1, o2) -> o2.getAnnotation(MyAnnotation.class).priority() - o1.getAnnotation(MyAnnotation.class).priority())
                .collect(Collectors.toList());
        for (Method m:executionList){
            m.invoke(null);
        }
    }
}
