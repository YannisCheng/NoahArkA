package com.cwj.scriptlib.reactor;


import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * com.cwj.scriptlib.reactor
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-08-10 09:50
 */
public class ReactorMainTest {


    public static void main(String[] args) {
        origin();
    }

    /**
     * Subscriber最多有4个参数：
     * subscribe(Consumer<? super T> consumer,
     * Consumer<? super Throwable> errorConsumer,
     * Runnable completeConsumer,
     * Consumer<? super Subscription> subscriptionConsumer);
     */
    public static void origin() {
        Flux<String> just = Flux.just("foo", "bar", "foobar");
        List<String> list = Arrays.asList("foo", "bar", "foobar");
        Flux<String> iterable = Flux.fromIterable(list);

        // 最多发射3个数据，到第四个时主动报异常
        Flux<String> objectFlux = Flux.range(1, 4).map(integer -> {
            if (integer <= 3) {
                return integer + "";
            } else {
                throw new RuntimeException("Got 4");
            }

        });

        // int转换为string
        Flux<String> objectFlux2 = Flux.range(1, 4).map(integer -> integer + "");

        //Disposable subscribe1 = just.subscribe(System.out::println);
        //if (!subscribe1.isDisposed()) {
        //    subscribe1.dispose();
        //    System.out.println("subscribe1.dispose()");
        //}
        //iterable.subscribe(System.out::println);
        // Lambda表达式,有异常不打印done
        //objectFlux.subscribe(System.out::println, System.out::println,()->System.out.println("done"));
        // 没有异常，打印done
        //objectFlux2.subscribe(System.out::println, System.out::println, () -> System.out.println("done"), new Consumer<Subscription>() {
        //    @Override
        //    public void accept(Subscription subscription) {
        //        subscription.request(10);
        //    }
        //});

        //objectFlux.subscribeWith(new Subscriber<String>() {
        //    @Override
        //    public void onSubscribe(Subscription s) {
        //        s.request(1);
        //    }
        //
        //    @Override
        //    public void onNext(String s) {
        //        System.out.println("onNext:" + s);
        //
        //    }
        //
        //    @Override
        //    public void onError(Throwable t) {
        //        System.out.println("onError:" + t.toString());
        //
        //    }
        //
        //    @Override
        //    public void onComplete() {
        //        System.out.println("onComplete");
        //    }
        //});

        // objectFlux.subscribe(new SampleSubscriber());

        // Mono与Mono联合为Flux
        // Mono.just("a").concatWith(Mono.just("b")).subscribe(System.out::println);


    }



}
