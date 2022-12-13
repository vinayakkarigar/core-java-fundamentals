package com.wgjd.ch04;

import java.net.http.HttpClient;

public class Ch04Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz);

        var clsThis = Ch04Demo.class.getClassLoader();
        System.out.println(clsThis);
        var clsObj = Object.class.getClassLoader();
        System.out.println(clsObj);
        var clsPlt = HttpClient.class.getClassLoader();
        System.out.println(clsPlt);

    }
}
