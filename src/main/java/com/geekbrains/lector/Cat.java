//Создадим класс Cat, который будем исследовать на предмет работы Reflection API
package com.geekbrains.lector;

public class Cat {
    private int privateField;
    int defaultField;
    public int publicField;

    public Cat(int privateField, int defaultField, int publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.publicField = publicField;
    }

    public Cat() {
    }

    public final void publicMeow() {
        System.out.println("public meow");
    }

    public void privateMeow() {
        System.out.println("private meow");
    }
}
