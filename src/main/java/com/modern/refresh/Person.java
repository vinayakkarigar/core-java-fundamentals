package com.modern.refresh;

import javax.print.attribute.standard.MediaSize;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Person {
    private Car car;

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknow");
    }

    public static Set<String> getInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(c -> c.flatMap(Car::getInsurance))
                .map(i -> i.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }

    public static Insurance getCheapestInsurance(Person person, Car car) {
        //Do some checks to get the cheapest insurances
        //hit the external sites with the combination of person and car to get the cheapest insurance.

        return new Insurance("CHEAPEST INSURANCE");
    }

    public static Optional<Insurance> nullSafeGetCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> getCheapestInsurance(p, c)));
    }


    public static void main(String[] args) {
        final Car car1 = new Car();
        final Car car2 = new Car();
        car1.setInsurance(Optional.ofNullable(new Insurance("BAJAJ AXA")));
        car2.setInsurance(Optional.ofNullable(new Insurance("AGONE RELIGARE")));
        final Person person1 = new Person();
        final Person person2 = new Person();
        person1.setCar(car1);
        person2.setCar(car2);


        final Optional<Person> person = Optional.ofNullable(person1);
        System.out.println(Person.getCarInsuranceName(person));

        final List<Person> persons = List.of(person1, person2);
        System.out.println(Person.getInsuranceNames(persons));

        System.out.println("Optional Demo: filter the values");
        Optional<Insurance> o = Optional.ofNullable(new Insurance("BAJAJ AXA"));
        System.out.println(o.filter(i -> i.getName().startsWith("BAJAJ")).isPresent());

        System.out.println("Optional Demo: combining optionals");
        nullSafeGetCheapestInsurance(Optional.ofNullable(person1), Optional.ofNullable(null)).ifPresent(System.out::println);
    }

}
