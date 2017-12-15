package com.example.advanced;

import com.example.common.StringEmitter;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.List;

public class Part2Backpressure {

    public static Publisher<String> handleBackpressureWithBuffering(StringEmitter stringEmitter) {
        // TODO: adapt non-Reactor api and apply backpressure strategy
        throw new RuntimeException("Not implemented yet");
    }

    public static Flux<String> dropElementsOnBackpressure(Flux<String> upstream){
        // TODO: apply backpressure to drop elements on downstream overwhelming
        throw new RuntimeException("Not implemented yet");
    }

    public static Flux<List<Long>> backpressureByBatching(Flux<Long> upstream){
        // TODO: decrease emission rate by buffering elements during the second
        throw new RuntimeException("Not implemented yet");
    }
}
