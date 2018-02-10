package com.example.part_8.service;

import com.example.part_8.dto.MessageDTO;
import com.example.part_8.service.utils.MessageMapper;
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
