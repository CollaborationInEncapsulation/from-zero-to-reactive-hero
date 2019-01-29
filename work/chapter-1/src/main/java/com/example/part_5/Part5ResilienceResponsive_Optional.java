package com.example.part_5;

import java.util.function.Function;

import com.example.annotations.Complexity;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import static com.example.annotations.Complexity.Level.HARD;

public class Part5ResilienceResponsive_Optional {

	@Complexity(HARD)
	public static Publisher<Integer> provideSupportOfContinuation(Flux<Integer> values) {
		// TODO: Enable continuation strategy

		return values;
	}

	@Complexity(HARD)
	public static Publisher<Integer> provideSupportOfContinuationWithoutErrorStrategy(Flux<Integer> values, Function<Integer, Integer> mapping) {
		// TODO: handle errors using flatting

		return values.map(mapping);
	}
}
