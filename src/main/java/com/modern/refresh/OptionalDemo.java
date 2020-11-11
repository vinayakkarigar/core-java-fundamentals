package com.modern.refresh;

import java.util.Optional;
import java.util.Properties;

public class OptionalDemo {

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }


    }

    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalDemo::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("b", "sds");
        System.out.println(readDuration(properties, "a"));
    }


}
