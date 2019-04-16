package com.n33.learn.ago.z190411.groupby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test01 {

    public static void main(String[] args) {

        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);


        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        //Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getName));
        //System.out.println(collect);

//        Map<String, Long> collect = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
//        System.out.println(collect);


        // Map<String, Double> collect = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));

        Map<Boolean, List<Student>> collect = students.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 90));

        System.out.println(collect.get(false));

    }
}
