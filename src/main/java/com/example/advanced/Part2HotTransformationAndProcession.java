package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class Part2HotTransformationAndProcession {


    public static Publisher<String> transformToHot(Flux<String> coldSource) {
        // TODO: transform to hot by publishing elements regardless amount of subscribers
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> replayLast3ElementsInHotFashion(Flux<String> coldSource) {
        // TODO: reply 3 last elements to subscribers
        throw new RuntimeException("Not implemented yet");
    }


    public static Publisher<String> transformToHotUsingProcessor(Flux<String> coldSource) {
        // TODO: use processor to transform cold upstream to hot
        throw new RuntimeException("Not implemented yet");
    }

    public static Flux<String> processEachSubscriberOnSeparateThread(Flux<String> coldSource) {
        throw new RuntimeException("Not implemented yet");
    }
}
