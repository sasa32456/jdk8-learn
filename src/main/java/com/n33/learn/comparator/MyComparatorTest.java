package com.n33.learn.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");

        //Collections.sort(list);

        //Collections.sort(list, (o1, o2) -> o1.length()-o2.length());
        //Collections.sort(list, (o1, o2) -> o2.length()-o1.length());

        //Collections.sort(list, Comparator.comparingInt(String::length));
        //Collections.sort(list, Comparator.comparingInt(String::length).reversed());
        //Collections.sort(list, Comparator.comparingInt((String value) -> value.length()).reversed());
        //Collections.sort(list, Comp arator.comparingInt((Object item) -> 1).reversed());

        //list.sort(Comparator.comparingInt(String::length).reversed());
        //list.sort(Comparator.comparingInt((String value) -> value.length()).reversed());

        //二次比较
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing((o1, o2) -> o1.toLowerCase().compareTo(o2.toLowerCase())));
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase)));
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        //Collections.sort(list, Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

        //多级排序
        Collections.sort(list, Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder()))
                //无作用
                .thenComparing(Comparator.reverseOrder()));


        System.out.println(list);

    }
}
