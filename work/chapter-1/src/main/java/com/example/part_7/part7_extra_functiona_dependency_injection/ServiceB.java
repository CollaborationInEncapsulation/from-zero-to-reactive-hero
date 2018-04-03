package com.example.part_7.part7_extra_functiona_dependency_injection;

import reactor.core.publisher.Flux;

public class ServiceB implements IServiceB {

	@Override
	public Flux<String> sourceB() {
		return Flux.range(5, 5)
				.map(i -> "B_" + i);
	}
}
