package com.example.part_5.part5_extra_tricky_network_interaction_optional;

import java.time.Duration;

import com.example.annotations.Complexity;
import com.example.annotations.Optional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.annotations.Complexity.Level.HARD;

public class DataUploaderService {

	private final HttpClient client;

	public DataUploaderService(HttpClient client) {
		this.client = client;
	}

	@Optional
	@Complexity(HARD)
	public Mono<Void> upload(Flux<OrderedByteBuffer> input) {
		// TODO: send data to a server using the given client
		// TODO: MAX amount of sent buffers MUST be less or equals to 50 per request
		// TODO: frequency of client#send invocation MUST be not often than once per 500 Milliseconds
		// TODO: delivered results MUST be ordered
		// TODO: in case if send operation take more than 1 second it MUST be considered as hanged and be restarted

		// HINT: consider usage of .windowTimeout, onBackpressureBuffer, concatMap, timeout, retryWhen or retryBackoff



		throw new RuntimeException("Not implemented");
	}
}
