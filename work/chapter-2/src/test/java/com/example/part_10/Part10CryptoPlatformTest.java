package com.example.part_10;

import org.junit.Test;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * HINT prepare mock server for testing
 */
public class Part10CryptoPlatformTest {

    @Test
    public void verifyIncomingMessageValidation() {
        StepVerifier.create(
                Part10CryptoPlatform.handleRequestedAveragePriceIntervalValue(
                        Flux.just("Invalid", "32", "", "-1", "1", "0", "5", "62", "5.6", "12")
                ).take(Duration.ofSeconds(1)))
                .expectNext(32L, 1L, 5L, 12L)
                .verifyComplete();
    }

    @Test
    public void verifyOutgoingStreamBackpressure() {
        DirectProcessor<String> processor = DirectProcessor.create();

        StepVerifier
                .create(
                        Part10CryptoPlatform.handleOutgoingStreamBackpressure(processor),
                        0
                )
                .expectSubscription()
                .then(() -> processor.onNext("A"))
                .then(() -> processor.onNext("B"))
                .then(() -> processor.onNext("C"))
                .then(() -> processor.onNext("D"))
                .then(() -> processor.onNext("E"))
                .then(() -> processor.onNext("F"))
                .expectNoEvent(Duration.ofMillis(300))
                .thenRequest(6)
                .expectNext("A", "B", "C", "D", "E", "F")
                .thenCancel()
                .verify();
    }
}
