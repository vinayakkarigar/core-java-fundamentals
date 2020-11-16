package com.modern.refresh;

import java.io.Flushable;
import java.util.*;
import java.util.stream.Collectors;

public class FunctionalStyleDemo {

    public static <T> List<List<T>> subsets(List<T> list) {
        if (list.isEmpty()) {
            List<List<T>> result = new ArrayList<>();
            result.add(Collections.emptyList());
            return result;
        }

        T fst = list.get(0);
        System.out.println(fst);
        final List<T> subList = list.subList(1, list.size());
        final List<List<T>> subA = subsets(subList);
        List<List<T>> subB = insertAll(fst, subA);

        return concat(subA, subB);
    }

    private static <T> List<List<T>> concat(List<List<T>> subA, List<List<T>> subB) {
        final List<List<T>> lists = new ArrayList<>(subA);
        lists.addAll(subB);
        return lists;
    }

    private static <T> List<List<T>> insertAll(T fst, List<List<T>> subList) {
        List<List<T>> result = new ArrayList<>();
        for (List<T> i :
                subList) {
            final List<T> r = new ArrayList<>();
            r.add(fst);
            r.addAll(i);
            result.add(r);
        }
        return result;
    }

    public static <T> List<List<T>> permutations(List<T> list) {
        if (list.size() == 1) {
            return List.of(list);
        }


        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final T t = list.get(i);
            final List<T> list1 = list.stream().filter(j -> !j.equals(t)).collect(Collectors.toList());
//            System.out.println("List:"+list1);
            final List<List<T>> permutations = permutations(list1);
            permutations.stream()
                    .forEach(list2 -> list2.add(0, t));

            result.addAll(permutations);
        }
        return result;
    }




    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 4, 9);
//        final List<List<Integer>> subsets = subsets(integers);
//        System.out.println(subsets);
//        subsets.stream()
//                .sorted(Comparator.comparing(List::size))
//                .forEach(System.out::println);
//        System.out.println(subsets.size());

        final List<List<Integer>> permutations = permutations(integers);
        permutations.forEach(System.out::println);
        System.out.println(permutations.size());



    }

}
