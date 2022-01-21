//Reflection API позволяет запрашивать любые данные любого объекта
package com.geekbrains.lector;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
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
        //если поле приватное, поступаем, как с методами

        //Можем создавать объекты:
        Cat cat2 = (Cat) classCat.newInstance();//для дефолтного конструктора. Если дефолтного конструктора
        //нет, получим Exception. Если хотим создать объект конструктором с параметрами:
        Cat cat3 = (Cat) classCat
                .getConstructor (int.class, int.class, int.class)
                .newInstance(20,30,40);

        //С помощью Reflection мы можем находу подключать классы, о которых ничего не знаем
        //За загрузку классов отвечают лоудеры, с помощью которых мы можем загрузить класс из любого места
        ClassLoader classLoader = new URLClassLoader(new URL[]{new File("C:/Drivers").toURL()});
        Class humanClass = classLoader.loadClass("Human"); //не работает. Скорее всего надо переопределять Security Manager, который не разрешает
        //classLoader совершать загрузку.

        //Попробуем поработать с Объектом этого класса:
        Object humanObj = humanClass.getConstructor(String.class, int.class).newInstance("Bob", 30);
        Method greetingsMethod = humanClass.getDeclaredMethod("greetings");
        greetingsMethod.invoke(humanObj);

    }
}
