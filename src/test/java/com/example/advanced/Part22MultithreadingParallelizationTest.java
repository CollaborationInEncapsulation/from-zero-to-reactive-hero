package com.example.advanced;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static com.example.advanced.Part22MultithreadingParallelization.*;

public class Part22MultithreadingParallelizationTest {

    @Test
    public void publishOnParallelThreadSchedulerTest() {
        Thread[] threads = new Thread[2];
        StepVerifier
                .create(publishOnParallelThreadScheduler(Flux.defer(() -> {
                    threads[0] = Thread.currentThread();
                    return Flux.just("Hello");
                })))
                .expectSubscription()
                .expectNext("Hello")
                .verifyComplete();

        Assert.assertTrue(
                "Expected execution on different Threads",
                !threads[0].equals(threads[1])
        );
    }

    @Test
    public void subscribeOnSingleThreadSchedulerTest() {
        Thread[] threads = new Thread[1];
        StepVerifier
                .create(subscribeOnSingleThreadScheduler(() -> {
                    threads[0] = Thread.currentThread();
                    return "Hello";
                }))
                .expectSubscription()
                .expectNext("Hello")
                .verifyComplete();

        Assert.assertTrue(
                "Expected execution on different Threads",
                !threads[0].equals(Thread.currentThread())
        );
    }

    @Test
    public void paralellizeLongRunningWorkOnUnboundedAmountOfThreadTest() {
        Thread[] threads = new Thread[3];
        StepVerifier
                .create(paralellizeLongRunningWorkOnUnboundedAmountOfThread(Flux.just(
                        () -> {
                            threads[0] = Thread.currentThread();
                            Thread.sleep(300);
                            return "Hello";
                        },
                        () -> {
                            threads[1] = Thread.currentThread();
                            Thread.sleep(300);
                            return "Hello";
                        },
                        () -> {
                            threads[2] = Thread.currentThread();
                            Thread.sleep(300);
                            return "Hello";
                        }
                )))
                .expectSubscription()
                .expectNext("Hello", "Hello", "Hello")
                .expectComplete()
                .verify(Duration.ofMillis(600));

        Assert.assertTrue(
                "Expected execution on different Threads",
                !threads[0].equals(threads[1])
        );
        Assert.assertTrue(
                "Expected execution on different Threads",
                !threads[1].equals(threads[2])
        );
        Assert.assertTrue(
                "Expected execution on different Threads",
                !threads[0].equals(threads[2])
        );
    }

    @Test
    public void paralellizeWorkOnDifferentThreadsTest() {
        StepVerifier
                .create(paralellizeWorkOnDifferentThreads(
                        Flux.just("Hello", "Hello", "Hello")
                ))
                .expectSubscription()
                .expectNext("Hello", "Hello", "Hello")
                .expectComplete()
                .verify();
    }
}
