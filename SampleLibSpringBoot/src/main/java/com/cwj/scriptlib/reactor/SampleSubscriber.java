package com.cwj.scriptlib.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

/**
 * com.cwj.scriptlib.reactor
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-10 17:22
 */
public class SampleSubscriber extends BaseSubscriber<String> {
    @Override
    protected void hookFinally(SignalType type) {
        super.hookFinally(type);
    }

    @Override
    protected void hookOnCancel() {
        super.hookOnCancel();
    }

    @Override
    protected void hookOnComplete() {
        super.hookOnComplete();
    }

    @Override
    protected void hookOnNext(String value) {
        super.hookOnNext(value);
        System.out.println(value);
        request(1);
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        super.hookOnError(throwable);
        System.out.println(throwable.toString());
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        super.hookOnSubscribe(subscription);
        request(1);
    }
}
