package com.example.part_5;

import java.util.function.Function;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.example.part_5.Part5ResilienceResponsive_Optional.provideSupportOfContinuation;
import static com.example.part_5.Part5ResilienceResponsive_Optional.provideSupportOfContinuationWithoutErrorStrategy;

public class Part5ResilienceResponsive_OptionalTest {

	@Test
	public void provideSupportOfContinuationTest() {
		Flux<Integer> range = Flux.range(0, 6)
		                          .map(i -> 10 / i);

		StepVerifier.create(provideSupportOfContinuation(range))
		            .expectSubscription()
		            .expectNextCount(5)
		            .expectComplete()
		            .verifyThenAssertThat()
		            .hasDroppedErrors(1);

		// TODO: fixme
	}

	@Test
	public void provideSupportOfContinuationWithoutErrorStrategyTest() {
		Function<Integer, Integer> mapping = i -> 10 / i;
		Flux<Integer> range = Flux.range(0, 6);

		StepVerifier.create(provideSupportOfContinuationWithoutErrorStrategy(range, mapping))
		            .expectSubscription()
		            .expectNextCount(5)
		            .expectComplete()
		            .verifyThenAssertThat()
		            .hasNotDroppedErrors();
	}
}