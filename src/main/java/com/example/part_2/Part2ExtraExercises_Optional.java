package com.example.part_2;

import com.example.annotations.Complexity;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import static com.example.annotations.Complexity.Level.EASY;

public class Part2ExtraExercises_Optional {

    @Complexity(EASY)
    public static String firstElementFromSource(Flux<String> source) {
        // TODO: block until first emitted
        // HINT: Flux#blockFirst

        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Publisher<String> mergeSeveralSourcesOrdered(Publisher<String>... sources) {
        // TODO: merge all sources in one stream with order keeping
        /*
         * HINT: Flux#mergeSequential for eager, parallel publisher subscribing and merging
         *       in order in which producers (publishers) have been passed as parameters
         */
        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Publisher<String> concatSeveralSourcesOrdered(Publisher<String>... sources) {
        // TODO: merge all sources in one stream with order keeping but in lazy fashion
        /*
         * HINT: Flux#cocat for lazy, sequential publisher subscribing and merging
         *       in order in which producers (publishers) have been passed as parameters
         *       The main difference is that we will subscribe to the next publisher only when the previous
         *       has been completed.
         */
        throw new RuntimeException("Not implemented");
    }

}
