package com.example.part_11.service;

import com.example.part_11.dto.MessageDTO;
import reactor.core.publisher.Flux;

public interface TradeService {

	Flux<MessageDTO<MessageDTO.Trade>> tradesStream();

}
