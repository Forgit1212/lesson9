package ru.geekBrains.titov.lesson9;

//Задачи:
// Создать валидатор на основе аннотаций:
//@Max(int length) - максимальное значение (все Number)
//@Min(int length) - минимальное значение (все Number)
//@Regexp(String regexp) - подходит ли по регулярному выражению (только String)
//@NotNull - применяется для всех ссылочных типов
//@NotEmpty - применяется для всех наследников Collections
//      Вместо интерфейса необходимо создать класс, который с помощью Reflection API будет делать валидацию по созданным аннотациям:
//class Validator {
//  boolean validate(Object obj);
//}

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Doc {

    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Max {
        int length() default Integer.MAX_VALUE; // все Number
    }

    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Min {
        int length() default Integer.MIN_VALUE; // все Number

    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Regexp { //применяется для строк, подходит ли по регулярному выражению
        String reg() default "\\D+";
    }

    //применяется для всех ссылочных типов
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface NotNull {
    }

    //@NotEmpty - применяется для всех наследников Collections
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface NotEmpty {
    }

}
