package com.n33.learn.collect.collertor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 1.输入：Set<String>
 * 2.输出：Map<String,String>
 * <p>
 * 示例输入：["hello","world","hello world"]
 * 示例输出：[{hello,hello},{world，world},{hello world,hello world}]
 */
public class MySetCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        //return HashSet<T>::new;
        return () -> {
            System.out.println("**************");//串行流一个，并行会按需创建(与cpu核心数相同........<=)
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        return (set, item) -> {
            //System.out.println("accumulator: " + " --------- " + Thread.currentThread().getName() + " ----- " + item);
            System.out.println("accumulator: " + set + " --------- " + Thread.currentThread().getName() + " ----- " + item);//打印出现异常，set被修改时候还被遍历（Characteristics.CONCURRENT）
            set.add(item);
        };
    }

    @Override
    //多线程才会调用
    //CONCURRENT则只有一个容器，无需合并，不会被调用，反之，调用
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (set1, set2) -> {
            System.out.println("set1: " + set1);
            System.out.println("set2: " + set2);
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked!");
        return set -> {
            Map<T, T> map = new HashMap<>();
            set.stream().forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    //空（三者都不满足），CONCURRENT并行(多个线程修改一个容器,反之多个线程修改多个容器)，UNORDERED结果无序，强制转换
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH));//异常(无法强转)
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));//异常ConcurrentModificationException(并发修改异常，线程修改集合，同时另一个在迭代)//accumulator()中输出set
    }

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());//cpu核心数量（超线程技术（一个当多个用））

        //for (int i = 0; i < 100; i++) {
        for (int i = 0; i < 1; i++) {


            List<String> list = Arrays.asList("hello", "world", "welcome", "hello", "a", "b", "c", "d", "e", "f", "g");

            Set<String> set = new HashSet<>();
            set.addAll(list);
            System.out.println("set: " + set);

            //Map<String, String> map = set.stream().collect(new MySetCollector2<>());
            //Map<String, String> map = set.stream().sequential().collect(new MySetCollector2<>());//并行
            Map<String, String> map = set.parallelStream().collect(new MySetCollector2<>());
            //Map<String, String> map = set.stream().parallel().collect(new MySetCollector2<>());//串行
            System.out.println(map);

        }
    }
}
/*

收集器：
对于Collectors静态工厂来说，其实现分为两种情况：
1.通过CollectorImpl来实现
2.通过reducing来实现，但reducing也是基于CollectorImpl




 */
