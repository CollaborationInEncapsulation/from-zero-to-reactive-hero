package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;

import java.util.concurrent.Callable;

public class Part22MultithreadingParallelization {

    public static Publisher<String> publishOnParallelThreadScheduler(Flux<String> source) {
        // TODO: publish elements on different parallel thread scheduler
        // HINT: Flux.publishOn(reactor.core.scheduler.Scheduler)
        // HINT: use reactor.core.scheduler.Schedulers.parallel() for thread-pool with several workers
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> subscribeOnSingleThreadScheduler(Callable<String> blockingCall) {
        // TODO: execute call on different thread
        // HINT: Mono.fromCallable
        // HINT: Mono#sibscribeOn( + reactor.core.scheduler.Schedulers.single() )
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> paralellizeLongRunningWorkOnUnboundedAmountOfThread(Flux<Callable<String>> streamOfLongRunningSources) {
        // TODO: execute each element on separate independent thread
        // HINT: use .flatMap( with Mono.fromCallable + .subscribeOn(Schedulers.elastic()))
        throw new RuntimeException("Not implemented yet");
    }

    public static ParallelFlux<String> paralellizeWorkOnDifferentThreads(Flux<String> source) {
        // TODO: switch source to parallel mode
        // HINT: Flux#parallel() + .runOn( Schedulers... )
        throw new RuntimeException("Not implemented yet");
    }
}
