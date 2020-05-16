package com.example.part_3;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.Callable;

import static com.example.part_3.Part3MultithreadingParallelization.*;

public class Part3MultithreadingParallelizationTest {

    @Test
    public void publishOnParallelThreadSchedulerTest() {
        AtomicReference<Thread> subscribeOnThread = new AtomicReference<>();
        AtomicReference<Thread> publishOnThread = new AtomicReference<>();
        StepVerifier
            .create(publishOnParallelThreadScheduler(Flux.defer(() -> {
                subscribeOnThread.set(Thread.currentThread());
                return Flux.just("Hello");
            })).map(value -> {
                publishOnThread.set(Thread.currentThread());
                return value;
            }))
            .expectSubscription()
            .expectNext("Hello")
            .verifyComplete();

        Assert.assertEquals(
            "Expected subscribeOn in the same Thread",
            subscribeOnThread.get(), Thread.currentThread()
        );
        Assert.assertNotEquals(
            "Expected publishOn different Threads",
            publishOnThread.get(), Thread.currentThread()
        );
    }

    @Test
    public void subscribeOnSingleThreadSchedulerTest() {
        Thread[] threads = new Thread[1];
        StepVerifier
                .create(subscribeOnSingleThreadScheduler(() -> {
                    System.out.println("Threads:" + Thread.currentThread().getName());
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

    @Test
    public void paralellizeLongRunningWorkOnUnboundedAmountOfThreadTest() {
        Thread[] threads = new Thread[3];
        StepVerifier
                .create(paralellizeLongRunningWorkOnUnboundedAmountOfThread(Flux.<Callable<String>>just(
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
                ).repeat(20)))
                .expectSubscription()
                .expectNext("Hello", "Hello", "Hello")
                .expectNextCount(60)
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
}
