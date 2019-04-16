package com.n33.learn.stream.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        //list.stream().forEach(System.out::println);

        //list.stream().forEach(System.out::println);

        list.stream().map(item -> item + "_abc").forEach(System.out::println);
    }
}
/**
 * An object for traversing and partitioning elements of a source.  The source
 * of elements covered by a Spliterator could be, for example, an array, a
 * {@link Collection}, an IO channel, or a generator function.
 * 用于遍历和分区源元素的对象。来源
 * 分裂器覆盖的元素的*可以是例如数组，a
 * {@link collection}，io频道或生成器功能。
 * *
 * <p>A Spliterator may traverse elements individually ({@link
 * #tryAdvance tryAdvance()}) or sequentially in bulk
 * ({@link #forEachRemaining forEachRemaining()}).
 * <p> Spliterator可以单独遍历元素（{@link #tryAdvance tryAdvance（）}）或按顺序批量处理
 * ({@link #forEachRemaining forEachRemaining()}).
 *
 * <p>A Spliterator may also partition off some of its elements (using
 * {@link #trySplit}) as another Spliterator, to be used in
 * possibly-parallel operations.  Operations using a Spliterator that
 * cannot split, or does so in a highly imbalanced or inefficient
 * manner, are unlikely to benefit from parallelism.  Traversal
 * and splitting exhaust elements; each Spliterator is useful for only a single
 * bulk computation.
 * <p> Spliterator也可以对其中的一些元素进行分区（使用
 * {@link #trySplit}）作为另一个Spliterator，用于
 * 可能并行的操作。使用Spliterator的操作
 * 不能分裂，或者在高度不平衡或低效的情况下分裂
 * 方式，不太可能从并行性中受益。穿越
 * 和分流排气元件;每个Spliterator仅适用于一个
 * 批量计算。
 * *
 * <p>A Spliterator also reports a set of {@link #characteristics()} of its
 * structure, source, and elements from among {@link #ORDERED},
 * {@link #DISTINCT}, {@link #SORTED}, {@link #SIZED}, {@link #NONNULL},
 * {@link #IMMUTABLE}, {@link #CONCURRENT}, and {@link #SUBSIZED}. These may
 * be employed by Spliterator clients to control, specialize or simplify
 * computation.  For example, a Spliterator for a {@link Collection} would
 * report {@code SIZED}, a Spliterator for a {@link Set} would report
 * {@code DISTINCT}, and a Spliterator for a {@link SortedSet} would also
 * report {@code SORTED}.  Characteristics are reported as a simple unioned bit
 * set.
 * <p>
 * <p> Spliterator还会报告一组{@link #characteristics（）}
 * {@link #ORDERED}中的结构，来源和元素，
 * {@link #DISTINCT}，{@ link #SORTED}，{@ link #SIZED}，{@ link #NONNULL}，
 * {@link #IMMUTABLE}，{@ link #CONCURRENT}和{@link #SUBSIZED}。这些可能
 * 被Spliterator客户用于控制，专业化或简化
 * 计算。例如，{@link Collection}的Spliterator会
 * 报告{@code SIZED}，{@link Set}的Spliterator会报告
 * {@code DISTINCT}，以及{@link SortedSet}的Spliterator也会
 * 报告{@code SORTED}。特征报告为简单的联合位
 * 设置。
 * <p>
 * <p>
 * * Some characteristics additionally constrain method behavior; for example if
 * {@code ORDERED}, traversal methods must conform to their documented ordering.
 * New characteristics may be defined in the future, so implementors should not
 * assign meanings to unlisted values.
 * *某些特征还会限制方法行为;例如，如果
 * {@code ORDERED}，遍历方法必须符合其记录的顺序。
 * 未来可能会定义新的特征，因此实现者不应该这样做
 * 为未列出的值指定含义。
 * *
 * <p><a name="binding">A Spliterator that does not report {@code IMMUTABLE} or
 * {@code CONCURRENT} is expected to have a documented policy concerning:
 * when the spliterator <em>binds</em> to the element source; and detection of
 * structural interference of the element source detected after binding.</a>  A
 * <em>late-binding</em> Spliterator binds to the source of elements at the
 * point of first traversal, first split, or first query for estimated size,
 * rather than at the time the Spliterator is created.  A Spliterator that is
 * not <em>late-binding</em> binds to the source of elements at the point of
 * construction or first invocation of any method.  Modifications made to the
 * source prior to binding are reflected when the Spliterator is traversed.
 * After binding a Spliterator should, on a best-effort basis, throw
 * {@link ConcurrentModificationException} if structural interference is
 * detected.  Spliterators that do this are called <em>fail-fast</em>.  The
 * bulk traversal method ({@link #forEachRemaining forEachRemaining()}) of a
 * Spliterator may optimize traversal and check for structural interference
 * after all elements have been traversed, rather than checking per-element and
 * failing immediately.
 * <p> <a name="binding">不报告{@code IMMUTABLE}的Spliterator或
 * {@code CONCURRENT}预计会有以下政策：
 * 当分裂者<em>绑定</ em>到元素源时;和检测
 * 绑定后检测到的元素源的结构干扰
 * <em>后期绑定</ em> Spliterator绑定到的元素源
 * 估计大小的第一次遍历，第一次分割或第一次查询的点，
 * 而不是在Spliterator创建时。是一个Spliterator
 * <em>后期绑定</ em>绑定到元素源
 * 构建或首次调用任何方法。修改了
 * 遍历Spliterator时会反映绑定前的源。
 * 绑定Spliterator之后，应该尽最大努力抛出
 * {@link ConcurrentModificationException}如果结构性干扰是
 * 检测到。执行此操作的Spliterators称为<em> fail-fast </ em>。该
 * 批量遍历方法（{@link #forEachRemaining forEachRemaining（）}）的一个
 * Spliterator可以优化遍历并检查结构干扰
 * 遍历所有元素后，而不是检查每个元素和
 * 立即失败。
 * *
 * <p>Spliterators can provide an estimate of the number of remaining elements
 * via the {@link #estimateSize} method.  Ideally, as reflected in characteristic
 * {@link #SIZED}, this value corresponds exactly to the number of elements
 * that would be encountered in a successful traversal.  However, even when not
 * exactly known, an estimated value value may still be useful to operations
 * being performed on the source, such as helping to determine whether it is
 * preferable to split further or traverse the remaining elements sequentially.
 * <p> Spliterators可以提供剩余元素数量的估计值
 * 通过{@link #estimateSize}方法。理想情况下，如特征所反映的那样
 * {@link #SIZED}，此值与元素数量完全对应
 * 在成功的遍历中会遇到。但是，即使不是
 * 确切知道，估计值值可能仍然对操作有用
 * 在源上执行，例如帮助确定它是否
 * 最好进一步拆分或顺序遍历其余元素。
 * *
 * <p>Despite their obvious utility in parallel algorithms, spliterators are not
 * expected to be thread-safe; instead, implementations of parallel algorithms
 * using spliterators should ensure that the spliterator is only used by one
 * thread at a time.  This is generally easy to attain via <em>serial
 * thread-confinement</em>, which often is a natural consequence of typical
 * parallel algorithms that work by recursive decomposition.  A thread calling
 * {@link #trySplit()} may hand over the returned Spliterator to another thread,
 * which in turn may traverse or further split that Spliterator.  The behaviour
 * of splitting and traversal is undefined if two or more threads operate
 * concurrently on the same spliterator.  If the original thread hands a
 * spliterator off to another thread for processing, it is best if that handoff
 * occurs before any elements are consumed with {@link #tryAdvance(Consumer)
 * tryAdvance()}, as certain guarantees (such as the accuracy of
 * {@link #estimateSize()} for {@code SIZED} spliterators) are only valid before
 * traversal has begun.
 * <p>尽管它们在并行算法中有明显的实用性，但分裂器并非如此
 * 预计是线程安全的;相反，并行算法的实现
 * 使用分裂器应确保分裂器仅由一个使用
 * 一次线程。这通常很容易通过<em>序列实现
 * 线程限制</ em>，这通常是典型的自然结果
 * 通过递归分解工作的并行算法。线程调用
 * {@link #trySplit（）}可以将返回的Spliterator移交给另一个线程，
 * 反过来可能会遍历或进一步拆分Spliterator。这种行为
 * 如果两个或多个线程运行，则拆分和遍历的*是不确定的
 * 同时在同一个分裂器上。如果原始线程交给a
 * spliterator关闭到另一个线程进行处理，最好是那个切换
 * 在使用{@link #tryAdvance（消费者）消费任何元素之前发生
 * tryAdvance（）}，作为一定的保证（如准确性）
 * {{code SIZED}分裂器的{@link #estimateSize（）}仅在之前有效
 * 遍历已经开始。
 * *
 * <p>Primitive subtype specializations of {@code Spliterator} are provided for
 * {@link OfInt int}, {@link OfLong long}, and {@link OfDouble double} values.
 * The subtype default implementations of
 * {@link Spliterator#tryAdvance(java.util.function.Consumer)}
 * and {@link Spliterator#forEachRemaining(java.util.function.Consumer)} box
 * primitive values to instances of their corresponding wrapper class.  Such
 * boxing may undermine any performance advantages gained by using the primitive
 * specializations.  To avoid boxing, the corresponding primitive-based methods
 * should be used.  For example,
 * {@link Spliterator.OfInt#tryAdvance(java.util.function.IntConsumer)}
 * and {@link Spliterator.OfInt#forEachRemaining(java.util.function.IntConsumer)}
 * should be used in preference to
 * {@link Spliterator.OfInt#tryAdvance(java.util.function.Consumer)} and
 * {@link Spliterator.OfInt#forEachRemaining(java.util.function.Consumer)}.
 * Traversal of primitive values using boxing-based methods
 * {@link #tryAdvance tryAdvance()} and
 * {@link #forEachRemaining(java.util.function.Consumer) forEachRemaining()}
 * does not affect the order in which the values, transformed to boxed values,
 * are encountered.
 * <p>提供了{@code Spliterator}的原始子类型特化
 * {@link OfInt int}，{@ link OfLong long}和{@link OfDouble double}值。
 * 子类型的默认实现
 * {@link Spliterator＃tryAdvance（java.util.function.Consumer）}
 * 和{@link Spliterator＃forEachRemaining（java.util.function.Consumer）}框
 * 原始值到其对应的包装类的实例。这样
 * 装箱可能会破坏使用原语获得的任何性能优势
 * 专业。为避免装箱，采用相应的基于原语的方法
 * 应该使用。例如，
 * {@link Spliterator.OfInt＃tryAdvance（java.util.function.IntConsumer）}
 * 和{@link Spliterator.OfInt＃forEachRemaining（java.util.function.IntConsumer）}
 * 应优先使用
 * {@link Spliterator.OfInt＃tryAdvance（java.util.function.Consumer）}和
 * {@link Spliterator.OfInt＃forEachRemaining（java.util.function.Consumer）}。
 * 使用基于拳击的方法遍历原始值
 * {@link #tryAdvance tryAdvance（）}和
 * {@link #forEachRemaining（java.util.function.Consumer）forEachRemaining（）}
 * 不影响值转换为盒装值的顺序，
 * 遇到了。
 * *
 *
 * @apiNote <p>Spliterators, like {@code Iterator}s, are for traversing the elements of
 * a source.  The {@code Spliterator} API was designed to support efficient
 * parallel traversal in addition to sequential traversal, by supporting
 * decomposition as well as single-element iteration.  In addition, the
 * protocol for accessing elements via a Spliterator is designed to impose
 * smaller per-element overhead than {@code Iterator}, and to avoid the inherent
 * race involved in having separate methods for {@code hasNext()} and
 * {@code next()}.
 * @apiNote <p> Spliterators，如{@code Iterator}，用于遍历元素
 * 来源。 {@code Spliterator} API旨在支持高效
 * 并行遍历除顺序遍历外，还通过支持
 * 分解以及单元素迭代。除此之外
 * 通过Spliterator访问元素的协议旨在强制执行
 * 比{@code Iterator}更小的每元素开销，并避免固有的
 * 种族涉及为{@code hasNext（）}提供单独的方法
 * {@code next（）}。
 * *
 * <p>For mutable sources, arbitrary and non-deterministic behavior may occur if
 * the source is structurally interfered with (elements added, replaced, or
 * removed) between the time that the Spliterator binds to its data source and
 * the end of traversal.  For example, such interference will produce arbitrary,
 * non-deterministic results when using the {@code java.util.stream} framework.
 * <p>对于可变来源，如果发生任意和非确定性行为
 * 源在结构上受到干扰（元素添加，替换或
 * 删除）Spliterator绑定到其数据源的时间和
 * 遍历结束。例如，这种干扰会产生任意的，
 * 使用{@code java.util.stream}框架时的非确定性结果。
 * *
 * <p>Structural interference of a source can be managed in the following ways
 * (in approximate order of decreasing desirability):
 * <p>可以通过以下方式管理源的结构干扰
 * （大致降低满意度的顺序）：
 * * <ul>
 * <li>The source cannot be structurally interfered with.
 * <br>For example, an instance of
 * {@link java.util.concurrent.CopyOnWriteArrayList} is an immutable source.
 * A Spliterator created from the source reports a characteristic of
 * {@code IMMUTABLE}.</li>
 * <li>The source manages concurrent modifications.
 * <br>For example, a key set of a {@link java.util.concurrent.ConcurrentHashMap}
 * is a concurrent source.  A Spliterator created from the source reports a
 * characteristic of {@code CONCURRENT}.</li>
 * <li>The mutable source provides a late-binding and fail-fast Spliterator.
 * <br>Late binding narrows the window during which interference can affect
 * the calculation; fail-fast detects, on a best-effort basis, that structural
 * interference has occurred after traversal has commenced and throws
 * {@link ConcurrentModificationException}.  For example, {@link ArrayList},
 * and many other non-concurrent {@code Collection} classes in the JDK, provide
 * a late-binding, fail-fast spliterator.</li>
 * <li>The mutable source provides a non-late-binding but fail-fast Spliterator.
 * <br>The source increases the likelihood of throwing
 * {@code ConcurrentModificationException} since the window of potential
 * interference is larger.</li>
 * <li>The mutable source provides a late-binding and non-fail-fast Spliterator.
 * <br>The source risks arbitrary, non-deterministic behavior after traversal
 * has commenced since interference is not detected.
 * </li>
 * <li>The mutable source provides a non-late-binding and non-fail-fast
 * Spliterator.
 * <br>The source increases the risk of arbitrary, non-deterministic behavior
 * since non-detected interference may occur after construction.
 * </li>
 * </ul>
 * * <ul>
 * <li>源不能在结构上受到干扰。
 * <br>例如，一个实例
 * {@link java.util.concurrent.CopyOnWriteArrayList}是一个不可变的源。
 * 从源创建的Spliterator报告的特征
 * {@code IMMUTABLE}。</ li>
 * <li>源管理并发修改。
 * <br>例如，{@link java.util.concurrent.ConcurrentHashMap}的密钥集
 * 是并发来源。从源创建的Spliterator报告a
 * {@code CONCURRENT}的特点。</ li>
 * <li>可变源提供后期绑定和失败快速的Spliterator。
 * <br>后期绑定会使窗口变窄，从而影响干扰
 * 计算;故障快速，尽力而为，检测到结构
 * 遍历开始并抛出后发生干扰
 * {@link ConcurrentModificationException}。例如，{@link ArrayList}，
 * 和JDK中的许多其他非并发{@code Collection}类提供
 * 一个后期绑定，失败快速的分裂器。</ li>
 * <li>可变源提供非后期绑定但失败快速的Spliterator。
 * <br>来源增加了投掷的可能性
 * {@code ConcurrentModificationException}自潜在的窗口
 * 干扰更大。</ li>
 * <li>可变源提供后期绑定和非故障快速Spliterator。
 * <br>源遍历遍历任意，非确定性行为的风险
 * 已开始，因为未检测到干扰。
 * </ li>
 * <li>可变源提供非后期绑定和非失败快速
 * Spliterator。
 * <br>源会增加任意，非确定性行为的风险
 * 因为在施工后可能会发生未检测到的干扰。
 * </ li>
 * </ ul>
 * <p>
 * *
 * <p><b>Example.</b> Here is a class (not a very useful one, except
 * for illustration) that maintains an array in which the actual data
 * are held in even locations, and unrelated tag data are held in odd
 * locations. Its Spliterator ignores the tags.
 * <p> <b>示例。</ b>这是一个类（不是非常有用的类，除了
 * 用于说明）维护一个数组，其中包含实际数据
 * 保存在偶数位置，不相关的标签数据保持在奇数位置
 * 地点。它的Spliterator忽略标签。
 * *
 * <pre> {@code
 * class TaggedArray<T> {
 *   private final Object[] elements; // immutable after construction
 *   TaggedArray(T[] data, Object[] tags) {
 *     int size = data.length;
 *     if (tags.length != size) throw new IllegalArgumentException();
 *     this.elements = new Object[2 * size];
 *     for (int i = 0, j = 0; i < size; ++i) {
 *       elements[j++] = data[i];
 *       elements[j++] = tags[i];
 *     }
 *   }
 *
 *   public Spliterator<T> spliterator() {
 *     return new TaggedArraySpliterator<>(elements, 0, elements.length);
 *   }
 *
 *   static class TaggedArraySpliterator<T> implements Spliterator<T> {
 *     private final Object[] array;
 *     private int origin; // current index, advanced on split or traversal
 *     private final int fence; // one past the greatest index
 *
 *     TaggedArraySpliterator(Object[] array, int origin, int fence) {
 *       this.array = array; this.origin = origin; this.fence = fence;
 *     }
 *
 *     public void forEachRemaining(Consumer<? super T> action) {
 *       for (; origin < fence; origin += 2)
 *         action.accept((T) array[origin]);
 *     }
 *
 *     public boolean tryAdvance(Consumer<? super T> action) {
 *       if (origin < fence) {
 *         action.accept((T) array[origin]);
 *         origin += 2;
 *         return true;
 *       }
 *       else // cannot advance
 *         return false;
 *     }
 *
 *     public Spliterator<T> trySplit() {
 *       int lo = origin; // divide range in half
 *       int mid = ((lo + fence) >>> 1) & ~1; // force midpoint to be even
 *       if (lo < mid) { // split out left half
 *         origin = mid; // reset this Spliterator's origin
 *         return new TaggedArraySpliterator<>(array, lo, mid);
 *       }
 *       else       // too small to split
 *         return null;
 *     }
 *
 *     public long estimateSize() {
 *       return (long)((fence - origin) / 2);
 *     }
 *
 *     public int characteristics() {
 *       return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
 *     }
 *   }
 * }}</pre>
 *
 * <p>As an example how a parallel computation framework, such as the
 * {@code java.util.stream} package, would use Spliterator in a parallel
 * computation, here is one way to implement an associated parallel forEach,
 * that illustrates the primary usage idiom of splitting off subtasks until
 * the estimated amount of work is small enough to perform
 * sequentially. Here we assume that the order of processing across
 * subtasks doesn't matter; different (forked) tasks may further split
 * and process elements concurrently in undetermined order.  This
 * example uses a {@link java.util.concurrent.CountedCompleter};
 * similar usages apply to other parallel task constructions.
 * <p>作为一个例子如何并行计算框架，如
 * {@code java.util.stream}包，将并行使用Spliterator
 * 计算，这是实现关联并行forEach的一种方法，
 * 它说明了直到分离子任务的主要用法习惯用法
 * 估计的工作量足够小，可以执行
 * 顺序。这里我们假设处理顺序
 * 子任务无关紧要;不同（分叉）的任务可能会进一步分裂
 * 并以不确定的顺序同时处理元素。这个
 * example使用{@link java.util.concurrent.CountedCompleter};
 * 类似的用法适用于其他并行任务结构。
 * *
 * <pre>{@code
 * static <T> void parEach(TaggedArray<T> a, Consumer<T> action) {
 *   Spliterator<T> s = a.spliterator();
 *   long targetBatchSize = s.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
 *   new ParEach(null, s, action, targetBatchSize).invoke();
 * }
 *
 * static class ParEach<T> extends CountedCompleter<Void> {
 *   final Spliterator<T> spliterator;
 *   final Consumer<T> action;
 *   final long targetBatchSize;
 *
 *   ParEach(ParEach<T> parent, Spliterator<T> spliterator,
 *           Consumer<T> action, long targetBatchSize) {
 *     super(parent);
 *     this.spliterator = spliterator; this.action = action;
 *     this.targetBatchSize = targetBatchSize;
 *   }
 *
 *   public void compute() {
 *     Spliterator<T> sub;
 *     while (spliterator.estimateSize() > targetBatchSize &&
 *            (sub = spliterator.trySplit()) != null) {
 *       addToPendingCount(1);
 *       new ParEach<>(this, sub, action, targetBatchSize).fork();
 *     }
 *     spliterator.forEachRemaining(action);
 *     propagateCompletion();
 *   }
 * }}</pre>
 * @implNote If the boolean system property {@code org.openjdk.java.util.stream.tripwire}
 * is set to {@code true} then diagnostic warnings are reported if boxing of
 * primitive values occur when operating on primitive subtype specializations.
 * @param <T> the type of elements returned by this Spliterator
 * @see Collection
 * @since 1.8
 * <p>
 * public interface Spliterator<T> {
 */
