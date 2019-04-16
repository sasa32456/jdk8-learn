package com.n33.learn.ago.z190410;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test02 {
    public static void main(String[] args) {

//        List<String> list = Arrays.asList("hello","world","helloworld","test");

        //list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        Stream<List<Integer>> stream = Stream.of(Collections.singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        stream.flatMap(Collection::stream).map(integer -> integer * integer).forEach(System.out::println);


        //Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        //stream.findFirst().ifPresent(System.out::println);


        //Stream.iterate(1, integer -> integer +2).limit(6).forEach(System.out::println);

//        Stream<Integer> stream = Stream.of(1, 3, 5, 7, 9, 11);
//        stream.filter(integer -> integer > 2).mapToInt(integer -> integer * 2).skip(2).limit(2).reduce(Integer::sum).ifPresent(System.out::println);

//        IntSummaryStatistics summaryStatistics = stream.filter(integer -> integer > 2).mapToInt(integer -> integer * 2).skip(2).limit(2).summaryStatistics();
//
//        System.out.println(summaryStatistics.getMax());
//        System.out.println(summaryStatistics.getMin());
//        System.out.println(summaryStatistics.getCount());








    }

}
