package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

public class Part2MultithreadingParallelization {

    public static Publisher<String> publishOnParallelThreadScheduler(Flux<String> source) {
        // TODO: publish elements on different parallel thread scheduler
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> subscribeOnSingleThreadScheduler(Callable<String> blockingCall) {
        // TODO: execute call on different thread
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> paralellizeLongRunningWorkOnUnboundedAmountOfThread(Flux<Callable<String>> streamOfLongRunningSources) {
        // TODO: execute each element on separate independent thread
        throw new RuntimeException("Not implemented yet");
    }

    public static ParallelFlux<String> paralellizeWorkOnDifferentThreads(Flux<String> source) {
        // TODO: switch source to parallel mode
        throw new RuntimeException("Not implemented yet");
    }
}
