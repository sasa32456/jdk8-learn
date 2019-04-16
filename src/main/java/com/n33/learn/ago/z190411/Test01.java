package com.n33.learn.ago.z190411;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Test01 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 500000; i++) {
            list.add(UUID.randomUUID().toString());
        }


        System.out.println("开始排序");

        long startTime = System.nanoTime();
        //long count = list.stream().sorted().count();
        long count = list.parallelStream().sorted().count();
        System.out.println(count);

        long endTime = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("排序耗时：" + millis);


    }
}
