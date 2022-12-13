package generics;

import java.util.Arrays;

public class GenericsArraysDemo {
    public static void main(String[] args) {
//        String arrays
        Pair<Employee>[] employees =createArray(new Pair<>(new Employee(), new Employee()), new Pair<>(new Employee(), new Employee()));
        Object[] objs = (Object[]) employees;
        objs[0] = new Pair<>("Vinayak", "Rachana");
        System.out.println(Arrays.toString(employees));

        Employee[] e = new Employee[2];
        Manager[] m = new Manager[2];
        if (m instanceof Object) {
            System.out.println("Yes its true");
        }

        Pair<? extends Employee> wildPairs = new Pair<>(new Employee(), new Employee());
        wildPairs = new Pair<>(new Manager(1), new Manager(2));
        Employee first = wildPairs.getFirst();

    }

    public static <T> T[] createArray(T... element) {

        return element;
    }

}
