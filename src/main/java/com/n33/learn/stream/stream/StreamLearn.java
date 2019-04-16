package com.n33.learn.stream.stream;

public class StreamLearn {
}
/**
 * A sequence of elements supporting sequential and parallel aggregate
 * operations.  The following example illustrates an aggregate operation using
 * {@link Stream} and {@link IntStream}:
 * 支持顺序和并行聚合的一系列元素
 * 运营。以下示例说明了使用的聚合操作
 * {@link stream}和{@link intstream}： *
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * <p>
 * In this example, {@code widgets} is a {@code Collection<Widget>}.  We create
 * a stream of {@code Widget} objects via {@link Collection#stream Collection.stream()},
 * filter it to produce a stream containing only the red widgets, and then
 * transform it into a stream of {@code int} values representing the weight of
 * each red widget. Then this stream is summed to produce a total weight.
 * 在此示例中，{@ code widgets}是{@code Collection <Widget>}。我们创造
 * 来自{@link Collection＃stream Collection.stream（）}的{@code Widget}对象流，
 * 过滤它以生成仅包含红色小部件的流，然后
 * 将其转换为代表权重的{@code int}值流
 * 每个红色小部件。然后将该流相加以产生总重量。 *
 *
 * <p>In addition to {@code Stream}, which is a stream of object references,
 * there are primitive specializations for {@link IntStream}, {@link LongStream},
 * and {@link DoubleStream}, all of which are referred to as "streams" and
 * conform to the characteristics and restrictions described here.
 * <p>除了{@code Stream}，它是一个对象引用流，
 * {@link IntStream}，{@ link LongStream}有原始专业，
 * 和{@link DoubleStream}，所有这些都被称为“流”和
 * 符合此处描述的特征和限制。 *
 *
 * <p>To perform a computation, stream
 * <a href="package-summary.html#StreamOps">operations</a> are composed into a
 * <em>stream pipeline</em>.  A stream pipeline consists of a source (which
 * might be an array, a collection, a generator function, an I/O channel,
 * etc), zero or more <em>intermediate operations</em> (which transform a
 * stream into another stream, such as {@link Stream#filter(Predicate)}), and a
 * <em>terminal operation</em> (which produces a result or side-effect, such
 * as {@link Stream#count()} or {@link Stream#forEach(Consumer)}).
 * Streams are lazy; computation on the source data is only performed when the
 * terminal operation is initiated, and source elements are consumed only
 * as needed.
 * <p>执行计算，流
 * <a href="package-summary.html#StreamOps">操作</a>组成一个
 * <em>流管道</ em>。流管道由一个源组成
 * 可能是一个数组，一个集合，一个生成器函数，一个I / O通道，
 * 等），零个或多个<em>中间操作</ em>（转换a
 * 流入另一个流，例如{@link Stream＃filter（Predicate）}）和a
 * <em>终端操作</ em>（产生结果或副作用，例如
 * 作为{@link Stream＃count（）}或{@link Stream＃forEach（Consumer）}）。
 * 流是懒惰的;只对数据进行源数据计算
 * 启动终端操作，仅消耗源元素
 * 如所须。 *
 *
 * <p>Collections and streams, while bearing some superficial similarities,
 * have different goals.  Collections are primarily concerned with the efficient
 * management of, and access to, their elements.  By contrast, streams do not
 * provide a means to directly access or manipulate their elements, and are
 * instead concerned with declaratively describing their source and the
 * computational operations which will be performed in aggregate on that source.
 * However, if the provided stream operations do not offer the desired
 * functionality, the {@link #iterator()} and {@link #spliterator()} operations
 * can be used to perform a controlled traversal.
 * <p>收藏品和流，虽然有一些肤浅的相似之处，
 * 有不同的目标。馆藏主要关注的是效率
 * 管理和访问其元素。相比之下，溪流没有
 * 提供直接访问或操纵其元素的方法，并且是
 * 而是关注声明性地描述他们的来源和
 * 计算操作将在该源上汇总执行。
 * 但是，如果提供的流操作不提供所需的
 * 功能，{@ link #iterator（）}和{@link #spliterator（）}操作
 * 可用于执行受控遍历。
 *
 * <p>A stream pipeline, like the "widgets" example above, can be viewed as
 * a <em>query</em> on the stream source.  Unless the source was explicitly
 * designed for concurrent modification (such as a {@link ConcurrentHashMap}),
 * unpredictable or erroneous behavior may result from modifying the stream
 * source while it is being queried.
 * <p>流管道，如上面的“小部件”示例，可以视为
 * 流源上的<em>查询</ em>。除非来源是明确的
 * 专为并发修改而设计（例如{@link concurrenthashmap}），
 * 修改流可能导致不可预测或错误的行为
 * 正在查询时的来源。
 *
 *
 * <p>Most stream operations accept parameters that describe user-specified
 * behavior, such as the lambda expression {@code w -> w.getWeight()} passed to
 * {@code mapToInt} in the example above.  To preserve correct behavior,
 * these <em>behavioral parameters</em>:
 * <p>大多数流操作接受描述用户指定的参数
 * 行为，例如传递给lambda表达式{@code w  - > w.getWeight（）}
 * 上面示例中的{@code mapToInt}。为了保持正确的行为，
 * 这些<em>行为参数</ em>：
 *
 * <ul>
 * <li>must be <a href="package-summary.html#NonInterference">non-interfering</a>
 * (they do not modify the stream source); and</li>
 * <li>in most cases must be <a href="package-summary.html#Statelessness">stateless</a>
 * (their result should not depend on any state that might change during execution
 * of the stream pipeline).</li>
 * </ul>
 * <ul>
 * <li>必须<a href="package-summary.html#NonInterference">不干扰</a>
 * （他们不修改流源）;和</ LI>
 * <li>在大多数情况下必须是<a href="package-summary.html#Statelessness">无状态</a>
 * （他们的结果不应取决于执行期间可能发生变化的任何状态
 * 流管道）。</ li>
 * </ ul>
 * <p>
 * <p>
 * *
 * <p>Such parameters are always instances of a
 * <a href="../function/package-summary.html">functional interface</a> such
 * as {@link java.util.function.Function}, and are often lambda expressions or
 * method references.  Unless otherwise specified these parameters must be
 * <em>non-null</em>.
 * <p>这些参数总是a的实例
 * <a href="../function/package-summary.html">功能界面</a>等
 * 作为{@link java.util.function.Function}，通常是lambda表达式或
 * 方法参考。除非另有说明，否则这些参数必须
 * <em>非null </ em>。
 * <p>
 * *
 * <p>A stream should be operated on (invoking an intermediate or terminal stream
 * operation) only once.  This rules out, for example, "forked" streams, where
 * the same source feeds two or more pipelines, or multiple traversals of the
 * same stream.  A stream implementation may throw {@link IllegalStateException}
 * if it detects that the stream is being reused. However, since some stream
 * operations may return their receiver rather than a new stream object, it may
 * not be possible to detect reuse in all cases.
 * <p>应该对一个流进行操作（调用中间流或终端流
 * 操作）只有一次。这排除了，例如，“分叉”流，其中
 * 相同的源提供两个或多个管道，或多个遍历
 * 相同的流。流实现可能抛出{@link IllegalStateException}
 * 如果它检测到正在重用流。但是，因为有些流
 * 操作可以返回其接收器而不是新的流对象
 * 无法在所有情况下检测重用。
 * <p>
 * <p>
 * *
 * <p>Streams have a {@link #close()} method and implement {@link AutoCloseable},
 * but nearly all stream instances do not actually need to be closed after use.
 * Generally, only streams whose source is an IO channel (such as those returned
 * by {@link Files#lines(Path, Charset)}) will require closing.  Most streams
 * are backed by collections, arrays, or generating functions, which require no
 * special resource management.  (If a stream does require closing, it can be
 * declared as a resource in a {@code try}-with-resources statement.)
 * <p> Streams有一个{@link #close（）}方法并实现{@link AutoCloseable}，
 * 但几乎所有的流实例实际上都不需要在使用后关闭。
 * 通常，只有源为IO通道的流（例如返回的流）
 * 由{@link Files＃lines（Path，Charset）}）将需要关闭。大多数流
 * 由集合，数组或生成函数支持，不需要
 * 特殊资源管理。 （如果流确实需要关闭，则可以
 * 在{@code try} -with-resources语句中声明为资源。）
 * <p>
 * *
 * <p>Stream pipelines may execute either sequentially or in
 * <a href="package-summary.html#Parallelism">parallel</a>.  This
 * execution mode is a property of the stream.  Streams are created
 * with an initial choice of sequential or parallel execution.  (For example,
 * {@link Collection#stream() Collection.stream()} creates a sequential stream,
 * and {@link Collection#parallelStream() Collection.parallelStream()} creates
 * a parallel one.)  This choice of execution mode may be modified by the
 * {@link #sequential()} or {@link #parallel()} methods, and may be queried with
 * the {@link #isParallel()} method.
 * <p>流管道可以按顺序执行，也可以执行
 * <a href="package-summary.html#Parallelism">并行</a>。这个
 * 执行模式是流的属性。流已创建
 * 初始选择顺序或并行执行。 （例如，
 * {@link Collection＃stream（）Collection.stream（）}创建一个顺序流，
 * 和{@link Collection＃parallelStream（）Collection.parallelStream（）}创建
 * 并行的。）这种执行模式的选择可以通过修改
 * {@link #sequential（）}或{@link #parallel（）}方法，可以查询
 * {@link #isParallel（）}方法。 *
 *
 * @param <T> the type of the stream elements
 * @param <S> the type of of the stream implementing {@code BaseStream}
 * @return {@code true} if this stream would execute in parallel if executed
 * <p>
 * <p>
 * <p>
 * 返回此流是否要执行终端操作，
 * 将并行执行。调用后调用此方法
 * 终端流操作方法可能产生不可预测的结果。
 * @return {@code true}如果此流将在执行时并行执行 *
 * <p>
 * boolean isParallel();
 * @return {@code true} if this stream would execute in parallel if executed
 * <p>
 * <p>
 * <p>
 * 返回此流是否要执行终端操作，
 * 将并行执行。调用后调用此方法
 * 终端流操作方法可能产生不可预测的结果。
 * @return {@code true}如果此流将在执行时并行执行 *
 * <p>
 * boolean isParallel();
 * @return {@code true} if this stream would execute in parallel if executed
 * <p>
 * <p>
 * <p>
 * 返回此流是否要执行终端操作，
 * 将并行执行。调用后调用此方法
 * 终端流操作方法可能产生不可预测的结果。
 * @return {@code true}如果此流将在执行时并行执行 *
 * <p>
 * boolean isParallel();
 * <p>
 * Returns an equivalent stream that is sequential.  May return
 * itself, either because the stream was already sequential, or because
 * the underlying stream state was modified to be sequential.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return a sequential stream
 * @return {@code true} if this stream would execute in parallel if executed
 * <p>
 * <p>
 * <p>
 * 返回此流是否要执行终端操作，
 * 将并行执行。调用后调用此方法
 * 终端流操作方法可能产生不可预测的结果。
 * @return {@code true}如果此流将在执行时并行执行 *
 * <p>
 * boolean isParallel();
 * <p>
 * Returns an equivalent stream that is sequential.  May return
 * itself, either because the stream was already sequential, or because
 * the underlying stream state was modified to be sequential.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return a sequential stream
 * 返回顺序的等效流。可能会回来
 * 本身，要么是因为流已经是顺序的，要么是因为
 * 基础流状态被修改为顺序。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 * 操作</a>。
 * @return顺序流 *
 * @return {@code true} if this stream would execute in parallel if executed
 * <p>
 * <p>
 * <p>
 * 返回此流是否要执行终端操作，
 * 将并行执行。调用后调用此方法
 * 终端流操作方法可能产生不可预测的结果。
 * @return {@code true}如果此流将在执行时并行执行 *
 * <p>
 * boolean isParallel();
 * <p>
 * Returns an equivalent stream that is sequential.  May return
 * itself, either because the stream was already sequential, or because
 * the underlying stream state was modified to be sequential.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return a sequential stream
 * 返回顺序的等效流。可能会回来
 * 本身，要么是因为流已经是顺序的，要么是因为
 * 基础流状态被修改为顺序。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 * 操作</a>。
 * @return顺序流 *
 * S sequential();
 * <p>
 * Returns an equivalent stream that is parallel.  May return
 * itself, either because the stream was already parallel, or because
 * the underlying stream state was modified to be parallel.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return a parallel stream
 * 返回并行的等效流。可能会回来
 * 本身，要么因为流已经并行，要么因为
 * 基础流状态被修改为并行。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 * 操作</a>。
 * @return并行流 *S parallel();
 * <p>
 * <p>
 * Returns an equivalent stream that is
 * <a href="package-summary.html#Ordering">unordered</a>.  May return
 * itself, either because the stream was already unordered, or because
 * the underlying stream state was modified to be unordered.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return an unordered stream
 * 返回一个等效的流
 * <a href="package-summary.html#Ordering">无序</a>。可能会回来
 * 本身，要么是因为流已经无序，要么是因为
 * 基础流状态被修改为无序。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 * 操作</a>。
 * @return无序流 * S unordered();
 * <p>
 * <p>
 * Returns an equivalent stream with an additional close handler.  Close
 * handlers are run when the {@link #close()} method
 * is called on the stream, and are executed in the order they were
 * added.  All close handlers are run, even if earlier close handlers throw
 * exceptions.  If any close handler throws an exception, the first
 * exception thrown will be relayed to the caller of {@code close()}, with
 * any remaining exceptions added to that exception as suppressed exceptions
 * (unless one of the remaining exceptions is the same exception as the
 * first exception, since an exception cannot suppress itself.)  May
 * return itself.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @param closeHandler A task to execute when the stream is closed
 * @return a stream with a handler that is run if the stream is closed
 * 返回具有附加关闭处理程序的等效流。关
 * 处理程序在{@link #close（）}方法运行
 * 在流上调用，并按照它们的顺序执行
 * 添加。即使较早的关闭处理程序抛出，所有关闭处理程序也会运行
 * 例外。如果任何关闭处理程序抛出异常，则第一个
 * 抛出的异常将被转发给{@code close（）}的调用者
 * 任何剩余的异常作为抑制异常添加到该异常中
 * （除非剩下的一个例外是与之相同的例外
 * 第一个例外，因为异常无法抑制自己。）5月
 * 返回自己。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 * 操作</a>。
 * @param closeHandler流关闭时执行的任务
 * @return一个流，其中包含在流关闭时运行的处理程序 * S onClose(Runnable closeHandler);
 * <p>
 * <p>
 * Closes this stream, causing all close handlers for this stream pipeline
 * to be called.
 * @Override void close();
 * @see IntStream
 * @see LongStream
 * @see DoubleStream
 * @see <a href="package-summary.html">java.util.stream</a>
 * @see Stream
 * @see IntStream
 * @see LongStream
 * @see DoubleStream
 * @see <a href="package-summary.html">java.util.stream</a>
 * @see AutoCloseable#close()
 * 关闭此流，导致此流管道的所有关闭处理程序
 * 被称为。
 * @see AutoCloseable #close（）
 * *
 * @since 1.8
 * public interface Stream<T> extends BaseStream<T, Stream<T>> {
 *
 *
 * <p>
 * <p>
 * Base interface for streams, which are sequences of elements supporting
 * sequential and parallel aggregate operations.  The following example
 * illustrates an aggregate operation using the stream types {@link Stream}
 * and {@link IntStream}, computing the sum of the weights of the red widgets:
 * 流的基本接口，它是支持的元素序列
 * 顺序和并行聚合操作。以下示例
 * 说明了使用流类型{@link Stream}的聚合操作
 * 和{@link IntStream}，计算红色小部件的权重总和：
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See the class documentation for {@link Stream} and the package documentation
 * for <a href="package-summary.html">java.util.stream</a> for additional
 * specification of streams, stream operations, stream pipelines, and
 * parallelism, which governs the behavior of all stream types.
 * 请参阅{@link Stream}的类文档和包文档
 * 用于<a href="package-summary.html"> java.util.stream </a>以获取更多信息
 * 流，流操作，流管道和流的规范
 * parallelism，它控制所有流类型的行为。
 * * @param <T> the type of the stream elements
 * @since 1.8
 * <p>
 * <p>
 * public interface BaseStream<T, S extends BaseStream<T, S>>
 * extends AutoCloseable {
 * <p>
 * <p>
 * <p>
 * Returns whether this stream, if a terminal operation were to be executed,
 * would execute in parallel.  Calling this method after invoking an
 * terminal stream operation method may yield unpredictable results.
 *
 * <p>
 * <p>
 * Base interface for streams, which are sequences of elements supporting
 * sequential and parallel aggregate operations.  The following example
 * illustrates an aggregate operation using the stream types {@link Stream}
 * and {@link IntStream}, computing the sum of the weights of the red widgets:
 * 流的基本接口，它是支持的元素序列
 * 顺序和并行聚合操作。以下示例
 * 说明了使用流类型{@link Stream}的聚合操作
 * 和{@link IntStream}，计算红色小部件的权重总和：
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See the class documentation for {@link Stream} and the package documentation
 * for <a href="package-summary.html">java.util.stream</a> for additional
 * specification of streams, stream operations, stream pipelines, and
 * parallelism, which governs the behavior of all stream types.
 * 请参阅{@link Stream}的类文档和包文档
 * 用于<a href="package-summary.html"> java.util.stream </a>以获取更多信息
 * 流，流操作，流管道和流的规范
 * parallelism，它控制所有流类型的行为。
 * * @param <T> the type of the stream elements
 * @since 1.8
 * <p>
 * <p>
 * public interface BaseStream<T, S extends BaseStream<T, S>>
 * extends AutoCloseable {
 * <p>
 * <p>
 * <p>
 * Returns whether this stream, if a terminal operation were to be executed,
 * would execute in parallel.  Calling this method after invoking an
 * terminal stream operation method may yield unpredictable results.
 *
 * <p>
 * <p>
 * Base interface for streams, which are sequences of elements supporting
 * sequential and parallel aggregate operations.  The following example
 * illustrates an aggregate operation using the stream types {@link Stream}
 * and {@link IntStream}, computing the sum of the weights of the red widgets:
 * 流的基本接口，它是支持的元素序列
 * 顺序和并行聚合操作。以下示例
 * 说明了使用流类型{@link Stream}的聚合操作
 * 和{@link IntStream}，计算红色小部件的权重总和：
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See the class documentation for {@link Stream} and the package documentation
 * for <a href="package-summary.html">java.util.stream</a> for additional
 * specification of streams, stream operations, stream pipelines, and
 * parallelism, which governs the behavior of all stream types.
 * 请参阅{@link Stream}的类文档和包文档
 * 用于<a href="package-summary.html"> java.util.stream </a>以获取更多信息
 * 流，流操作，流管道和流的规范
 * parallelism，它控制所有流类型的行为。
 * * @param <T> the type of the stream elements
 * @since 1.8
 * <p>
 * <p>
 * public interface BaseStream<T, S extends BaseStream<T, S>>
 * extends AutoCloseable {
 * <p>
 * <p>
 * <p>
 * Returns whether this stream, if a terminal operation were to be executed,
 * would execute in parallel.  Calling this method after invoking an
 * terminal stream operation method may yield unpredictable results.
 *
 * <p>
 * <p>
 * Base interface for streams, which are sequences of elements supporting
 * sequential and parallel aggregate operations.  The following example
 * illustrates an aggregate operation using the stream types {@link Stream}
 * and {@link IntStream}, computing the sum of the weights of the red widgets:
 * 流的基本接口，它是支持的元素序列
 * 顺序和并行聚合操作。以下示例
 * 说明了使用流类型{@link Stream}的聚合操作
 * 和{@link IntStream}，计算红色小部件的权重总和：
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See the class documentation for {@link Stream} and the package documentation
 * for <a href="package-summary.html">java.util.stream</a> for additional
 * specification of streams, stream operations, stream pipelines, and
 * parallelism, which governs the behavior of all stream types.
 * 请参阅{@link Stream}的类文档和包文档
 * 用于<a href="package-summary.html"> java.util.stream </a>以获取更多信息
 * 流，流操作，流管道和流的规范
 * parallelism，它控制所有流类型的行为。
 * * @param <T> the type of the stream elements
 * @since 1.8
 * <p>
 * <p>
 * public interface BaseStream<T, S extends BaseStream<T, S>>
 * extends AutoCloseable {
 * <p>
 * <p>
 * <p>
 * Returns whether this stream, if a terminal operation were to be executed,
 * would execute in parallel.  Calling this method after invoking an
 * terminal stream operation method may yield unpredictable results.
 *
 * <p>
 * <p>
 * Base interface for streams, which are sequences of elements supporting
 * sequential and parallel aggregate operations.  The following example
 * illustrates an aggregate operation using the stream types {@link Stream}
 * and {@link IntStream}, computing the sum of the weights of the red widgets:
 * 流的基本接口，它是支持的元素序列
 * 顺序和并行聚合操作。以下示例
 * 说明了使用流类型{@link Stream}的聚合操作
 * 和{@link IntStream}，计算红色小部件的权重总和：
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See the class documentation for {@link Stream} and the package documentation
 * for <a href="package-summary.html">java.util.stream</a> for additional
 * specification of streams, stream operations, stream pipelines, and
 * parallelism, which governs the behavior of all stream types.
 * 请参阅{@link Stream}的类文档和包文档
 * 用于<a href="package-summary.html"> java.util.stream </a>以获取更多信息
 * 流，流操作，流管道和流的规范
 * parallelism，它控制所有流类型的行为。
 * * @param <T> the type of the stream elements
 * @since 1.8
 * <p>
 * <p>
 * public interface BaseStream<T, S extends BaseStream<T, S>>
 * extends AutoCloseable {
 * <p>
 * <p>
 * <p>
 * Returns whether this stream, if a terminal operation were to be executed,
 * would execute in parallel.  Calling this method after invoking an
 * terminal stream operation method may yield unpredictable results.
 */


/**
 * <p>
 * <p>
 * Base interface for streams, which are sequences of elements supporting
 * sequential and parallel aggregate operations.  The following example
 * illustrates an aggregate operation using the stream types {@link Stream}
 * and {@link IntStream}, computing the sum of the weights of the red widgets:
 * 流的基本接口，它是支持的元素序列
 * 顺序和并行聚合操作。以下示例
 * 说明了使用流类型{@link Stream}的聚合操作
 * 和{@link IntStream}，计算红色小部件的权重总和：
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 * See the class documentation for {@link Stream} and the package documentation
 * for <a href="package-summary.html">java.util.stream</a> for additional
 * specification of streams, stream operations, stream pipelines, and
 * parallelism, which governs the behavior of all stream types.
 * 请参阅{@link Stream}的类文档和包文档
 * 用于<a href="package-summary.html"> java.util.stream </a>以获取更多信息
 * 流，流操作，流管道和流的规范
 * parallelism，它控制所有流类型的行为。
 * * @param <T> the type of the stream elements
 * @since 1.8
 *
 *
 * public interface BaseStream<T, S extends BaseStream<T, S>>
 *         extends AutoCloseable {
 *
 *
 *
 * Returns whether this stream, if a terminal operation were to be executed,
 * would execute in parallel.  Calling this method after invoking an
 * terminal stream operation method may yield unpredictable results.
 * @return {@code true} if this stream would execute in parallel if executed
 *
 *
 *
 *  返回此流是否要执行终端操作，
 *  将并行执行。调用后调用此方法
 *  终端流操作方法可能产生不可预测的结果。
 *   @return {@code true}如果此流将在执行时并行执行 *
 *
 *  boolean isParallel();
 *
 * Returns an equivalent stream that is sequential.  May return
 * itself, either because the stream was already sequential, or because
 * the underlying stream state was modified to be sequential.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return a sequential stream
 *返回顺序的等效流。可能会回来
 *本身，要么是因为流已经是顺序的，要么是因为
 *基础流状态被修改为顺序。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 *操作</a>。
 * @return顺序流 *
 * S sequential();
 *
 * Returns an equivalent stream that is parallel.  May return
 * itself, either because the stream was already parallel, or because
 * the underlying stream state was modified to be parallel.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return a parallel stream
 *返回并行的等效流。可能会回来
 *本身，要么因为流已经并行，要么因为
 *基础流状态被修改为并行。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 *操作</a>。
 * @return并行流
 * *S parallel();
 *
 *
 * Returns an equivalent stream that is
 * <a href="package-summary.html#Ordering">unordered</a>.  May return
 * itself, either because the stream was already unordered, or because
 * the underlying stream state was modified to be unordered.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @return an unordered stream
 *返回一个等效的流
 * <a href="package-summary.html#Ordering">无序</a>。可能会回来
 *本身，要么是因为流已经无序，要么是因为
 *基础流状态被修改为无序。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 *操作</a>。
 * @return无序流
 * * S unordered();
 *
 *
 * Returns an equivalent stream with an additional close handler.  Close
 * handlers are run when the {@link #close()} method
 * is called on the stream, and are executed in the order they were
 * added.  All close handlers are run, even if earlier close handlers throw
 * exceptions.  If any close handler throws an exception, the first
 * exception thrown will be relayed to the caller of {@code close()}, with
 * any remaining exceptions added to that exception as suppressed exceptions
 * (unless one of the remaining exceptions is the same exception as the
 * first exception, since an exception cannot suppress itself.)  May
 * return itself.
 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
 * operation</a>.
 * @param closeHandler A task to execute when the stream is closed
 * @return a stream with a handler that is run if the stream is closed
 *返回具有附加关闭处理程序的等效流。关
 *处理程序在{@link #close（）}方法运行
 *在流上调用，并按照它们的顺序执行
 * 添加。即使较早的关闭处理程序抛出，所有关闭处理程序也会运行
 *例外。如果任何关闭处理程序抛出异常，则第一个
 *抛出的异常将被转发给{@code close（）}的调用者
 *任何剩余的异常作为抑制异常添加到该异常中
 *（除非剩下的一个例外是与之相同的例外
 *第一个例外，因为异常无法抑制自己。）5月
 *返回自己。
 * <p>这是一个<a href="package-summary.html#StreamOps">中级
 *操作</a>。
 * @param closeHandler流关闭时执行的任务
 * @return一个流，其中包含在流关闭时运行的处理程序
 * * S onClose(Runnable closeHandler);
 *
 *
 * Closes this stream, causing all close handlers for this stream pipeline
 * to be called.
 * @see AutoCloseable#close()
 *关闭此流，导致此流管道的所有关闭处理程序
 * 被称为。
 * @see AutoCloseable #close（）
 * *
 * @Override
 *     void close();
 *
 *
 */
