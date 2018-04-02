package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.impl;

import com.example.annotations.Complexity;
import com.example.annotations.Optional;
import com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.Payment;
import com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.PaymentsHistoryReactiveJpaRepository;
import reactor.core.publisher.Flux;

import static com.example.annotations.Complexity.Level.HARD;

public class ReactivePaymentsHistoryJpaRepositoryAdapter
		implements PaymentsHistoryReactiveJpaRepository {

	@Optional
	@Complexity(HARD)
	public Flux<Payment> findAllByUserId(String userId) {
		// TODO: provide asynchronous wrapping around blocking JPARepository
		// HINT: Consider provide custom singleton thread-pool with limited amount of workers
		//       ThreadCount == ConnectionsPool.size()
		throw new RuntimeException("Not implemented");
	}
}
