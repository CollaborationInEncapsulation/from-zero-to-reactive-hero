package com.example.part_10.service.impl;

import com.example.part_10.dto.MessageDTO;
import com.example.part_10.service.CryptoService;
import com.example.part_10.service.PriceService;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.logging.Logger;

public class DefaultPriceService implements PriceService {

    private static final Logger logger = Logger.getLogger("price-service");

    private static final long DEFAULT_AVG_PRICE_INTERVAL = 30L;

    private final Flux<MessageDTO<Float>> sharedStream;

    public DefaultPriceService(CryptoService cryptoService) {
        sharedStream = cryptoService.eventsStream()
                                    .doOnNext(event -> logger.fine("Incoming event: " + event))
                                    .transform(this::selectOnlyPriceUpdateEvents)
                                    .transform(this::currentPrice)
                                    .doOnNext(event -> logger.fine("Price event: " + event));
    }



    public Flux<MessageDTO<Float>> pricesStream(Flux<Long> intervalPreferencesStream) {
        return sharedStream.transform(mainFlow -> Flux.merge(
                mainFlow,
                averagePrice(intervalPreferencesStream, mainFlow)
        ));
    }

    // FIXME:
    // 1) JUST FOR WARM UP: .map() incoming Map<String, Object> to MessageDTO. For that purpose use MessageDTO.price()
    //    NOTE: Incoming Map<String, Object> contains keys PRICE_KEY and CURRENCY_KEY
    //    NOTE: Use MessageMapper utility class for message validation and transformation
    // Visible for testing
    Flux<Map<String, Object>> selectOnlyPriceUpdateEvents(Flux<Map<String, Object>> input) {
    	// TODO: filter only Price messages
	    // TODO: verify that price message are valid
	    // HINT: take a look at helper MessageMapper

        return Flux.never();
    }

    // Visible for testing
    Flux<MessageDTO<Float>> currentPrice(Flux<Map<String, Object>> input) {
    	// TODO map to Statistic message using MessageMapper.mapToPriceMessage

        return Flux.never();
    }

    // 1.1)   TODO Collect crypto currency price during the interval of seconds
    //        HINT consider corner case when a client did not send any info about interval (add initial interval (mergeWith(...)))
    //        HINT use window + switchMap
    // 1.2)   TODO group collected MessageDTO results by currency
    //        HINT for reduce consider to reuse Sum.empty and Sum#add
    // 1.3.2) TODO calculate average for reduced Sum object using Sun#avg
    // 1.3.3) TODO map to Statistic message using MessageDTO#avg()

    // Visible for testing
    // TODO: Remove as should be implemented by trainees
    Flux<MessageDTO<Float>> averagePrice(
            Flux<Long> requestedInterval,
            Flux<MessageDTO<Float>> priceData
    ) {
        return Flux.never();
    }

}
