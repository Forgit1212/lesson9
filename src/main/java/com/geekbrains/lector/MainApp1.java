package com.geekbrains.lector;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class MainApp1 {
    public static void main(String[] args) {
        Class testClass = new TestClass.class;
        Method[] methods = testClass.getDeclaredMethods();
    }
}
