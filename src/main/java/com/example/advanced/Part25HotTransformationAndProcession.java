package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class Part25HotTransformationAndProcession {


    public static Publisher<String> transformToHot(Flux<String> coldSource) {
        // TODO: transform to hot by publishing elements regardless amount of subscribers
        // HINT: Flux#publish() + .autoConnect()
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> replayLast3ElementsInHotFashion(Flux<String> coldSource) {
        // TODO: reply 3 last elements to subscribers
        // HINT: Flux#reply(3) + .autoConnect()
        throw new RuntimeException("Not implemented yet");
    }


    public static Publisher<String> transformToHotUsingProcessor(Flux<String> coldSource) {
        // TODO: use processor to transform cold upstream to hot
        // HINT: 1) Create DirectProcessor
        //       2) subscribe cold source onto created instance of Processor
        //       3) return processor instance
        throw new RuntimeException("Not implemented yet");
    }

    public static Flux<String> processEachSubscriberOnSeparateThread(Flux<String> coldSource) {
        // TODO: use processor to transform cold source to hot and process each subscribe on own, dedicated thread
        // HINT: 1) Create TopicProcessor
        //       2) subscribe cold source onto created instance of Processor
        //       3) return processor instance
        throw new RuntimeException("Not implemented yet");
    }
}
