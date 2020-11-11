package com.modern.refresh;

public class AppleSimpleFormatter implements  AppleFormatter{
    @Override
    public String accept(Apple apple) {
        return apple.getColor() +" Apple having weight "+apple.getWeight() ;
    }
}
