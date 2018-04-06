package com.example.part_10.service;

import com.example.part_10.dto.MessageDTO;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.logging.Logger;

public class TradeService {
    private static final Logger logger = Logger.getLogger("trade-service");

    public Flux<MessageDTO> tradingEvents(
            Flux<Map<String, Object>> input
    ) {
        // TODO: Add implementation to produce trading events
        return Flux.never();
    }

}
