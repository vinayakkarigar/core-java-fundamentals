package com.modern.refresh.again;

import com.modern.refresh.Dish;

import java.util.List;

public class PrimitiveStreamsDemo {
    public static void main(String[] args) {
        System.out.println("Mapping Calories to Numeric stream");
        List<Dish> menu = Dish.getDishes();
        System.out.println(menu.stream().mapToInt(Dish::getCalories).sum());
    }
}
