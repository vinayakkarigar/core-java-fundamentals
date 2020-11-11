package com.vin.lambda;

import java.util.Objects;

public class Dish {
    private String name;
    private boolean isVegetarian;
    private int calories;
    private Type type;

    public Dish(String name, boolean isVegetarian, int calories, Type type) {
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", isVegetarian=" + isVegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return calories == dish.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(calories);
    }

    public enum Type {MEAT, FISH, OTHER}
}
