package com.example.part_10.service.external;

import com.example.part_10.service.CryptoService;
import com.example.part_10.service.external.utils.PriceMessageUnpacker;
import com.example.part_10.service.external.utils.TradeMessageUnpacker;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Map;

//TODO turn to multi-subscriber with processor or another similar operator
//TODO add small history for each subscriber
//TODO add resilience

public class CryptoCompareService implements CryptoService {
    public static final int CACHE_SIZE = 3;

    private final Flux<Map<String, Object>> reactiveCryptoListener;

    public CryptoCompareService() {
        reactiveCryptoListener = CryptoCompareClient
                .connect(
                        Flux.just("5~CCCAGG~BTC~USD", "0~Coinbase~BTC~USD", "0~Cexio~BTC~USD"),
                        Arrays.asList(new PriceMessageUnpacker(), new TradeMessageUnpacker())
                )
                .transform(CryptoCompareService::provideResilience)
                .transform(CryptoCompareService::provideCaching);
    }

    public Flux<Map<String, Object>> eventsStream() {
        return reactiveCryptoListener;
    }

    // TODO: implement resilience such as retry with delay
    public static <T> Flux<T> provideResilience(Flux<T> input) {
        return input;
    }


    // TODO: implement caching of 3 last elements & multi subscribers support
    public static <T> Flux<T> provideCaching(Flux<T> input) {
        return input;
    }
}
