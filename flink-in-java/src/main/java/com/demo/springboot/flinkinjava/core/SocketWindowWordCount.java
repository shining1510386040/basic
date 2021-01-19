package com.demo.springboot.flinkinjava.core;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description demo测试
 * @date 2021/1/15 15:01
 * @see
 */
public class SocketWindowWordCount {


    public static void main(String[] args) throws Exception {
        // 1.创建  flink流处理执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 2.通过连接 socket 获取输入数据，这里连接到本地9000端口，如果9000端口已被占用，请换一个端口
        DataStream text = env.socketTextStream("localhost", 9000, "\n");

        // 3.解析数据，按 word 分组，开窗，聚合
        DataStream<WordWithCount> windowCount = text
                //打平操作，把每行的单词转为<word,count>类型的数据
                .flatMap(new FlatMapFunction<String, WordWithCount>() {
                    @Override
                    public void flatMap(String value, Collector<WordWithCount> out) {
                        // 按#分割
                        String[] splits = value.split("#");
                        for (String word : splits) {
                            out.collect(new WordWithCount(word, 1L));
                        }
                    }
                })
                //针对相同的word数据进行分组
                .keyBy("word")
                //指定计算数据的窗口大小和滑动窗口大小
                .timeWindow(Time.seconds(2), Time.seconds(1))
                .sum("count");

        //把数据打印到控制台,使用一个并行度
        windowCount.print().setParallelism(1);
        //注意：因为flink是懒加载的，所以必须调用execute方法，上面的代码才会执行
        env.execute("streaming word count");
    }

    /**
     * 单词和出现的次数
     */
    public static class WordWithCount {
        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WordWithCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
