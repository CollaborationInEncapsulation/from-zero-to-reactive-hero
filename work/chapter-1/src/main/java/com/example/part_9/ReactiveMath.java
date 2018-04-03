package com.example.part_9;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveMath {
	public static Flux<Integer> doCalculation(Flux<Integer> range) {
		return range
				.scan(0, (aLong, aLong2) -> (aLong + aLong2 + 2 * aLong) / aLong2)
				.flatMap( e -> Mono.just(1 / Mono.just(e).filter(p -> 121418 % p == 0).block()));
	}
}
