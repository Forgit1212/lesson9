package com.geekbrains.hm;

public class Human {
    String name;
    int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greeting() {
        System.out.println("Hello, "+name);
    }
}
