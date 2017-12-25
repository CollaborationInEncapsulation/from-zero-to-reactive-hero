package com.example.advanced;

import com.example.common.StringEmitter;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.List;

public class Part24Backpressure {

    public static Publisher<String> handleBackpressureWithBuffering(StringEmitter stringEmitter) {
        // TODO: adapt non-Reactor api and apply backpressure strategy
        // HINT: Flux.create or Flux.push
        throw new RuntimeException("Not implemented yet");
    }

    public static Flux<String> dropElementsOnBackpressure(Flux<String> upstream){
        // TODO: apply backpressure to drop elements on downstream overwhelming
        // HINT: Flux#onBackpressureDrop
        throw new RuntimeException("Not implemented yet");
    }

    public static Flux<List<Long>> backpressureByBatching(Flux<Long> upstream){
        // TODO: decrease emission rate by buffering elements during the second
        // HINT: Flux#window(Duration) + .flatMap( .collectList ) or MORE simply Flux#buffer(Duration)
        throw new RuntimeException("Not implemented yet");
    }
}
