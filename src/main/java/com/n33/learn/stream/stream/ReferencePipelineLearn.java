package com.n33.learn.stream.stream;

import java.util.stream.BaseStream;

public class ReferencePipelineLearn {
}
/**
 * ReferencePipeline表示流的源阶段与中间阶段
 * ReferencePipeline.Head表示流的源阶段
 * <p>
 * 二者相似但有些属性不同，ReferencePipeline.Head没有previousStage,反之有
 * <p>
 * <p>
 * <p>
 * Abstract base class for "pipeline" classes, which are the core
 * implementations of the Stream interface and its primitive specializations.
 * Manages construction and evaluation of stream pipelines.
 * “管道”类的抽象基类，这是核心
 * Stream接口的实现及其原始特化。
 * 管理流管道的建设和评估。
 * <p>
 * <p>
 * *
 * <p>An {@code AbstractPipeline} represents an initial portion of a stream
 * pipeline, encapsulating a stream source and zero or more intermediate
 * operations.  The individual {@code AbstractPipeline} objects are often
 * referred to as <em>stages</em>, where each stage describes either the stream
 * source or an intermediate operation.
 * <p> {@code AbstractPipeline}代表流的初始部分
 * 管道，封装流源和零或多个中间
 * 运营。个别的{@code AbstractPipeline}对象经常出现
 * 称为<em>阶段</ em>，其中每个阶段描述流
 * 来源或中间操作。
 * <p>
 * *
 * <p>A concrete intermediate stage is generally built from an
 * {@code AbstractPipeline}, a shape-specific pipeline class which extends it
 * (e.g., {@code IntPipeline}) which is also abstract, and an operation-specific
 * concrete class which extends that.  {@code AbstractPipeline} contains most of
 * the mechanics of evaluating the pipeline, and implements methods that will be
 * used by the operation; the shape-specific classes add helper methods for
 * dealing with collection of results into the appropriate shape-specific
 * containers.
 * <p>具体的中间阶段通常是由一个人构建的
 * {@code AbstractPipeline}，一个特定于形状的管道类，用于扩展它
 * （例如，{@code IntPipeline}），它也是抽象的，并且是特定于操作的
 * 扩展的具体类。 {@code AbstractPipeline}包含大部分内容
 * 评估管道的机制，并实现将要的方法
 * 操作使用;特定于形状的类为其添加辅助方法
 * 处理结果收集到适当的形状特定
 * 容器。
 * <p>
 * *
 * <p>After chaining a new intermediate operation, or executing a terminal
 * operation, the stream is considered to be consumed, and no more intermediate
 * or terminal operations are permitted on this stream instance.
 * <p>链接新的中间操作或执行终端后
 * 操作，流被认为是消耗的，并且不再是中间的
 * 或此流实例上允许终端操作。
 * <p>
 * *
 *
 * @implNote <p>For sequential streams, and parallel streams without
 * <a href="package-summary.html#StreamOps">stateful intermediate
 * operations</a>, parallel streams, pipeline evaluation is done in a single
 * pass that "jams" all the operations together.  For parallel streams with
 * stateful operations, execution is divided into segments, where each
 * stateful operations marks the end of a segment, and each segment is
 * evaluated separately and the result used as the input to the next
 * segment.  In all cases, the source data is not consumed until a terminal
 * operation begins.
 * @implNote <p>对于顺序流和没有的并行流
 * <a href="package-summary.html#StreamOps">有状态中间人
 * 操作</a>，并行流，管道评估在单个中完成
 * 将所有操作“堵塞”在一起。对于并行流
 * 有状态操作，执行分为段，每个段
 * 有状态操作标记段的结束，每个段都是
 * 单独评估，结果用作下一个的输入
 * 分割。在所有情况下，直到终端才消耗源数据
 * 开始运作。
 * <p>
 * * @param <E_IN>  type of input elements
 * @param <E_OUT> type of output elements
 * @param <S> type of the subclass implementing {@code BaseStream}
 * @since 1.8
 * <p>
 * abstract class AbstractPipeline<E_IN, E_OUT, S extends BaseStream<E_OUT, S>>
 * extends PipelineHelper<E_OUT> implements BaseStream<E_OUT, S> {
 */
