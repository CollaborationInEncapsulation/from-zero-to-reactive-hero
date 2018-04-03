package com.example.part_9;

import com.example.annotations.Complexity;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.example.annotations.Complexity.Level.MEDIUM;

public class Part9Debugging {

	@Test
	@Complexity(MEDIUM)
	public void checkMath() {
		StepVerifier.create(Flux.range(0, 1234567)
		                        .transform(ReactiveMath::doCalculation)
								.take(1))
		            .expectSubscription()
		            .expectNextCount(1)
		            .verifyComplete();

	}
}
