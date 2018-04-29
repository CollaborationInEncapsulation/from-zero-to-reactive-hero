package com.example.part_11.service;

import com.example.part_11.dto.MessageDTO;
import reactor.core.publisher.Flux;

public interface PriceService {

	Flux<MessageDTO<Float>> pricesStream(Flux<Long> intervalPreferencesStream);
}
