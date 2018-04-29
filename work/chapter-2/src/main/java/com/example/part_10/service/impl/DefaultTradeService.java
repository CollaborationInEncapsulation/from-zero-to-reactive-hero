package com.example.part_10.service.impl;

import com.example.part_10.domain.Trade;
import com.example.part_10.dto.MessageDTO;
import com.example.part_10.repository.TradeRepository;
import com.example.part_10.service.CryptoService;
import com.example.part_10.service.TradeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.logging.Logger;

public class DefaultTradeService implements TradeService {

	private static final Logger logger = Logger.getLogger("trade-service");

	private final Flux<MessageDTO<MessageDTO.Trade>> sharedStream;

	public DefaultTradeService(CryptoService service, TradeRepository repository) {
		sharedStream = service.eventsStream()
		                      .transform(this::filterTradingEvents)
		                      .transform(trades -> Flux.merge(
	                              trades,
			                      trades.transform(this::mapToDomainTrade)
			                            .transform(repository::saveAll)
			                            .then(Mono.empty())
		                      ));
	}

	@Override
	public Flux<MessageDTO<MessageDTO.Trade>> tradesStream() {
		return sharedStream;
	}

	Flux<MessageDTO<MessageDTO.Trade>> filterTradingEvents(Flux<Map<String, Object>> input) {
		// TODO: Add implementation to produce trading events
		return Flux.never();
	}

	Flux<Trade> mapToDomainTrade(Flux<MessageDTO<MessageDTO.Trade>> input) {
		// TODO: Add implementation to mapping to com.example.part_10.domain.Trade
		return Flux.never();
	}

}
