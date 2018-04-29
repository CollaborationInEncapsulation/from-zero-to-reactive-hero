package com.example.part_11.controller.ws;

import com.example.part_11.dto.MessageDTO;
import com.example.part_11.service.PriceService;
import com.example.part_11.service.TradeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

@Service
public class WSHandler implements WebSocketHandler {

	private final WebSocketMessageMapper mapper;
	private final PriceService           priceService;
	private final TradeService           tradeService;

	public WSHandler(WebSocketMessageMapper mapper,
					 PriceService priceService,
					 TradeService tradeService) {
		this.mapper = mapper;
		this.priceService = priceService;
		this.tradeService = tradeService;
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.receive()
		              .map(WebSocketMessage::getPayloadAsText)
		              .transform(this::handleRequestedAveragePriceIntervalValue)
		              .publishOn(Schedulers.parallel())
		              .transform(this::handle)
		              .onBackpressureBuffer()
		              .transform(m -> mapper.encode(m, session.bufferFactory()))
		              .map(db -> new WebSocketMessage(WebSocketMessage.Type.TEXT, db))
		              .as(session::send);
	}

	public Flux<MessageDTO> handle(Flux<Long> input) {
		return Flux.merge(priceService.pricesStream(input), tradeService.tradesStream());
	}

	Flux<Long> handleRequestedAveragePriceIntervalValue(Flux<String> requestedInterval) {
		// TODO Port logic from previous example
		return Flux.never();
	}
}
