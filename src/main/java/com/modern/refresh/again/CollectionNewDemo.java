package com.modern.refresh.again;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionNewDemo {
    public static void main(String[] args) {
//        Collection factories introduced in java 9
        List<String> familyMembers = List.of("Vinayak", "Rachana", "Manasw");
        System.out.println(familyMembers);

        System.out.println(familyMembers.stream().map(String::toUpperCase).collect(Collectors.toList()));

        System.out.println("Demo Set factory method");
        System.out.println(Set.of("Vinayak", "Rachana", "Manas"));
    }
}
