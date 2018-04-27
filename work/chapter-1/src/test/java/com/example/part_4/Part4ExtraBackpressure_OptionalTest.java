package com.example.part_4;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.common.TestStringEventPublisher;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import static com.example.part_4.Part4ExtraBackpressure_Optional.dynamicDemand;
import static com.example.part_4.Part4ExtraBackpressure_Optional.handleBackpressureWithBuffering;
import static org.junit.Assert.*;

public class Part4ExtraBackpressure_OptionalTest {

	@Test
	public void handleBackpressureWithBufferingTest() {
		TestStringEventPublisher stringEmitter = new TestStringEventPublisher();
		StepVerifier
				.create(
						handleBackpressureWithBuffering(stringEmitter),
						0
				)
				.expectSubscription()
				.then(() -> stringEmitter.consumer.accept("A"))
				.then(() -> stringEmitter.consumer.accept("B"))
				.then(() -> stringEmitter.consumer.accept("C"))
				.then(() -> stringEmitter.consumer.accept("D"))
				.then(() -> stringEmitter.consumer.accept("E"))
				.then(() -> stringEmitter.consumer.accept("F"))
				.expectNoEvent(Duration.ofMillis(300))
				.thenRequest(6)
				.expectNext("A", "B", "C", "D", "E", "F")
				.thenCancel()
				.verify();
	}

	@Test
	public void dynamicDemandTest() throws InterruptedException {
		int size = 100000;
		long requests = (long) Math.ceil((Math.log(size) / Math.log(2) + 1e-10));
		CountDownLatch latch = new CountDownLatch(1);
		AtomicInteger iterations = new AtomicInteger();

		dynamicDemand(
				Flux.range(0, size)
				    .map(String::valueOf)
				    .publishOn(Schedulers.single())
				    .doOnRequest(r -> iterations.incrementAndGet()),
				latch
		);

		latch.await(5, TimeUnit.SECONDS);

		assertEquals(requests, iterations.get());
	}
}