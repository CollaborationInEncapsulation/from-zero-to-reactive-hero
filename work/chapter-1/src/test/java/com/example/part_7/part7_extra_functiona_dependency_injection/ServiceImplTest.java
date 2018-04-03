package com.example.part_7.part7_extra_functiona_dependency_injection;

import org.junit.Test;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

public class ServiceImplTest {

	@Test
	public void doStaff() {
		Service proxy = Injector.proxy(new ServiceImpl(), Service.class);

		StepVerifier.create(proxy.doStaff())
					.expectSubscription()
					.expectAccessibleContext().hasKey(Injector.HOLDER_KEY).then()
					.expectNextCount(15)
					.verifyComplete();
	}
}