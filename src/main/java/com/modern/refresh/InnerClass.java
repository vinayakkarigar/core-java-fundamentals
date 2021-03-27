package com.modern.refresh;

import java.util.function.Function;

public class InnerClass {
    Function<Object, String> func = new Function<Object, String>(){

        @Override
        public String apply(Object o) {
            return o.toString();
        }
    };

}
