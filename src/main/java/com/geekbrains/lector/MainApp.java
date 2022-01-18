//Reflection API позволяет запрашивать любые данные любого объекта
package com.geekbrains.lector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) throws Exception {
        //Классы можно спрашивать абсолютно у всего:
        System.out.println(MainApp.class);
        System.out.println(String.class);
        System.out.println(int.class);
        System.out.println(int[].class);
        System.out.println(int[][].class);
        System.out.println(void.class);
        System.out.println("/n");

        //Есть несколько вариантов запроса этих классов:
        Class stringClass1 = "Java".getClass();//непосредственно у строки
        Class stringClass2 = String.class;//у класса строк
        Class stringClass3 = Class.forName("java.lang.String");//в этом случае запрос может выбросить Exception,
        //поскольку можем ошибиться в значении в скобках. Поэтому добавляем в метод throws Exception

        //Допустим, мы хотим получить ссылку на класс Cat
        Class classCat = Cat.class;
        //Теперь, подставив в classCat точку, мы получим доступ к множеству характеристик класса Cat
        classCat.getSimpleName(); //имя класса без пакета
        //Как получить методы, которые объявлены в классе Cat
        Method[] methods1 = classCat.getMethods(); //покажет все открытые public методы, доступные классу
        for (Method o : methods1){ //в этом случае мы не видим метод privateMeow(), потому что он приватный
            System.out.println(o.getName());
        }

        System.out.println("/n");
        Method[] methods2 = classCat.getDeclaredMethods();//
        for (Method o : methods2){ //в этом случае мы видим методы, объявленные внутри класса
            System.out.println(o.getName());
        }

        System.out.println("/n");
        //Что можно сделать с полученными методами? Их можно вызвать, только вызов будет не стандартный
        //Не cat.nameMetod, а:
        Cat cat = new Cat(1, 2,3);
        methods2[0].invoke(cat);
        //если метод приватный, перед вызовом мы должны дать к нему доступ, иначе получим Exception:
        methods2[1].setAccessible(true);
        methods2[1].invoke(cat);
        System.out.println("/n");

        //Подставив в метод точку, мы получим доступ к множеству характеристик метода
        int mods = methods2[0].getModifiers();
        System.out.println("isStatic = "+Modifier.isStatic(mods));
        System.out.println("isFinal = "+Modifier.isFinal(mods));
        System.out.println("isPublic = "+Modifier.isPublic(mods));
        System.out.println("/n");

        //Если хотим получить список полей, то:
        Field[] fields = classCat.getDeclaredFields();
        //если хотим спросить, чему, например, равняется дефолтное поле:
        System.out.println(Arrays.toString(fields));//выводим список всех полей;
        System.out.println(fields[1].get(cat));
        //если хотим изменить значение какого-то поля, порядок такой же:
        fields[1].set(cat, 20);

    }
}
