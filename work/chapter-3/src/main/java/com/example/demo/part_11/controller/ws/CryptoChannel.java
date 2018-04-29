package com.example.demo.part_11.controller.ws;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

@Service
public class CryptoChannel implements WebSocketHandler {

	private final WebSocketMessageMapper mapper;

	public CryptoChannel(WebSocketMessageMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.receive()
		              .map(WebSocketMessage::getPayloadAsText)
//		              .transform(this::handleRequestedAveragePriceIntervalValue)
		              .publishOn(Schedulers.parallel())
//		              .transform(WSHandler::handle)
		              .onBackpressureBuffer()
		              .transform(m -> mapper.encode(m, session.bufferFactory()))
		              .map(db -> new WebSocketMessage(WebSocketMessage.Type.TEXT, db))
		              .as(session::send);
	}

}
