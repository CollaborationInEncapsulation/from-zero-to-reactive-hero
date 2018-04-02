package com.example.part_8.service;

import com.example.part_8.dto.MessageDTO;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Predicate;

import static com.example.part_8.service.utils.MessageMapper.CURRENCY_KEY;
import static com.example.part_8.service.utils.MessageMapper.MARKET_KEY;
import static com.example.part_8.service.utils.MessageMapper.PRICE_KEY;
import static com.example.part_8.service.utils.MessageMapper.TYPE_KEY;

public class PriceServiceTest {

    @Test
    public void verifyBuildingCurrentPriceEvents() {
        StepVerifier.create(
                new PriceService().selectOnlyPriceUpdateEvents(
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
                .verifyComplete();
    }

    // HINT: This is for reference implementation, your implementation may produce different average values
    @Test
    @SuppressWarnings("unchecked")
    public void verifyBuildingAveragePriceEvents() {
        StepVerifier.withVirtualTime(() ->
                new PriceService().averagePrice(
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