package com.example.part_5;

import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import static com.example.part_5.Part5ResilienceResponsive.*;

public class Part5ResilienceResponsiveTest {

    @Test
    public void fallbackHelloOnEmptyTest() {
        StepVerifier
                .create(fallbackHelloOnEmpty(Flux.empty()))
                .expectSubscription()
                .expectNext("Hello")
                .verifyComplete();
    }

    @Test
    public void fallbackHelloOnErrorTest() {
        StepVerifier
                .create(fallbackHelloOnError(Flux.error(new RuntimeException())))
                .expectSubscription()
                .expectNext("Hello")
                .verifyComplete();
    }

    @Test
    public void retryOnErrorTest() throws Exception {
        Callable<String> callable = Mockito.mock(Callable.class);
        Mockito.when(callable.call())
               .thenThrow(new RuntimeException())
               .thenReturn("Hello");

        StepVerifier
                .create(retryOnError(Mono.fromCallable(callable)))
                .expectSubscription()
                .expectNext("Hello")
                .expectComplete()
                .verify();
    }

    @Test
    public void timeoutLongOperationWithCompletableFutureTest() {
        StepVerifier
                .withVirtualTime(() -> timeoutLongOperation(CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        return null;
                    }

                    return "Toooooo long";
                })))
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNext("Hello")
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

    @Test
    public void timeoutLongOperationWithCallableTest() {
        StepVerifier
                .create(timeoutLongOperation(() -> {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        return null;
                    }

                    return "Toooooo long";
                }))
                .expectSubscription()
                .expectNext("Hello")
                .expectComplete()
                .verify(Duration.ofSeconds(2));
    }
}
