package com.example.cryptotrading.external;

import reactor.core.publisher.Flux;

import java.util.Map;

public class CryptoConnectionHolder {
    //TODO turn to multi-subscriber with processor or another similar operator
    //TODO add small history for each subscriber
    //TODO add resilience
    private static final Flux<Map<String, Object>> reactiveCryptoListener = ReactiveCryptoListener
            .connect(Flux.just("BTC"));


    public static Flux<Map<String, Object>> get() {
        return reactiveCryptoListener;
    }
}
