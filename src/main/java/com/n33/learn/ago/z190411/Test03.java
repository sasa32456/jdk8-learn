package com.n33.learn.ago.z190411;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test03 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");

        //list.stream().map(s -> s.split(" ")).distinct().collect(Collectors.toList()).forEach(System.out::println);

        list.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);
//        list.stream().flatMap(s -> Arrays.stream(s.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
}
