package com.example.advanced;

import com.example.common.StringEmitter;
import com.example.common.TestStringEmitter;
import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static com.example.advanced.Part2Backpressure.backpressureByBatching;
import static com.example.advanced.Part2Backpressure.dropElementsOnBackpressure;
import static com.example.advanced.Part2Backpressure.handleBackpressureWithBuffering;

public class Part2BackpressureTest {

    @Test
    public void handleBackpressureWithBufferingTest() {
        TestStringEmitter stringEmitter = new TestStringEmitter();
        StepVerifier
                .create(
                        handleBackpressureWithBuffering(stringEmitter),
                        0
                )
                .expectSubscription()
                .then(() -> stringEmitter.consumer.accept("A"))
                .then(() -> stringEmitter.consumer.accept("B"))
                .then(() -> stringEmitter.consumer.accept("C"))
                .then(() -> stringEmitter.consumer.accept("D"))
                .then(() -> stringEmitter.consumer.accept("E"))
                .then(() -> stringEmitter.consumer.accept("F"))
                .expectNoEvent(Duration.ofMillis(300))
                .thenRequest(6)
                .expectNext("A", "B", "C", "D", "E", "F")
                .thenCancel()
                .verify();
    }

    @Test
    public void dropElementsOnBackpressureTest() {
        DirectProcessor<String> processor = DirectProcessor.create();
        StepVerifier
                .create(dropElementsOnBackpressure(processor), 0)
                .expectSubscription()
                .then(() -> processor.onNext(""))
                .then(() -> processor.onNext(""))
                .thenRequest(1)
                .then(() -> processor.onNext("0"))
                .expectNext("0")
                .then(() -> processor.onNext("0"))
                .then(() -> processor.onNext("0"))
                .thenRequest(1)
                .then(() -> processor.onNext("10"))
                .expectNext("10")
                .thenRequest(1)
                .then(() -> processor.onNext("20"))
                .expectNext("20")
                .then(() -> processor.onNext("40"))
                .then(() -> processor.onNext("30"))
                .then(processor::onComplete)
                .expectComplete()
                .verify();
    }


    @Test
    public void backpressureByBatchingTest() {
        StepVerifier
                .withVirtualTime(() -> backpressureByBatching(Flux.interval(Duration.ofMillis(1))), 0)
                .expectSubscription()
                .thenRequest(1)
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNextCount(1)
                .thenRequest(1)
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }
}
