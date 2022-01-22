package com.geekbrains.lector;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp1 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class testClass = TestClass.class;//этот код по сути - обработчик аннотаций
        Method[] methods = testClass.getDeclaredMethods();
        List<Method> executionList = new ArrayList<>();
        for (Method o:methods){
            if(o.isAnnotationPresent(MyAnnotation.class)){
                o.invoke(null);//это статический метод, поэтому делаем null
                //при запуске выведет: method1 method2
                executionList.add(o);
            };
        }
        executionList.sort((o1, o2) -> o2.getAnnotation(MyAnnotation.class).priority() - o1.getAnnotation(MyAnnotation.class).priority());
        //затем в цикле перебираем отсортированные методы.
        //Способ через Stream в классе MainApp2.
    }
}
