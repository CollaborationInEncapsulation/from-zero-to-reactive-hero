package com.example.part_7.part7_extra_functiona_dependency_injection;

import reactor.core.publisher.Flux;

public interface IServiceA {

	Flux<String> sourceA();

	static IServiceA instance() {
		return new ServiceA();
	}
}
