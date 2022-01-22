package com.geekbrains.lector;

public class TestClass {

    @MyAnnotation(priority = 3)
    public  static void method1 () {
        System.out.println("method 1");
    }

    //@Test()//рядом с аннотациями иногда можно размещать данные. Откуда они берутся?
    @MyAnnotation(priority = 4)
    public  static void method2 () {
        System.out.println("method 2");
    }

    @MyAnnotation(priority = 2)
    public  static void method3 () {
        System.out.println("method 3");
    }

}
