package com.modern.refresh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderLambdaDemo {

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
//        create a bufferedReader read a single line and return it.
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }


    }


    public static void main(String[] args) throws IOException {
//        read 2 lines with lambda
        System.out.println(processFile((BufferedReader br) -> br.readLine() + br.readLine()));

//        read 1 line only with lambda
        System.out.println(processFile(bufferedReader -> bufferedReader.readLine()));

    }

}
