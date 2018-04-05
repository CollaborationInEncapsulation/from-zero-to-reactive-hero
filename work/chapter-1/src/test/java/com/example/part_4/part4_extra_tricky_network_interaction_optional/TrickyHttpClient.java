package com.example.part_4.part4_extra_tricky_network_interaction_optional;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

public class TrickyHttpClient implements HttpClient {
	private final List<OrderedByteBuffer> byteBuffers = new CopyOnWriteArrayList<>();
	private final List<List<Tuple2<List<OrderedByteBuffer>, Long>>> records = new CopyOnWriteArrayList<>();

	@Override
	public Mono<Void> send(Flux<OrderedByteBuffer> value) {
		List<Tuple2<List<OrderedByteBuffer>, Long>> record = new CopyOnWriteArrayList<>();
		records.add(record);
		Mono<List<OrderedByteBuffer>> cache = value.collectList()
		                                           .cache();
		return cache
		            .flatMap(l -> {
			           long timeout = ThreadLocalRandom.current()
			                                           .nextLong(25) * l.size();
		               record.add(Tuples.of(l, timeout));
			           if(l.size() > 50) {
				           return Mono.error(IllegalArgumentException::new);
			           } else {
			               return Mono.just(l).delayElement(Duration.ofMillis(timeout),
					               Schedulers.single());
			           }
		            })
		            .doOnNext(byteBuffers::addAll)
		            .then();

	}

	public List<OrderedByteBuffer> getRecordedBuffers() {
		return byteBuffers;
	}
	public List<List<Tuple2<List<OrderedByteBuffer>, Long>>> getRecords() {
		return records;
	}
}
