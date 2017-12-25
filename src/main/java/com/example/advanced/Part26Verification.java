package com.example.advanced;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

public class Part26Verification {

    @Test
    public void verifyThen10ElementsEmitted() {
        Flux<Integer> toVerify = Flux.fromStream(new Random().ints().boxed())
                .take(15)
                .skip(5);
        //TODO: use StepVerifier to perform testing
        // HINT: use StepVerifier.create(..)
        // HINT: use .expectSubscription as the first verification step
        // HINT: use .expectNextCount to verify that publisher has emitted 10 elements
        // HINT: use .expectComplete() + .verify() or .verifyComplete()

        throw new RuntimeException("Not implemented");
    }

    @Test
    public void verifyEmissionWithVirtualTimeScheduler() {
        Supplier<Flux<Long>> toVerify = () -> {
            return Flux.interval(Duration.ofDays(1))
                    .take(15)
                    .skip(5);
        };

        //TODO: use StepVerifier to perform testing
        // HINT: use StepVerifier.withVirtualTime(..)
        // HINT: use .expectSubscription as the first verification step
        // HINT: use .thenAwait(Duration.ofDays(15)) to advance time by specified duration
        // HINT: use .expectNextCount to verify that publisher has emitted 10 elements
        // HINT: use .expectComplete() + .verify() or .verifyComplete()
        throw new RuntimeException("Not implemented");
    }
}
