package com.modern.refresh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.*;
import static java.util.stream.Collectors.*;

public class CollectionFactoriesDemo {

    private static MessageDigest md;

    public static byte[] calculateDigest(String key) {
        return md.digest(key.getBytes());
    }

    static {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        System.out.println("Before Java 9 ways of handling small list, set");
        final List<String> names = Arrays.asList("Vinayak", "Rachana", "Giri", "Mahesh");
        names.set(0, "Vini");
//        names.add("Ohhhh");

        System.out.println("Before Java 9: sets using arrays hack");
        final HashSet<String> strings = new HashSet<>(Arrays.asList("VIn", "Rac"));
        Stream.of("Vin", "Rac").collect(toSet());

        System.out.println("Java 9: List factories demo");
        final List<String> vin = List.of("Vin", "Rac", "Riya", "Arnab");
        System.out.println(vin);

        System.out.println("Jave 9: Set factories demo");
        final Set<String> vinayak = Set.of("Vinayak", "Rachana");
//        vinayak.add("dsds");
        System.out.println(vinayak);

        System.out.println("Java 9: Map factories demo");
        System.out.println("Approach 1: alternating key and values");
        final Map<String, Integer> vinayak1 = of("Vinayak", 35, "Rachana", 28);
        System.out.println(vinayak1);

        System.out.println("Approach 2: Map of entries");
        final Map<String, Integer> ageOfFriends = ofEntries(entry("Vinayak", 35),
                entry("Rachana", 28),
                entry("Gapu", 30));
        System.out.println(ageOfFriends);

        final ArrayList<String> nameList = new ArrayList<>();
        nameList.add("vinayak");
        nameList.add("rachana");
        nameList.add("amit");
        nameList.add("samarth");
        nameList.add("swarnika");
        System.out.println("Java 8 - 9: Working with List modification operations");
        System.out.println("Java 8 9: Removeif demo");
        nameList.removeIf(s -> s.charAt(0) == 'v');
        System.out.println(nameList);
        System.out.println("Java 8 9: replace all");
        nameList.replaceAll(s -> {
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        });
        System.out.println(nameList);
        System.out.println("Java 8 9: Working with map operations");
        ageOfFriends.forEach((k, v) -> System.out.println("Name:" + k + " Age:" + v));

        System.out.println("Java 8 9: sorting of map using key or value");
        Map<String, String> actorFilmMap = Map.ofEntries(
                entry("Rapheal", "Star wars"),
                entry("Christina", "Matrix"),
                entry("Olivia", "James Bond")
        );
        System.out.println("Sort by names");
        actorFilmMap.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(System.out::println);
        System.out.println("Sort bt values");
        actorFilmMap.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .forEachOrdered(System.out::println);
        System.out.println("get or default method");
        final String orDefault = actorFilmMap.getOrDefault("Vin", "DefaultMovie");
        System.out.println(orDefault);

        System.out.println("Demo advanced compute methods");
        Map<String, byte[]> dataToHash = new HashMap<>();

        try (Stream<String> lines = Files.lines(Path.of("data.txt"))) {
            lines.forEach(l -> dataToHash.computeIfAbsent(l, CollectionFactoriesDemo::calculateDigest));
            System.out.println(dataToHash);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, List<String>> friendToMovies = new HashMap<>();
        final List<String> rapheal = friendToMovies.computeIfAbsent("Rapheal", s -> new ArrayList<>());
        rapheal.add("Star war");
        rapheal.add("matrix");
        System.out.println(friendToMovies);

        friendToMovies.computeIfPresent("Rapheal", (s, l) -> {
            l.add("test movies");
            return l;
        });
        System.out.println(friendToMovies);

        System.out.println("Map: remove entry if key is associated with specific value");
        Map<String, String> friendFavMovie = new HashMap<>();
        friendFavMovie.put("Rapheal", "Starwar");
        friendFavMovie.put("Olivia", "Matrix");
        friendFavMovie.put("Vinayak", "TinTin");

        friendFavMovie.remove("Rapheal", "Starwar");
        System.out.println(friendFavMovie);

        friendFavMovie.replaceAll((n, m) -> m.toUpperCase());
        System.out.println(friendFavMovie);

        System.out.println("Map Merge Demo");
        Map<String, String> family = Map.ofEntries(
                entry("Rachana", "VishnuSena"),
                entry("Vinayak", "TinTin")
        );
        Map<String, String> friends = Map.ofEntries(
                entry("Mahesh", "Toto"),
                entry("Vinayak", "CheChe")
        );

        Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (v1, v2) -> v1 + " & " + v2));
        System.out.println(everyone);

        System.out.println("Concurrent HashMap Demo");
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("A", 10L);
        concurrentHashMap.put("B", 90L);
        concurrentHashMap.put("C", 0L);
        concurrentHashMap.put("D", 9L);
        concurrentHashMap.put("E", 8L);
        System.out.println("Count:"+concurrentHashMap.size());

        final Long aLong = concurrentHashMap.reduceValues(1L, Long::max);
        System.out.println(aLong);

        System.out.println("Set view");
        final ConcurrentHashMap.KeySetView<String, Long> strings1 = concurrentHashMap.keySet();
        System.out.println(strings1.getMappedValue());
        System.out.println(concurrentHashMap);


    }

}
