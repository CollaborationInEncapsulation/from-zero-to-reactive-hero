package com.example.part_7.part7_extra_functiona_dependency_injection;

import reactor.core.publisher.Flux;

public interface Service {
	Flux<String> doStaff();
}
