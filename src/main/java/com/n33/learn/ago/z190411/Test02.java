package com.n33.learn.ago.z190411;

import java.util.Arrays;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");

        list.stream().mapToInt(value -> value.length()).filter(value -> value == 5).findFirst().ifPresent(System.out::println);

        list.stream().mapToInt(value -> {
            int length = value.length();
            System.out.println(value);
            return length;
        }).filter(value -> value == 5).findFirst().ifPresent(System.out::println);


    }
}
