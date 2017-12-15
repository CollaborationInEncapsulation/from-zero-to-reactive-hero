package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Part2CreationTransformationTermination {

    public static Publisher<String> mergeSeveralSources(Publisher<String>... sources) {
        // TODO: merge all sources in one stream
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> mergeSeveralSourcesOrdered(Publisher<String>... sources) {
        // TODO: merge all sources in one stream with order keeping
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> concatSeveralSourcesOrdered(Publisher<String>... sources) {
        // TODO: merge all sources in one stream with order keeping but in lazy fashion
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> zipSeveralSources(Publisher<String> prefix,
                                                      Publisher<String> word,
                                                      Publisher<String> suffix) {
        // TODO: zip sources and concat elements in string
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> combineSeveralSources(Publisher<String> prefix,
                                                          Publisher<String> word,
                                                          Publisher<String> suffix) {
        // TODO: combine latest element emitted from the sources in string
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> fromFirstEmitted(Publisher<String>... sources) {
        // TODO: return events from the first emitted
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<GroupedFlux<Character, String>> groupWordsByFirstLatter(Flux<String> words) {
        // TODO: group elements by first latter
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> switchBetweenSources(Flux<Source> sources, Flux<String> sourceA, Flux<String> sourceB) {
        // TODO: switch between sources regarding to the event type emitted from the main source
        throw new RuntimeException("Not implemented yet");
    }

    public static Mono<List<String>> collectAllItemsToList(Flux<String> source) {
        // TODO: collect to list
        throw new RuntimeException("Not implemented yet");
    }

    public static Mono<String> executeLazyTerminationOperationAndSendHello(Flux<String> source) {
        // TODO: wait completion and .THEN() execute another source
        throw new RuntimeException("Not implemented yet");
    }

    public static String firstElementFromSource(Flux<String> source) {
        // TODO: block until first emitted
        throw new RuntimeException("Not implemented yet");
    }

    public static String lastElementFromSource(Flux<String> source) {
        // TODO: block until all emitted
        throw new RuntimeException("Not implemented yet");
    }
}
