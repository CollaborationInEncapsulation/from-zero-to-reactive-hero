package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional;

import reactor.core.publisher.Flux;

public class PaymentService {
	private final PaymentsHistoryReactiveJpaRepository repository;

	public PaymentService() {
		this.repository = null; // REPLACE
	}

	public Flux<Payment> findPayments(Flux<String> userIds) {
		return userIds.flatMap(repository::findAllByUserId);
	}
}
