package com.example.part_10.service;

import com.example.part_10.dto.MessageDTO;
import reactor.core.publisher.Flux;

public interface TradeService {

	Flux<MessageDTO<MessageDTO.Trade>> tradesStream();

}
