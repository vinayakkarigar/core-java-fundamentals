package com.modern.refresh;

public class AppleWeightFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return "Apple:"+apple.hashCode()+" is having weight "+apple.getWeight();
    }
}
