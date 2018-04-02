package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional;

import reactor.core.publisher.Flux;

public interface PaymentsHistoryReactiveJpaRepository {
	Flux<Payment> findAllByUserId(String userId);
}
