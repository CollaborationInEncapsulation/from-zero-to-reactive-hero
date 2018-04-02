package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional;

import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionsPool {

	private static final ConnectionsPool connectionsPool = new ConnectionsPool(20);

	public static ConnectionsPool instance() {
		return connectionsPool;
	}




	private final int           size;
	private final AtomicInteger connectionsCounter = new AtomicInteger();

	public ConnectionsPool(int size) {
		this.size = size;
	}

	public void tryAcquire() {
		connectionsCounter.accumulateAndGet(1, (current, plus) -> {
			if (current >= size) {
				throw new IllegalStateException("No available connections in the pool");
			}
			return current + plus;
		});
	}

	public void release() {
		connectionsCounter.decrementAndGet();
	}

	public int size() {
		return size;
	}
}
