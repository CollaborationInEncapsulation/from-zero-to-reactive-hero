package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional;

import com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.impl.ReactivePaymentsHistoryJpaRepositoryAdapter;
import reactor.core.publisher.Flux;

/**
 * Description: You have got a task to integrate our Reactive Application with JDBC.
 * The problem with JDBC is that it is blocking one and as we might remember blocking
 * I/O is pure evil. Fortunately, there is a hack here. We may rely on Cached
 * ThreadPool and execute each blocking operation in the separate Thread. It might help
 * us when the number of queries is small. However, when the number of queries become
 * higher than usual, we may get into the troubles. As we might remember, most of the
 * Database has connections-pool. That connections-pool is limited to prevent Database
 * overwhelming. In that case, if our application creates more Threads than available
 * connections in the connection-pool, then the application will be overwhelmed by
 * redundant Thread. Hence additional memory will be used.
 *
 * As great developers, we have to tackle that noisy issue. To avoid that insufficient
 * wasting we have to provide adaptation of {@link com.example.part_3.part3_extra_wrapping_blocking_jpa_optional.impl.BlockingPaymentsHistoryJpaRepository}
 * using {@link ReactivePaymentsHistoryJpaRepositoryAdapter} and custom Scheduler
 * service. In turn, you have to care about connections-pool limitations so your
 * application will not overwhelm it by redundant calls.
 */
public class PaymentService {
	private final PaymentsHistoryReactiveJpaRepository repository;

	public PaymentService() {
		this.repository = null; // REPLACE
	}

	public Flux<Payment> findPayments(Flux<String> userIds) {
		return userIds.flatMap(repository::findAllByUserId);
	}
}
