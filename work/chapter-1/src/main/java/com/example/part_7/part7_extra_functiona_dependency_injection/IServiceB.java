package com.example.part_7.part7_extra_functiona_dependency_injection;

import reactor.core.publisher.Flux;

public interface IServiceB {

	Flux<String> sourceB();

	static IServiceB instance() {
		return new ServiceB();
	}
}
