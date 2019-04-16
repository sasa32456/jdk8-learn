package com.n33.learn.stream.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        final NullPointerException my_exception = new NullPointerException("my exception");

        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaa");
                //throw new NullPointerException("first exception");
                throw my_exception;
            }).onClose(() -> {
                System.out.println("bbb");
                //throw new ArithmeticException("second exception");
                //throw new NullPointerException("second exception");
                throw my_exception;
            }).forEach(System.out::println);
        }

    }
}
/**压制异常
 * Exception in thread "main" java.lang.NullPointerException: first exception
 * 	at com.n33.learn.stream.stream.StreamTest2.lambda$main$0(StreamTest2.java:15)
 * 	at java.util.stream.Streams$1.run(Streams.java:850)
 * 	at java.util.stream.AbstractPipeline.close(AbstractPipeline.java:323)
 * 	at com.n33.learn.stream.stream.StreamTest2.main(StreamTest2.java:20)
 * 	Suppressed: java.lang.ArithmeticException: second exception
 * 		at com.n33.learn.stream.stream.StreamTest2.lambda$main$1(StreamTest2.java:18)
 * 		at java.util.stream.Streams$1.run(Streams.java:854)
 * 		... 2 more
 *
 * 同一个不压制
 * Exception in thread "main" java.lang.NullPointerException: my exception
 * 	at com.n33.learn.stream.stream.StreamTest2.main(StreamTest2.java:12)
 *
 */
