package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class PaymentServiceTest {

	@Test
	public void findPayments() {
		PaymentService service = new PaymentService();

		StepVerifier.create(service.findPayments(Flux.range(1, 100)
		                                             .map(String::valueOf))
		                           .then())
		            .expectSubscription()
		            .verifyComplete();
	}
}