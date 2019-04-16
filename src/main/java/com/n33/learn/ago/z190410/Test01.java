package com.n33.learn.ago.z190410;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test01 {

    public static void main(String[] args) {

//        Stream<String> stream = Stream.of("hello", "world", "helloworld");


        //String[] strings = stream.toArray(length -> new String[length]);

        //String[] strings = stream.toArray(String[]::new);

        //Arrays.asList(strings).forEach(System.out::println);

        //List<String> list = stream.collect(Collectors.toList());

        //List<String> list = stream.collect(Collectors.toList());

        //list.forEach(System.out::println);

//        ArrayList list = stream.collect(() -> new ArrayList(), (theList, item) -> theList.add(item),(theList1, theList2) -> theList1.addAll(theList2));

//        String s = "c ;  b; a ;d ;  f";
//        String[] split = s.split("\\;");
//        List<String> collect = Arrays.asList(split).stream().map(i -> i.trim()).collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        System.out.println("--------------------");
//        Collections.sort(collect,(o1, o2) -> o2.compareTo(o1));
//        collect.forEach(System.out::println);

        //ArrayList<String> collect = stream.collect(Collectors.toCollection(ArrayList::new));


//        TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));
//        treeSet.forEach(s -> System.out.println(s));


        Stream<String> stream = Stream.of("hello", "world", "helloworld");
        String s = stream.collect(Collectors.joining()).toString();
        System.out.println(s);


    }
}
