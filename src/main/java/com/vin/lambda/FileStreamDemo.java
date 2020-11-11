package com.vin.lambda;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileStreamDemo {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"))) {
            final long count = lines.flatMap(l -> Arrays.stream(l.split(" "))).distinct().count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
