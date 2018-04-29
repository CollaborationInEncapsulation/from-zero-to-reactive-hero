package com.example.part_10.repository;

import com.example.part_10.domain.Trade;
import reactor.core.publisher.Flux;

public interface TradeRepository {

	Flux<Trade> saveAll(Flux<Trade> input);
}
