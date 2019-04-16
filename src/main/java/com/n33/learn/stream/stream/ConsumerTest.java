package com.n33.learn.stream.stream;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerTest {

    public void test(Consumer<Integer> consumer){
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();

        Consumer<Integer> consumer = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);

        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof IntConsumer);

        consumerTest.test(consumer);//面向对象
        //consumerTest.test((Consumer)intConsumer);//Exception in thread "main" java.lang.ClassCastException: com.n33.learn.stream.stream.ConsumerTest$$Lambda$2/1747585824 cannot be cast to java.util.function.Consumer
        consumerTest.test(consumer::accept);
        consumerTest.test(intConsumer::accept);//函数方式
    }
}
