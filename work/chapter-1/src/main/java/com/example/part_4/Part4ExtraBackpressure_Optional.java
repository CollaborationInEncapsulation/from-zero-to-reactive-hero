package com.example.part_4;

import java.util.concurrent.CountDownLatch;

import com.example.annotations.Complexity;
import com.example.annotations.Optional;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import static com.example.annotations.Complexity.Level.MEDIUM;

public class Part4ExtraBackpressure_Optional {

	@Optional
	@Complexity(MEDIUM)
	public static void dynamicDemand(Flux<String> source, CountDownLatch countDownOnComplete) {
		// TODO: provide own implementation of Subscriber. Manage request count manually.
		// TODO: two times increase request size each time when previous demand has been satisfied

		// HINT: Consider usage of reactor.core.publisher.BaseSubscriber
		// HINT: count down onComplete

		throw new RuntimeException("Not implemented");
	}
}
