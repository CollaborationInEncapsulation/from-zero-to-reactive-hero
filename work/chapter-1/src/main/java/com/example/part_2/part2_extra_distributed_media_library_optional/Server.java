package com.example.part_2.part2_extra_distributed_media_library_optional;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import reactor.core.publisher.Mono;

public class Server {

	private final String address;

	public Server(String address) {
		this.address = address;
	}

	public Mono<Video> searchOne(String name) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		boolean doubled = random.nextBoolean();
		return Mono.just(new Video(name, address, "Some fake description"))
		           .delaySubscription(Duration.ofMillis(random.nextLong(200, doubled ? 6000 : 3000)));
	}
}
