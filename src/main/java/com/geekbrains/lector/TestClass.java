package com.geekbrains.lector;

public class TestClass {

    @MyAnnotation(priority = 10)
    public  static void method1 () {
        System.out.println("method 1");
    }

    //@Test()//рядом с аннотациями иногда можно размещать данные. Откуда они берутся?
    public  static void method2 () {
        System.out.println("method 2");
    }

    @MyAnnotation(priority = 5)
    public  static void method3 () {
        System.out.println("method 3");
    }

}
