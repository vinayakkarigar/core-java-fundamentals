package com.modern.refresh;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public class AnnotationDemo {

    @Repeatable(Authors.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Author {
        String name();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Authors{
        Author[]  value();
    }

    @Author(name = "Vinayak") @Author(name = "Rachana")
    public static class Book {

    }

    public static void main(String[] args) {
        final Author[] annotationsByType = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(annotationsByType).stream()
                .forEach(a -> System.out.println(a.name()));
    }

}
