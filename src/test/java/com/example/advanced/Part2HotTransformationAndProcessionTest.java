package com.example.advanced;

import org.junit.Assert;
import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.*;
import reactor.core.scheduler.Scheduler;
import reactor.test.StepVerifier;

import static com.example.advanced.Part2HotTransformationAndProcession.*;

public class Part2HotTransformationAndProcessionTest {

    @Test
    public void transformToHotTest() {
        UnicastProcessor<String> source = UnicastProcessor.create();
        ReplayProcessor<String> consumer1 = ReplayProcessor.create(10);
        ReplayProcessor<String> consumer2 = ReplayProcessor.create(10);

        Publisher<String> publisher = transformToHot(source);

        publisher.subscribe(consumer1);

        source.onNext("A");
        source.onNext("B");
        source.onNext("C");

        publisher.subscribe(consumer2);

        source.onNext("D");
        source.onNext("E");
        source.onNext("F");

        source.onComplete();

        StepVerifier.create(consumer1)
                .expectSubscription()
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

        StepVerifier.create(consumer2)
                .expectSubscription()
                .expectNext("D", "E", "F")
                .verifyComplete();
    }

    @Test
    public void replayLast3ElementsInHotFashionTest() {
        UnicastProcessor<String> source = UnicastProcessor.create();
        ReplayProcessor<String> consumer1 = ReplayProcessor.create(10);
        ReplayProcessor<String> consumer2 = ReplayProcessor.create(10);

        Publisher<String> publisher = replayLast3ElementsInHotFashion(source);


        source.onNext("A");
        source.onNext("B");
        source.onNext("C");

        publisher.subscribe(consumer1);

        source.onNext("D");
        source.onNext("E");
        source.onNext("F");

        publisher.subscribe(consumer2);

        source.onNext("G");

        source.onComplete();

        StepVerifier.create(consumer1)
                .expectSubscription()
                .expectNext("A", "B", "C", "D", "E", "F", "G")
                .verifyComplete();

        StepVerifier.create(consumer2)
                .expectSubscription()
                .expectNext("D", "E", "F", "G")
                .verifyComplete();
    }

    @Test
    public void transformToHotUsingProcessorTest() {
        UnicastProcessor<String> source = UnicastProcessor.create();
        ReplayProcessor<String> consumer1 = ReplayProcessor.create(10);
        ReplayProcessor<String> consumer2 = ReplayProcessor.create(10);

        Publisher<String> publisher = transformToHotUsingProcessor(source);

        publisher.subscribe(consumer1);

        source.onNext("A");
        source.onNext("B");
        source.onNext("C");

        publisher.subscribe(consumer2);

        source.onNext("D");
        source.onNext("E");
        source.onNext("F");

        source.onComplete();

        StepVerifier.create(consumer1)
                .expectSubscription()
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

        StepVerifier.create(consumer2)
                .expectSubscription()
                .expectNext("D", "E", "F")
                .verifyComplete();
    }

    @Test
    public void processEachSubscriberOnSeparateThreadTest() {
        UnicastProcessor<String> source = UnicastProcessor.create();
        ReplayProcessor<String> consumer1 = ReplayProcessor.create(10);
        ReplayProcessor<String> consumer2 = ReplayProcessor.create(10);
        Thread[] forConsumers = new Thread[2];

        Flux<String> publisher = processEachSubscriberOnSeparateThread(source);

        publisher
                .doOnComplete(() -> forConsumers[0] = Thread.currentThread())
                .subscribe(consumer1);

        source.onNext("A");
        source.onNext("B");
        source.onNext("C");

        publisher
                .doOnComplete(() -> forConsumers[1] = Thread.currentThread())
                .subscribe(consumer2);


        source.onNext("D");
        source.onNext("E");
        source.onNext("F");

        source.onComplete();


        StepVerifier.create(consumer1)
                .expectSubscription()
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

        StepVerifier.create(consumer2)
                .expectSubscription()
                .expectNext("D", "E", "F")
                .verifyComplete();

        Assert.assertTrue(
                "Expected execution on different Threads",
                !forConsumers[0].equals(forConsumers[1])
        );
    }
}
