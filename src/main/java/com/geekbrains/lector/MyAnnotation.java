package com.geekbrains.lector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//когда мы создаем свою собственную аннотацию, к ней нужно подцепить две аннотации:
@Retention(RetentionPolicy.RUNTIME)//это то, где будет видна наша аннотация:(
//SOURCE - в исходном коде, т.е. это просто разметка класса, которая поможет программистам понять, что с этим классом делать.;
//При этом в момент компиляции все эти аннотации исчезают;
//CLASS - аннотация остается в скомпилированном коде, но при этом её нельзя использовать в процессе выполнения программы;
//RUNTIME - аннотацию можно видеть в процессе выполнения программы;

@Target(ElementType.METHOD)//это то, куда мы хотим цеплять эти аннотации. Здесь можно указать одно значение, либо массив значений.
//Допустим, мы хотим подключать к методам. Здесь можно указать FIELD - это поля, TYPE - это класс и т.д.

//Создали, что теперь нам с этим делать? Давайте создадим какой-то TestClass, в котором будет какое-то количество методов
//Теперь наша задача взять этот класс и вызвать те методы, которые помечены нашими аннотациями
//При запуске MainApp1 выведет method1 method2

public @interface MyAnnotation {
    //Если в аннотации мы хотим получить дополнительные данные:
    //Допустим, у аннотации есть какой-то приоритет
    int priority() default 5;
    //Теперь в вызывающем классе при вводе аннатации, нас попросят указать приоритет
    //Но указать здесь ограничения, например от 1 до 100 здесь не получится, но ничего не мешает
    //разместить эту проверку в обработчике аннотаций
    //Парраметров в аннотациях м.б. сколько угодно: время выполнения, название и т.д.
    //Как это использовать? Давайте решим такую задачу, что в классе TestClass у нас все 3 аннотации
    //помечены с приоритетом, и мы хотим их вызвать в каком-то порядке в зависимости от приоритета
    //Для этого создадим executionList и в обработчике аннотаций добавим в него все методы с
    //аннотацией. Теперь мы хотим этот список отсортировать
}
