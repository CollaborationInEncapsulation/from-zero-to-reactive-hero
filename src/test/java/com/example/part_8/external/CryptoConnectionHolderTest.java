package com.example.part_8.external;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.reactivestreams.Publisher;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;
import reactor.core.publisher.UnicastProcessor;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(PowerMockRunner.class)
// HINT use PowerMock to mock static method behaviour and verify in integrity
@PrepareForTest(ReactiveCryptoListener.class)
public class CryptoConnectionHolderTest {

    @Test
    public void verifyThatSupportMultiSubscribers() {
        AtomicInteger subscribtions = new AtomicInteger(0);
        Flux<Object> source = DirectProcessor
                .create()
                .doOnSubscribe(s -> subscribtions.incrementAndGet());

        Flux<Object> cachedFlux = CryptoConnectionHolder.provideCaching(source);

        cachedFlux.subscribe(System.out::println);
        cachedFlux.subscribe(System.out::println);

        Assert.assertEquals(1, subscribtions.get());
    }

    @Test
    public void verifyThatSupportIsolationAndResilience() {
        Flux<String> source = Flux.defer(() -> Flux.just("1", "2", "3")
                .mergeWith(Flux.error(new RuntimeException())));

        StepVerifier.withVirtualTime(() ->
                CryptoConnectionHolder.provideResilience(source)
                .take(6)
        )
                .expectSubscription()
                .thenAwait(Duration.ofDays(10))
                .expectNext("1", "2", "3")
                .expectNext("1", "2", "3")
                .expectComplete()
                .verify();

    }

    @Test
    public void verifyThatSupportReplayMode() {
        UnicastProcessor<String> source = UnicastProcessor.create();
        ReplayProcessor<String> consumer1 = ReplayProcessor.create(10);
        ReplayProcessor<String> consumer2 = ReplayProcessor.create(10);

        Publisher<String> publisher = CryptoConnectionHolder.provideCaching(source);

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
}
