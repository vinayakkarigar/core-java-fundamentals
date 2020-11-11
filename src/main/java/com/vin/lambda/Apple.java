package com.vin.lambda;

public class Apple {
    private final int weight;
    private final Color color;

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public enum Color { RED, GREEN}

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}
