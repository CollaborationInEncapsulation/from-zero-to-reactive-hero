package com.example.part_7;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.context.Context;

import static com.example.part_7.Part7Context.grabDataFromTheGivenContext;
import static com.example.part_7.Part7Context.provideCorrectContext;
import static org.junit.Assert.*;

public class Part7ContextTest {

	@Test
	public void grabDataFromTheGivenContextTest() {
		StepVerifier.create(
			grabDataFromTheGivenContext("Test")
					.subscriberContext(Context.of("Test", "Test"))
		)
		            .expectSubscription()
		            .expectNext("Test")
		            .verifyComplete();

	}

	@Test
	public void provideCorrectContextTest() {
		StepVerifier.create(
				provideCorrectContext(Mono.just("Test"), "Key", "Value")
		)
		            .expectSubscription()
		            .expectAccessibleContext().contains("Key", "Value").then()
		            .expectNext("Test")
		            .verifyComplete();
	}

	@Test
	public void provideCorrectContext1() {
		Mono<String> a = Mono.subscriberContext().map(context -> context.get("a"));
		Mono<String> b = Mono.subscriberContext().map(context -> context.get("b"));
		Flux<String> flux =
				provideCorrectContext(a, Context.of("a", "a"), b, Context.of("b", "b"));

		StepVerifier.create(flux)
		            .expectSubscription()
		            .expectAccessibleContext().hasKey("a").hasKey("b").then()
		            .expectNext("a")
		            .expectNext("b")
		            .verifyComplete();
	}
}