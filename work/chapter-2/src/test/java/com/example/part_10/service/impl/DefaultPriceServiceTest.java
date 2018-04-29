package com.example.part_10.service.impl;

import com.example.part_10.dto.MessageDTO;
import com.example.part_10.service.CryptoService;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Predicate;

import static com.example.part_10.service.utils.MessageMapper.CURRENCY_KEY;
import static com.example.part_10.service.utils.MessageMapper.MARKET_KEY;
import static com.example.part_10.service.utils.MessageMapper.PRICE_KEY;
import static com.example.part_10.service.utils.MessageMapper.TYPE_KEY;

public class DefaultPriceServiceTest {
    private final CryptoService cryptoService = Mockito.mock(CryptoService.class);

    @Before
    public void setUp() {
        Mockito.when(cryptoService.eventsStream()).thenReturn(Flux.empty());
    }

    @Test
    public void verifyBuildingCurrentPriceEvents() {
        StepVerifier.create(
                new DefaultPriceService(cryptoService).selectOnlyPriceUpdateEvents(
                        Flux.just(
                                map().put("Invalid", "A").build(),
                                map().put(TYPE_KEY, "1").build(),
                                map().put(TYPE_KEY, "5").build(),
                                map().put(TYPE_KEY, "5")
                                        .put(PRICE_KEY, 0.1F)
                                        .put(CURRENCY_KEY, "USD")
                                        .put(MARKET_KEY, "External").build()
                        )
                )
        )
                .expectNext(
                        map().put(TYPE_KEY, "5")
                                .put(PRICE_KEY, 0.1F)
                                .put(CURRENCY_KEY, "USD")
                                .put(MARKET_KEY, "External").build()
                )
                .expectComplete()
                .verify(Duration.ofSeconds(2));
    }

    // HINT: This is for reference implementation, your implementation may produce different average values
    @Test
    @SuppressWarnings("unchecked")
    public void verifyBuildingAveragePriceEvents() {
        StepVerifier.withVirtualTime(() ->
                new DefaultPriceService(cryptoService).averagePrice(
                        Flux.interval(Duration.ofSeconds(0), Duration.ofSeconds(5))
                                .map(i -> i + 1)
                                .doOnNext(i -> System.out.println("Interval: " + i)),
                        Flux.interval(Duration.ofMillis(500), Duration.ofSeconds(1))
                                .map(p -> p + 100)
                                .map(tick -> MessageDTO.price((float) tick, "U", "M"))
                                .take(20)
                                .doOnNext(p -> System.out.println("Price: " + p.getData()))
                                .replay(1000)
                                .autoConnect()
                        )
                                               .take(10)
                                               .take(Duration.ofHours(1))
                                               .map(MessageDTO::getData)
                                               .doOnNext(a -> System.out.println("AVG: " + a))
        )
                .expectSubscription()
                .thenAwait(Duration.ofDays(1))
                .expectNextMatches(expectedPrice(100.0F))
                .expectNextMatches(expectedPrice(101.0F))
                .expectNextMatches(expectedPrice(102.0F))
                .expectNextMatches(expectedPrice(103.0F))
                .expectNextMatches(expectedPrice(104.0F))
                .expectNextMatches(expectedPrice(103.0F))
                .expectNextMatches(expectedPrice(107.5F))
                .expectNextMatches(expectedPrice(109.5F))
                .expectNextMatches(expectedPrice(106.0F))
                .expectNextMatches(expectedPrice(114.0F))
                .verifyComplete();
    }

    private Predicate<Float> expectedPrice(float v) {
        return m -> (Math.abs(m - v) < 0.0001);
    }

    private ImmutableMap.Builder<String, Object> map() {
        return ImmutableMap.builder();
    }

}