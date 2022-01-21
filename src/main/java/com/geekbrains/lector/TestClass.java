package com.geekbrains.lector;

public class TestClass {

    @MyAnnotation
    public  static void method1 () {
        System.out.println("method 1");
    }

    public  static void method2 () {
        System.out.println("method 2");
    }

    @MyAnnotation
    public  static void method3 () {
        System.out.println("method 3");
    }

}
