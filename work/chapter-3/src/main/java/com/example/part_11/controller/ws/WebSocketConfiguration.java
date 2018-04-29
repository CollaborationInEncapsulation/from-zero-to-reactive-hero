package com.example.part_11.controller.ws;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerAdapter;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@Configuration
public class WebSocketConfiguration {

	@Bean
	public HandlerAdapter webSocketHandlerAdapter() {
		return new WebSocketHandlerAdapter();
	}

	@Bean
	public HandlerMapping webSocketHandler(WSHandler channel) {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();

		mapping.setUrlMap(Collections.singletonMap("/stream", channel));
		mapping.setOrder(0);

		return mapping;
	}
}