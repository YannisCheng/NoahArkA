package com.cwj.scriptlib.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/**
 * com.cwj.scriptlib.reactor
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-10 21:03
 */
public class FluxCreate {
    public static void main(String[] args) {

        /**
         * create
         * 生成方式既可以是同步， 也可以是异步的，并且还可以每次发出多个元素。
         */
        /*Flux.create(new Consumer<FluxSink<String>>() {
            @Override
            public void accept(FluxSink<String> fluxSink) {
                // 可以将现有的 API 转为响应式，比如监听器的异步方法。
                new MyEventListener<String>() {
                    @Override
                    public void onDataChunk(List<String> chunk) {
                        for (String item: chunk) {
                            fluxSink.next(item);
                        }
                    }

                    @Override
                    public void processComplete() {
                        fluxSink.complete();
                    }
                };
            }
        },FluxSink.OverflowStrategy.LATEST).subscribe(System.out::println);*/

        /**
         * push
         */
        Flux.create(new Consumer<FluxSink<String>>() {
            @Override
            public void accept(FluxSink<String> fluxSink) {
                for (int i = 0; i < 10; i++) {
                    fluxSink.next("index is : " + i);
                    if (i == 9) {
                        fluxSink.complete();
                    }
                    fluxSink.complete();
                }

            }
        },FluxSink.OverflowStrategy.LATEST).subscribe(System.out::println);


        /**
         * push/pull
         */
        Flux.create(new Consumer<FluxSink<String>>() {
            @Override
            public void accept(FluxSink<String> fluxSink) {

            }
        },FluxSink.OverflowStrategy.LATEST).subscribe(System.out::println);

    }
}
