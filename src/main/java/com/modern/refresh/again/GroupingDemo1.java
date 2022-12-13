package com.modern.refresh.again;

import com.modern.refresh.Dish;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

public class GroupingDemo1 {
    public static void main(String[] args) {
        List<Dish> dishes = Dish.getDishes();
        Map<Dish.Type, List<Dish>> dishesByType =
                dishes.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

    }
}
