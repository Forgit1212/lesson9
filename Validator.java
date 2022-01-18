package ru.geekBrains.titov.lesson9;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.Number;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Doc {

    boolean validate(Object obj) throws IllegalAccessException {

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields(); //Получил все поля класса

        for (Field field : fields) {

            field.setAccessible(true); // разрешил доступ к private полям
            Object value = field.get(obj); //Получил значение поля
            checkAnnotation(field, value);
        }

        return true;
    }

    private boolean checkAnnotation(Field field, Object value)  {

        Annotation[] annotations = field.getAnnotations();

        for (Annotation annotation : annotations) {

            if (value instanceof Number) { //Это Number!

                if (annotation != null && annotation.annotationType().equals(Max.class)) {
                    Max max = (Max) annotation;
                    System.out.println("max = " + max.length());


                    if ((((Number) value).doubleValue()) - max.length() > 0.00001) {
                        System.out.println(field + ": " + value + " > " + "max.length() = " + max.length() +" " +true);
                        return false;
                    }
                }
                if (annotation != null && annotation.annotationType().equals(Min.class)) {
                    Min min = (Min) annotation;

                    if (((Number) value).doubleValue() - min.length() < 0.00001) {
                        System.out.println(field + ": " + value + " < " + "min.length() = " + min.length() + " " + false);
                        return false;
                    }
                }
            }

            if (value instanceof Collection) {

                if (annotation != null && annotation.annotationType().equals(NotEmpty.class)) {

                    if (((Collection) value).size() == 0) {
                        System.out.println(field + " is empty");
                        return false;
                    }
                }
            }
            if (value instanceof String) {
                if (value != null && annotation != null && annotation.annotationType().equals(Regexp.class)) {
                    Regexp regexp = (Regexp) annotation;

                    Pattern p = Pattern.compile(regexp.reg());
                    Matcher m = p.matcher((CharSequence) value);
                    System.out.println(field +" " + m.matches());
                    return (m.matches());


                }
            }
            if ((value == null) && (annotation != null && annotation.annotationType().equals(NotNull.class))) {
                System.out.println(field + " is NULL");
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {


        Verifiable ver = new Verifiable();
        Validator validator = new Validator();
        ver.pow(55, 12);
        validator.validate(ver);

    }
}
