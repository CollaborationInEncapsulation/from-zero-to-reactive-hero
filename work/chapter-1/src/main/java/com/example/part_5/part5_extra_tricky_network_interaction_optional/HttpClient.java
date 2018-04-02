package com.example.part_5.part5_extra_tricky_network_interaction_optional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HttpClient {

	Mono<Void> send(Flux<OrderedByteBuffer> value);
}
