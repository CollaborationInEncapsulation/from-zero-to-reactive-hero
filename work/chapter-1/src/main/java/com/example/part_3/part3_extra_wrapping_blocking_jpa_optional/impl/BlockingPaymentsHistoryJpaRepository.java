package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.impl;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.ConnectionsPool;
import com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.Payment;
import com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.PaymentsHistoryJpaRepository;
import reactor.core.publisher.Flux;

public class BlockingPaymentsHistoryJpaRepository
		implements PaymentsHistoryJpaRepository {

	@Override
	public List<Payment> findAllByUserId(String userId) {
		try {
			ConnectionsPool.instance().tryAcquire();
			ThreadLocalRandom random = ThreadLocalRandom.current();

			return Flux.fromStream(Stream.generate(Payment::new))
			           .take(random.nextLong(50))
			           .delayElements(Duration.ofMillis(random.nextLong(5, 50)))
			           .collectList()
			           .block();
		}
		finally {
			ConnectionsPool.instance().release();
		}
	}
}
