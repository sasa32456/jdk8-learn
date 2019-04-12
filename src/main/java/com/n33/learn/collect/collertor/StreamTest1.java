package com.n33.learn.collect.collertor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamTest1 {

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 80);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 100);
        Student student4 = new Student("zhaoliu", 90);
        Student student5 = new Student("zhaoliu", 90);

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);

        List<Student> students1 = students.stream().collect(Collectors.toList());
        students1.forEach(System.out::println);

        System.out.println("------------");

        Long count = students.stream().collect(Collectors.counting());
        System.out.println("count: " + count);

        System.out.println("count: " + students.stream().count());
        System.out.println("-------------------");


        students.stream().collect(minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        students.stream().collect(maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);

        System.out.println(students.stream().collect(averagingInt(Student::getScore)));
        System.out.println(students.stream().collect(summingInt(Student::getScore)));

        IntSummaryStatistics intSummaryStatistics = students.stream().collect(summarizingInt(Student::getScore));
        System.out.println(intSummaryStatistics);

        System.out.println("---------------");

        System.out.println(students.stream().map(Student::getName).collect(joining()));
        System.out.println(students.stream().map(Student::getName).collect(joining(", ")));
        System.out.println(students.stream().map(Student::getName).collect(joining(", ", "<begin>", "<end>")));

        System.out.println("--------------");

        Map<Integer, Map<String, List<Student>>> map = students.stream().collect(groupingBy(Student::getScore, groupingBy(Student::getName)));
        System.out.println(map);
        System.out.println("--------------");

        Map<Boolean, List<Student>> map2 = students.stream().collect(partitioningBy(student -> student.getScore() > 80));
        System.out.println(map2);

        Map<Boolean, Map<Boolean, List<Student>>> map3
                = students.stream().collect(partitioningBy(student -> student.getScore() > 80,
                partitioningBy(student -> student.getScore() > 90)));
        System.out.println(map3);

        System.out.println("--------------");

        Map<Boolean, Long> map4 = students.stream().collect(partitioningBy(student -> student.getScore() > 80, counting()));
        System.out.println(map4);

        System.out.println("--------------");

        Map<String, Student> map5 = students.stream().
                collect(groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingInt(Student::getScore)),
                        Optional::get)));
        System.out.println(map5);

    }
}

/**
 * collector 是一个接口，他是一个可变的汇聚操作，将输入元素累积到一个可变的结构容器当中；
 * 他会在所有元素处理完毕之后，将累计结果装换为一个最终的表示（可选操作）；它支持穿行和并行操作
 * <p>
 * 为了确保穿行和并行操作的等价性，Collector函数需要实现两个条件:identity（同一性)与associativity(结合性)
 * a == combiner.apply（a，supplier.get（ ））
 * supplier.get（ ）空容器
 * (List<String> list1,List<String> list2))->{list1.addAll(list2);return list1;}
 * <p>
 * reduce不可变，并行流注意，collect可变
 *
 *
 *
 * <p>
 * <p>
 * A <a href="package-summary.html#Reduction">mutable reduction operation</a> that
 * accumulates input elements into a mutable result container, optionally transforming
 * the accumulated result into a final representation after all input elements
 * have been processed.  Reduction operations can be performed either sequentially
 * or in parallel.
 * <p>
 * <p>
 * <a href="package-summary.html#Reduction">可变缩减操作</a>
 * *将输入元素累积到可变结果容器中，可选择转换
 * *在所有输入元素之后将累积结果转换为最终表示
 * *已处理完毕。还原操作可以顺序执行
 * *或并行。
 * * <p>
 * * <p>
 *
 *
 * <p>Examples of mutable reduction operations include:
 * accumulating elements into a {@code Collection}; concatenating
 * strings using a {@code StringBuilder}; computing summary information about
 * elements such as sum, min, max, or average; computing "pivot table" summaries
 * such as "maximum valued transaction by seller", etc.  The class {@link Collectors}
 * provides implementations of many common mutable reductions.
 * <p>
 * <p>
 * 可变减少操作的示例包括：*将元素累积到集合;使用{@code StringBuilder}连接*字符串;计算关于*元素的摘要信息，例如sum，min，max或average;计算“数据透视表”摘要*，例如“卖方的最大价值交易”等。类{@link Collectors} *提供许多常见的可变减少的实现。
 * Collectors本省提供了关于Collector的常见汇聚实现，Collectors是个工厂
 *
 *
 * <p>A {@code Collector} is specified by four functions that work together to
 * accumulate entries into a mutable result container, and optionally perform
 * a final transform on the result.  They are: <ul>
 * <li>creation of a new result container ({@link #supplier()})</li>
 * <li>incorporating a new data element into a result container ({@link #accumulator()})</li>
 * <li>combining two result containers into one ({@link #combiner()})</li>
 * <li>performing an optional final transform on the container ({@link #finisher()})</li>
 * </ul>
 *
 * <p> {@code Collector}由四个函数指定，这四个函数一起工作以将条目累积到可变结果容器中，并可选择对结果执行*最终转换。它们是：<ul>
 * <li>创建新的结果容器（{@link #supplier   接受不返回（）}）</ li>
 * <li>将新数据元素合并到结果容器中（{@link #accumulator 返回BiFunction（ ）}）</ li>
 * <li>将两个结果容器合并为一个（{@link #combiner 并发相关,将并发汇总,（）}）</ li>
 * <li>对容器执行可选的最终转换（{@link #finisher（ ）}）</ li> * </ ul>
 *
 *
 *
 *
 * <p>Collectors also have a set of characteristics, such as
 * {@link Characteristics#CONCURRENT}, that provide hints that can be used by a
 * reduction implementation to provide better performance.
 *
 * <p>收集器还具有一组特性，例如* {@link Characteristics＃CONCURRENT}，它们提供可以由*简化实现使用的提示，以提供更好的性能。
 *
 * <p>A sequential implementation of a reduction using a collector would
 * create a single result container using the supplier function, and invoke the
 * accumulator function once for each input element.  A parallel implementation
 * would partition the input, create a result container for each partition,
 * accumulate the contents of each partition into a subresult for that partition,
 * and then use the combiner function to merge the subresults into a combined
 * result.
 * <p>
 * 使用收集器顺序实现简化将使用supplier函数创建单个结果容器，并为每个输入元素调用* accumulator函数一次。并行实现*将对输入进行分区，为每个分区创建结果容器，*将每个分区的内容累积到该分区的子结果中*，然后使用组合器函数将子结果合并为组合*结果。
 *
 * <p>To ensure that sequential and parallel executions produce equivalent
 * results, the collector functions must satisfy an <em>identity</em> and an
 * <a href="package-summary.html#Associativity">associativity</a> constraints.
 *
 *
 * <p>为了确保顺序和并行执行产生等效的*结果，收集器函数必须满足<em>标识</ em>和* <a href="package-summary.html#Associativity">关联性</ a >约束。
 *
 *
 * <p>The identity constraint says that for any partially accumulated result,
 * combining it with an empty result container must produce an equivalent
 * result.  That is, for a partially accumulated result {@code a} that is the
 * result of any series of accumulator and combiner invocations, {@code a} must
 * be equivalent to {@code combiner.apply(a, supplier.get())}.
 *
 * <p>标识约束表示对于任何部分累积的结果，将它与空结果容器组合必须产生等效的结果。也就是说，对于部分累积的结果{@code a}，
 * 它是任何一系列累加器和组合器调用的结果，{@ code a}必须*等同于{@code combiner.apply（a，supplier.get（ ））}。
 *
 * <p>The associativity constraint says that splitting the computation must
 * produce an equivalent result.  That is, for any input elements {@code t1}
 * and {@code t2}, the results {@code r1} and {@code r2} in the computation
 * below must be equivalent:
 *
 *
 * <p>关联约束表示拆分计算必须*产生等效结果。也就是说，对于任何输入元素{@code t1} *和{@code t2}，
 * 下面的计算*中的结果{@code r1}和{@code r2}必须是等效的：
 *
 *
 * <pre>{@code
 * A a1 = supplier.get();
 * accumulator.accept(a1, t1);a1:每次的第一个结果,t1:流中的待处理元素
 * accumulator.accept(a1, t2);
 * R r1 = finisher.apply(a1);  // result without splitting
 *
 * A a2 = supplier.get();
 * accumulator.accept(a2, t1);
 * A a3 = supplier.get();
 * accumulator.accept(a3, t2);
 * R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting   r1 == r2
 * } </pre>
 *
 * <p>For collectors that do not have the {@code UNORDERED} characteristic,
 * two accumulated results {@code a1} and {@code a2} are equivalent if
 * {@code finisher.apply(a1).equals(finisher.apply(a2))}.  For unordered
 * collectors, equivalence is relaxed to allow for non-equality related to
 * differences in order.  (For example, an unordered collector that accumulated
 * elements to a {@code List} would consider two lists equivalent if they
 * contained the same elements, ignoring order.)
 *
 * <p>对于没有{@code UNORDERED  无序}特征的收藏家，如果{@code finisher.apply（a1）.equals（finisher.apply（两个），
 * 那么两个累积结果{@code a1}和{@code a2}是等效的。 a2））}。对于无序收集器，放宽等价以允许与顺序差异相关的不相等。
 * （例如，如果元素包含相同的元素，忽略顺序，那么将元素累积到{@code List}的无序收集器会考虑两个等价的列表。）
 * <p>
 * 忽略顺序，内部元素相同，即等价
 *
 * <p>Libraries that implement reduction based on {@code Collector}, such as
 * {@link Stream#collect(Collector)}, must adhere to the following constraints:
 *
 * <ul>
 * <li>The first argument passed to the accumulator function, both
 * arguments passed to the combiner function, and the argument passed to the
 * finisher function must be the result of a previous invocation of the
 * result supplier, accumulator, or combiner functions.</li>
 * <li>The implementation should not do anything with the result of any of
 * the result supplier, accumulator, or combiner functions other than to
 * pass them again to the accumulator, combiner, or finisher functions,
 * or return them to the caller of the reduction operation.</li>
 * <li>If a result is passed to the combiner or finisher
 * function, and the same object is not returned from that function, it is
 * never used again.</li>
 * <li>Once a result is passed to the combiner or finisher function, it
 * is never passed to the accumulator function again.</li>
 * <li>For non-concurrent collectors, any result returned from the result
 * supplier, accumulator, or combiner functions must be serially
 * thread-confined.  This enables collection to occur in parallel without
 * the {@code Collector} needing to implement any additional synchronization.
 * The reduction implementation must manage that the input is properly
 * partitioned, that partitions are processed in isolation, and combining
 * happens only after accumulation is complete.</li>
 * <li>For concurrent collectors, an implementation is free to (but not
 * required to) implement reduction concurrently.  A concurrent reduction
 * is one where the accumulator function is called concurrently from
 * multiple threads, using the same concurrently-modifiable result container,
 * rather than keeping the result isolated during accumulation.
 * A concurrent reduction should only be applied if the collector has the
 * {@link Characteristics#UNORDERED} characteristics or if the
 * originating data is unordered.</li>
 * </ul>
 *
 * <p>基于{@code Collector}实现缩减的库，例如{@link Stream #colle（Collector）}，必须遵守以下约束：
 *
 * <UL>
 * <li>第一个参数传递给累加器函数，两者都是传递给组合器函数的参数，以及传递给的参数finisher函数必须是之前调用的结果
 * 结果供应商，累加器或组合器功能。</ li>
 * <li>实现不应该对任何结果做任何事情结果供应商，累加器或组合器功能除了再次将它们传递给累加器，组合器或修整器功能，
 * 或者将它们还给还原操作的调用者。</ li>
 * <li>如果结果传递给合成器或修整器函数，并且该函数不返回相同的对象，它是从未再次使用过。</ li>
 * <li>一旦结果传递给合成器或修整器功能，它就会永远不会再次传递给累加器函数。</ li>
 * <li>对于非并发收集器，从结果返回任何结果供应商，累加器或组合器功能必须是连续的线程限制。这使得收集可以并行发生{@code Collector}需要实现任何其他同步。
 * 减少实现必须管理输入正确分区，分区处理和组合处理只有在积累完成后才会发生。</ li>
 * <li>对于并发收集器，实现是免费的（但不是要求同时实施减少。同时减少是一个从中同时调用累加器函数的函数
 * 多个线程，使用相同的可同时修改的结果容器，而不是在积累期间保持结果被隔离。只有在收集器具有的情况下才应用并发减少
 * {@link Characteristics＃UNORDERED}特征或者如果原始数据是无序的。</ li>
 * </ UL>
 *
 *
 *
 *
 * <p>In addition to the predefined implementations in {@link Collectors}, the
 * static factory methods {@link #of(Supplier, BiConsumer, BinaryOperator, Characteristics...)}
 * can be used to construct collectors.  For example, you could create a collector
 * that accumulates widgets into a {@code TreeSet} with:
 *
 * <p>除{@link Collectors}中的预定义实现外，还有
 * 静态工厂方法{@link #of（Supplier，BiConsumer，BinaryOperator，Characteristics ...）}
 * 可以用来构建收藏家。例如，您可以创建一个收集器
 * 将小部件累积到{@code TreeSet}中：
 *
 * <pre>{@code
 * Collector<Widget, ?, TreeSet<Widget>> intoSet =
 * Collector.of(TreeSet::new, TreeSet::add,
 * (left, right) -> { left.addAll(right); return left; });
 * }</pre>
 * <p>
 * (This behavior is also implemented by the predefined collector
 * {@link Collectors#toCollection(Supplier)}).
 *
 * @apiNote Performing a reduction operation with a {@code Collector} should produce a
 * result equivalent to:
 * <pre>{@code
 * R container = collector.supplier().get();
 * for (T t : data)
 * collector.accumulator().accept(container, t);
 * return collector.finisher().apply(container);
 * }</pre>
 *
 * <p>However, the library is free to partition the input, perform the reduction
 * on the partitions, and then use the combiner function to combine the partial
 * results to achieve a parallel reduction.  (Depending on the specific reduction
 * operation, this may perform better or worse, depending on the relative cost
 * of the accumulator and combiner functions.)
 * <p>但是，库可以自由分区输入，执行缩减
 * 在分区上，然后使用组合器功能来组合部分
 * 结果实现平行减少。 （取决于具体的减少
 * 操作，这可能会表现得更好或更差，具体取决于相对成本
 * 累加器和组合器功能。） *
 * <p>Collectors are designed to be <em>composed</em>; many of the methods
 * in {@link Collectors} are functions that take a collector and produce
 * a new collector.  For example, given the following collector that computes
 * the sum of the salaries of a stream of employees:
 * <p>收藏家的目的是<em>撰写</ em>;很多方法
 * {@link Collectors}中的*是收集和制作的功能
 * 一个新的收藏家。例如，给定以下计算器的收集器
 * 员工流的总和： *
 * <pre>{@code
 * Collector<Employee, ?, Integer> summingSalaries
 * = Collectors.summingInt(Employee::getSalary))
 * }</pre>
 * <p>
 * If we wanted to create a collector to tabulate the sum of salaries by
 * department, we could reuse the "sum of salaries" logic using
 * {@link Collectors#groupingBy(Function, Collector)}:
 * <p>
 * 如果我们想创建一个收集器来列出工资总额
 * 部门，我们可以重复使用“工资总和”逻辑
 * {@link Collectors＃groupingBy（Function，Collector）}： *
 * <pre>{@code
 * Collector<Employee, ?, Map<Department, Integer>> summingSalariesByDept
 * = Collectors.groupingBy(Employee::getDepartment, summingSalaries);
 * }</pre>
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * <p>
 * BinaryOperator<A> combiner();
 * @param <T>
 * @param <A>
 * @param <R>
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * @return a function which returns a new, mutable result container
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * BinaryOperator<A> combiner();
 * }
 * @param <T>
 * @param <A>
 * @param <R>
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * @return a function which returns a new, mutable result container
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * @param <T>
 * @param <A>
 * @param <R>
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * @return a function which returns a new, mutable result container
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * @param <T>
 * @param <A>
 * @param <R>
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * @return a function which returns a new, mutable result container
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @return a function which returns a new, mutable result container
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @return a function which returns a new, mutable result container
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * set，此函数可以假设为带有的标识变换
 * 取消选中从{@code A}到{@code R}的演员表。
 * @return一个将中间结果转换为最终结果的函数 结果
 * <p> * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @param <T> the type of input elements to the reduction operation
 * @param <A> the mutable accumulation type of the reduction operation (often
 * hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 * @param <T>流每个元素类型
 * @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 * @param <R>结果类型
 * @return a function which returns a new, mutable result container
 * @return 一个返回一个新的可变结果容器的函数
 * Supplier<A> supplier();
 * <p>
 * A function that folds a value into a mutable result container.
 * @return a function which folds a value into a mutable result container
 * @return 将值折叠到可变结果容器中的函数
 * BiConsumer<A, T> accumulator();
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * @return a function which combines two partial results into a combined
 * result
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * @return一个功能，它将两个部分结果组合成一个组合 结果
 * BinaryOperator<A> combiner();
 * }
 * <p>
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
 * <p>
 * 从中间累积类型执行最终转换
 * {@code A}到最终结果类型{@code R}。
 *
 * <p>如果特征{@code IDENTITY_TRANSFORM}是
 * 设置，该函数可以被假定为具有的标识变换
 * 从{@code A}到{@code R}取消选中。
 * @return一个将中间结果转换为final的函数 结果
 * <p>
 * Function<A, R> finisher();
 * @see Stream#collect(Collector)
 * @see Collectors
 * @since 1.8
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * A function that accepts two partial results and merges them.  The
 * combiner function may fold state from one argument into the other and
 * return that, or may return a new result container.
 * <p>
 * <p>
 * 一个接受两个部分结果并合并它们的函数。该
 * 组合器函数可以将状态从一个参数折叠到另一个参数中
 * 返回，或可能返回一个新的结果容器。
 * <p> * <p>
 * <p>
 * <p>
 * <p>
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 * @since 1.8
 * @since 1.8
 * <p>
 * interface Collector<T, A, R> {
 * A function that creates and returns a new mutable result container.
 * 一个创建并返回新的可变结果容器的函数。
 */


/**

 @param <T> the type of input elements to the reduction operation
 @param <A> the mutable accumulation type of the reduction operation (often
 hidden as an implementation detail)
 @param <R> the result type of the reduction operation
 @since 1.8

 @param <T>流每个元素类型
 @param <A>流操作的可变累积类型（通常隐藏为实现细节）中间生成每个元素的类型
 @param <R>结果类型
 @since 1.8

 interface Collector<T, A, R> {
 A function that creates and returns a new mutable result container.
 一个创建并返回新的可变结果容器的函数。

 @return a function which returns a new, mutable result container
 @return 一个返回一个新的可变结果容器的函数
 Supplier<A> supplier();

 A function that folds a value into a mutable result container.

 @return a function which folds a value into a mutable result container
 @return 将值折叠到可变结果容器中的函数
 BiConsumer<A, T> accumulator();

 A function that accepts two partial results and merges them.  The
 combiner function may fold state from one argument into the other and
 return that, or may return a new result container.

 @return a function which combines two partial results into a combined
 result

 一个接受两个部分结果并合并它们的函数。该
 组合器函数可以将状态从一个参数折叠到另一个参数中
 返回，或可能返回一个新的结果容器。
 @return一个功能，它将两个部分结果组合成一个组合 结果
 BinaryOperator<A> combiner();
 }



 Perform the final transformation from the intermediate accumulation type
 {@code A} to the final result type {@code R}.

 <p>If the characteristic {@code IDENTITY_TRANSFORM} is
 set, this function may be presumed to be an identity transform with an
 unchecked cast from {@code A} to {@code R}.

 @return a function which transforms the intermediate result to the final
 result

 从中间累积类型执行最终转换
 {@code A}到最终结果类型{@code R}。

 <p>如果特征{@code IDENTITY_TRANSFORM}是
 设置，该函数可以被假定为具有的标识变换
 从{@code A}到{@code R}取消选中。

 @return一个将中间结果转换为final的函数 结果

 Function<A, R> finisher();

 */
