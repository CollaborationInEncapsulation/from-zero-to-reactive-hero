package com.example.part_10.controller;

import com.example.part_10.dto.MessageDTO;
import com.example.part_10.service.PriceService;
import com.example.part_10.service.TradeService;
import reactor.core.publisher.Flux;

public class WSHandler {

	private final PriceService priceService;
	private final TradeService tradeService;

	public WSHandler(PriceService priceService, TradeService tradeService) {
		this.priceService = priceService;
		this.tradeService = tradeService;
	}

	public Flux<MessageDTO> handle(Flux<Long> input) {
		return Flux.merge(
				priceService.pricesStream(input),
				tradeService.tradesStream()
		);
	}
}
