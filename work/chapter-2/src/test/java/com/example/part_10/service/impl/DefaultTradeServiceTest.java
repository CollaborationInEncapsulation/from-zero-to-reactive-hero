package com.example.part_10.service.impl;

import com.example.part_10.dto.MessageDTO;
import com.example.part_10.repository.TradeRepository;
import com.example.part_10.service.CryptoService;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static com.example.part_10.service.utils.MessageMapper.CURRENCY_KEY;
import static com.example.part_10.service.utils.MessageMapper.FLAGS_KEY;
import static com.example.part_10.service.utils.MessageMapper.MARKET_KEY;
import static com.example.part_10.service.utils.MessageMapper.PRICE_KEY;
import static com.example.part_10.service.utils.MessageMapper.QUANTITY_KEY;
import static com.example.part_10.service.utils.MessageMapper.TIMESTAMP_KEY;
import static com.example.part_10.service.utils.MessageMapper.TYPE_KEY;

public class DefaultTradeServiceTest {

    private final CryptoService cryptoService = Mockito.mock(CryptoService.class);
    private final TradeRepository tradeRepository = Mockito.mock(TradeRepository.class);

    @Before
    public void setUp() {
        Mockito.when(cryptoService.eventsStream()).thenReturn(Flux.empty());
        Mockito.when(tradeRepository.saveAll(Mockito.any())).thenReturn(Flux.empty());
    }

    @Test
    public void verifyTradeInfoMappingToDTO() {
        StepVerifier.create(
                new DefaultTradeService(cryptoService, tradeRepository).filterTradingEvents(
                        Flux.just(
                                map().put("Invalid", "A").build(),
                                map().put(TYPE_KEY, "1").build(),
                                map().put(TYPE_KEY, "0")
                                        .put(TIMESTAMP_KEY, 1F)
                                        .put(PRICE_KEY, 0.1F)
                                        .put(FLAGS_KEY, "1")
                                        .put(QUANTITY_KEY, 1.2F)
                                        .put(CURRENCY_KEY, "USD")
                                        .put(MARKET_KEY, "External").build()
                        )
                )
                                                            .take(Duration.ofSeconds(1))
        )
                .expectNext(MessageDTO.trade(1000, 0.1F, 1.2F, "USD", "External"))
                .verifyComplete();
    }

    private ImmutableMap.Builder<String, Object> map() {
        return ImmutableMap.builder();
    }
}