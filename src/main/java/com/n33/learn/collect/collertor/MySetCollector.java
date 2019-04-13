package com.n33.learn.collect.collertor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulatar invoked!");
        return Set<T>::add;
        //return (set,item)->set.add(item);
        //return HashSet<T>::add;//错误,父类子类太多，无法确认
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher invoked!");
        return Function.identity();
        //return t -> t;//如果最终和开始集合一致，则不会调用(IDENTITY_FINISH枚举标签)
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","hello");
        Set<String> set = list.stream().collect(new MySetCollector<>());
        System.out.println(set);
    }
}
/**
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。 *
 *
 * @return a function which returns a new, mutable result container
 * <p>
 * Supplier<A> supplier();
 * <p>
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * 将值折叠到可变结果容器中的函数。
 * @return一个将值折叠到可变结果容器中的函数 * <p>
 * BiConsumer<A, T> accumulator();
 * <p>
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果 * <p>
 * BinaryOperator<A> combiner();
 * <p>
 * <p>
 * Perform the final transformation from the intermediate accumulation type
 * {@code A} to the final result type {@code R}.
 *
 * <p>If the characteristic {@code IDENTITY_TRANSFORM} is
 * set, this function may be presumed to be an identity transform with an
 * unchecked cast from {@code A} to {@code R}.
 * @return a function which transforms the intermediate result to the final
 * result
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * set，此函数可以假设为带有的标识变换
 * 取消选中从{@code A}到{@code R}的演员表。
 * @return一个将中间结果转换为最终结果的函数 结果 * <p>
 * Function<A, R> finisher();
 * <p>
 * <p>
 * Returns a {@code Set} of {@code Collector.Characteristics} indicating
 * the characteristics of this Collector.  This set should be immutable.
 * @return an immutable set of collector characteristics
*返回{@code Collector.Characteristics}的{@code Set}指示
 *这个收藏家的特点。这个集合应该是不可变的。
 * @return一组不可变的收集器特征 * <p>
 * Set<Characteristics> characteristics();
 */



/*
    enum Characteristics {

      Indicates that this collector is <em>concurrent</em>, meaning that
      the result container can support the accumulator function being
      called concurrently with the same result container from multiple
      threads.
表示此收集器是<em>并发</ em>，这意味着
      结果容器可以支持累加器功能
      从多个同时调用相同的结果容器
      线程。
      <p>If a {@code CONCURRENT} collector is not also {@code UNORDERED},
      then it should only be evaluated concurrently if applied to an
      unordered data source.
<p>如果{@code CONCURRENT}收藏家不是{@code UNORDERED}，
      那么只有在应用于a时才应该同时进行评估
      无序数据源。
        CONCURRENT,


 Indicates that the collection operation does not commit to preserving
 the encounter order of input elements.  (This might be true if the
 result container has no intrinsic order, such as a {@link Set}.)
表明收集操作不承诺保存
 输入元素的重要命令。 （如果是的话，这可能是真的
 结果容器没有任何内在的顺序，例如{@LINK SET}。）
  UNORDERED,


 Indicates that the finisher function is the identity function and
 can be elided.  If set, it must be the case that an unchecked cast
 from A to R will succeed.
表示整理器功能是身份功能和
 可以省略。如果设置，则必须是未经检查的强制转换的情况
 从a到r将成功。
  IDENTITY_FINISH
  }
 */
