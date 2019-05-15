package com.example.part_10.service.external;

import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import com.example.part_10.service.external.utils.MessageUnpacker;
import io.socket.client.IO;
import io.socket.client.Socket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

class FakeCryptoCompareClient {

    static final String TYPE_KEY = "TYPE";
    static final String TIMESTAMP_KEY = "TIMESTAMP";
    static final String PRICE_KEY = "PRICE";
    static final String QUANTITY_KEY = "QUANTITY";
    static final String CURRENCY_KEY = "FROMSYMBOL";
    static final String MARKET_KEY = "MARKET";
    static final String FLAGS_KEY = "FLAGS";
    private static final Logger logger = Logger.getLogger("external-trading-service");

    static Flux<Map<String, Object>> connect(Flux<String> input, Collection<MessageUnpacker> unpackers) {
        return Flux.defer(() -> Flux
            .interval(Duration.ofMillis(500))
            .map(__ -> {
                HashMap<String, Object> map = new HashMap<>();
                ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

                if (threadLocalRandom.nextBoolean()) {
                    map.put(TYPE_KEY, "5");
                    map.put(PRICE_KEY, Float.valueOf(threadLocalRandom.nextInt(4650, 4700)));
                    map.put(CURRENCY_KEY, "BTC");
                    map.put(MARKET_KEY, "LOCAL");
                } else {
                    map.put(TYPE_KEY, "1");
                    map.put(TIMESTAMP_KEY, Instant.now().getEpochSecond());
                    map.put(PRICE_KEY, Float.valueOf(threadLocalRandom.nextInt(4500, 4700)));
                    map.put(CURRENCY_KEY, "BTC");
                    map.put(MARKET_KEY, "LOCAL");
                }

                return map;
            })
        );
    }

}
