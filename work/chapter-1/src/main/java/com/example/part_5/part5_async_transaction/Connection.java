package com.example.part_5.part5_async_transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Connection<T> {

    Mono<Long> write(Flux<T> data);

    Mono<Void> rollback();

    Mono<Void> close();
}
