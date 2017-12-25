package com.example.advanced;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Part21CreationTransformationTermination {

    public static Publisher<String> mergeSeveralSources(Publisher<String>... sources) {
        // TODO: merge all sources in one stream
        // HINT: Flux#merge
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> mergeSeveralSourcesOrdered(Publisher<String>... sources) {
        // TODO: merge all sources in one stream with order keeping
        /*
         * HINT: Flux#mergeSequential for eager, parallel publisher subscribing and merging
         *       in order in which producers (publishers) have been passed as parameters
         */
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> concatSeveralSourcesOrdered(Publisher<String>... sources) {
        // TODO: merge all sources in one stream with order keeping but in lazy fashion
        /*
         * HINT: Flux#cocat for lazy, sequential publisher subscribing and merging
         *       in order in which producers (publishers) have been passed as parameters
         *       The main difference is that we will subscribe to the next publisher only when the previous
         *       has been completed.
         */
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> zipSeveralSources(Publisher<String> prefix,
                                                      Publisher<String> word,
                                                      Publisher<String> suffix) {
        // TODO: zip sources and concat elements in string
        // HINT: Flux#zip produce as the result of zipping 3 streams elements of type Tuple3
        // HINT: use Tuple3.getT1 ... getT2 ... getT3
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> combineSeveralSources(Publisher<String> prefix,
                                                          Publisher<String> word,
                                                          Publisher<String> suffix) {
        // TODO: combine latest element emitted from the sources in string
        // HINT: use Tuples::fromArray as a combinator function for
        //       reactor.core.publisher.Flux.combineLatest(
        //                 java.util.function.Function<java.lang.Object[],V>,  <--- Tuples::fromArray
        //                 org.reactivestreams.Publisher<? extends T>...
        //       )
        // HINT: Use Flux#cast to cast Tuple2 --> Tuple3
        // HINT: Use "" + Tuple3.getT1 ... getT2 ... getT3
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> fromFirstEmitted(Publisher<String>... sources) {
        // TODO: return events from the first emitted
        // HINT: Flux.first()
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<GroupedFlux<Character, String>> groupWordsByFirstLatter(Flux<String> words) {
        // TODO: group elements by first latter
        // HINT: flux.groupBy(java.util.function.Function<? super T,? extends K>)
        // HINT: String#chartAt(0) to extract first character
        throw new RuntimeException("Not implemented yet");
    }

    public static Publisher<String> switchBetweenSources(Flux<Source> sources, Flux<String> sourceA, Flux<String> sourceB) {
        // TODO: switch between sources regarding to the event type emitted from the main source
        // HINT: use sources.switchMap to switch between sources
        // HINT: use plain if else statement to check if emitted element from sources Flux is Source.A
        //       or Source.B
        //       In case if event is Source.A - return sourceA Flux
        //       otherwise return sourceB Flux
        throw new RuntimeException("Not implemented yet");
    }

    public static Mono<List<String>> collectAllItemsToList(Flux<String> source) {
        // TODO: collect to list
        // Flux#collectList or Flux#collect(+Collectors.toList)
        throw new RuntimeException("Not implemented yet");
    }

    public static Mono<String> executeLazyTerminationOperationAndSendHello(Flux<String> source) {
        // TODO: wait completion and .THEN() execute another source
        // HINT: source.then( + Flux#just('Hello') )
        throw new RuntimeException("Not implemented yet");
    }

    public static String firstElementFromSource(Flux<String> source) {
        // TODO: block until first emitted
        // HINT: Flux#blockFirst
        throw new RuntimeException("Not implemented yet");
    }

    public static String lastElementFromSource(Flux<String> source) {
        // TODO: block until all emitted
        // HINT: Flux#blockLast
        throw new RuntimeException("Not implemented yet");
    }
}
