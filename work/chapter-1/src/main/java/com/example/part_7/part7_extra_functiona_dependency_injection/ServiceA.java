package com.example.part_7.part7_extra_functiona_dependency_injection;

import javax.inject.Inject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ServiceA implements IServiceA {

	@Inject
	Mono<IServiceB> serviceB;

	@Override
	public Flux<String> sourceA() {
		return Flux.range(0, 5)
				.map(i -> "A_" + i)
				.concatWith(serviceB.flatMapMany(IServiceB::sourceB));
	}
}
