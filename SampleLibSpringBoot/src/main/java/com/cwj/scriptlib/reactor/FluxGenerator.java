package com.cwj.scriptlib.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * com.cwj.scriptlib.reactor
 *
 * 这是一种 同步地， 逐个地 产生值的方法，意味着 sink 是一个 SynchronousSink 而且其 next() 方法在每次回调的时候最多只能被调用一次。
 * 也可以调用 error(Throwable) 或者 complete()，不过是可选的。
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-10 21:00
 */
public class FluxGenerator {

    public static void main(String[] args) {
        /**
         * 可编程式创建数据序列 lambda 版
         */
        Flux.generate(() -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }).subscribe(System.out::println);


        /**
         * 可编程式创建数据序列 非lambda 版：2个参数
         *
         * 参数说明：
         * - Callable<S> stateSupplier
         *   为每个传入的订阅者调用以提供生成器双功能的初始状态
         *
         * – iFunction<S, SynchronousSink<T>, S> generator
         *   使用 Reactor 为每个订阅者提供的SynchronousSink以及当前状态，以在每次传递时生成单个信号并返回（新）状态。
         *
         * 类型参数：
         * <S> – 每个订阅者的自定义状态类
         * <T> – 发出的值类型
         *
         * 使用步骤：
         * 1. 确定好状态记录类型、发出值类型
         * 2. 初始化状态记录
         * 3. 定义发出值格式
         * 4. 修改状态类型的记录值，供下一次调用
         */
        Flux.generate(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 2. 初始化状态记录
                return 0;
            }
        }, new BiFunction<Integer, SynchronousSink<String>, Integer>() {
            @Override
            public Integer apply(Integer state, SynchronousSink<String> sink) {
                // 3. 定义发出值格式
                sink.next("3 x " + state + " = " + 3 * state);
                if (state == 10) {
                    sink.complete();
                }
                // 4. 修改状态类型的记录值，供下一次调用
                return state + 1;
            }
        }).subscribe(System.out::println);

        /**
         * 3个参数版
         *
         * stateConsumer – 在生成器终止或下游取消后调用，接收要处理的最后一个状态（即释放资源或进行其他清理）
         */
        Flux.generate(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        }, new BiFunction<Integer, SynchronousSink<String>, Integer>() {
            @Override
            public Integer apply(Integer state, SynchronousSink<String> sink) {
                sink.next("3 x " + state + " = " + 3 * state);
                if (state == 10) {
                    sink.complete();
                }
                return state + 1;
            }
        }, new Consumer<Integer>() {
            @Override
            public void accept(Integer stateConsumer) {
                System.out.println("consumer" + stateConsumer);
            }
        }).subscribe(System.out::println);
    }
}
