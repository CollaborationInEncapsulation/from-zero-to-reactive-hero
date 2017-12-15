package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class Part2ResilienceResponsive {

    public static Publisher<String> retryOnError(Mono<String> failurePublisher) {
        // TODO: retry operation if error
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> timeoutLongOperation(CompletableFuture<String> longRunningCall) {
        // TODO: limit the overall operation execution to one second
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> timeoutLongOperation(Callable<String> longRunningCall) {
        // TODO: limit the overall operation execution to one second
        // HINT: remember that execution should occur on different thread

        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> fallbackHelloOnError(Flux<String> failurePublisher) {
        // TODO: return fallback on error
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> fallbackHelloOnEmpty(Flux<String> failurePublisher) {
        // TODO: return fallback on empty source
        throw new RuntimeException("Not implemented yet");
    }
}
