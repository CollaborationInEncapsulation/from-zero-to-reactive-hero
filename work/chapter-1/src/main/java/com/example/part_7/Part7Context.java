package com.example.part_7;

import com.example.annotations.Complexity;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import static com.example.annotations.Complexity.Level.EASY;

public class Part7Context {

	@Complexity(EASY)
	public static Mono<String> grabDataFromTheGivenContext(Object key) {
		// TODO: get data from the context

		return Mono.subscriberContext()
				   .map(c -> c.get(key));
	}

	@Complexity(EASY)
	public static Mono<String> provideCorrectContext(Mono<String> source, Object key, Object value) {
		// TODO: provide context for upstream source

		return source.subscriberContext(Context.of(key, value));
	}

	@Complexity(EASY)
	public static Flux<String> provideCorrectContext(
			Publisher<String> sourceA, Context contextA,
			Publisher<String> sourceB, Context contextB) {
		// TODO: edit without significant changes to provide corresponding context for each source
		return Flux.from(sourceA)
		           .mergeWith(sourceB);
	}
}
