package com.example.part_3;

import com.example.annotations.Complexity;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

import static com.example.annotations.Complexity.Level.EASY;
import static com.example.annotations.Complexity.Level.HARD;

public class Part3MultithreadingParallelization {

    @Complexity(EASY)
    public static Publisher<String> publishOnParallelThreadScheduler(Flux<String> source) {
        // TODO: publish elements on different parallel thread scheduler
        // HINT: Flux.publishOn(reactor.core.scheduler.Scheduler)
        // HINT: use reactor.core.scheduler.Schedulers.parallel() for thread-pool with several workers

        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Publisher<String> subscribeOnSingleThreadScheduler(Callable<String> blockingCall) {
        // TODO: execute call on different thread
        // HINT: Mono.fromCallable
        // HINT: Mono#sibscribeOn( + reactor.core.scheduler.Schedulers.single() )

        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static ParallelFlux<String> paralellizeWorkOnDifferentThreads(Flux<String> source) {
        // TODO: switch source to parallel mode
        // HINT: Flux#parallel() + .runOn( Schedulers... )

        throw new RuntimeException("Not implemented");
    }

    @Complexity(HARD)
    public static Publisher<String> paralellizeLongRunningWorkOnUnboundedAmountOfThread(Flux<Callable<String>> streamOfLongRunningSources) {
        // TODO: execute each element on separate independent threads

        throw new RuntimeException("Not implemented");
    }
}
