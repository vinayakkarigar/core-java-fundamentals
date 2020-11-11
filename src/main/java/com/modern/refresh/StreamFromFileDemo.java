package com.modern.refresh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamFromFileDemo {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("data.txt"))) {
            final long count = lines.flatMap(l -> Arrays.stream(l.split(" ")))
                    .distinct()
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
