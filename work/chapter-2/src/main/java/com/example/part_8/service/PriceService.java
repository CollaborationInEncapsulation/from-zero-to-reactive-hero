package com.example.part_8.service;

import com.example.part_8.dto.MessageDTO;
import com.example.part_8.service.utils.MessageMapper;
import com.example.part_8.service.utils.Sum;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;
import java.util.logging.Logger;

public class PriceService {
    private static final Logger logger = Logger.getLogger("price-service");

    private static final long DEFAULT_AVG_PRICE_INTERVAL = 30L;

    // 1) JUST FOR WARM UP: .map() incoming Map<String, Object> to MessageDTO. For that purpose use MessageDTO.price()
    //    NOTE: Incoming Map<String, Object> contains keys PRICE_KEY and CURRENCY_KEY
    //    NOTE: Use MessageMapper utility class for message validation and transformation

    public Flux<MessageDTO> calculatePriceAndAveragePrice(
            Flux<Map<String, Object>> input,
            Flux<Long> averagesTimeInterval) {

        return input
                .doOnNext(event -> logger.fine("Incoming event: " + event))
                .transform(this::selectOnlyPriceUpdateEvents)
                .transform(this::currentPrice)
                .doOnNext(event -> logger.fine("Price event: " + event))
                .transform(mainFlow -> Flux.merge(
                        mainFlow,
                        averagePrice(averagesTimeInterval, mainFlow)
                ));
    }

    // Visible for testing
    Flux<Map<String, Object>> selectOnlyPriceUpdateEvents(Flux<Map<String, Object>> input) {
        // return Flux.never();

        return input
                .filter(MessageMapper::isPriceMessageType)
                .filter(MessageMapper::isValidPriceMessage);
    }

    // Visible for testing
    Flux<MessageDTO<Float>> currentPrice(Flux<Map<String, Object>> input) {
        return Flux.never();
    }

    // 1.1)   TODO Collect crypto currency price during the interval of seconds
    //        HINT consider corner case when client did not send any info about interval (add initial interval (mergeWith(...)))
    //        HINT use window + switchMap
    // 1.2)   TODO group collected Maps result by currency
    //        HINT to get currency name from the Map use CURRENCY_KEY constant
    //        HINT for reduce consider to reuse Sum.empty and Sum#add
    // 1.3.1) TODO Filter grouped stream on map without price info
    // 1.3.2) TODO Grouped filtered stream reduce calculate average
    // 1.3.3) TODO map to Statistic message using MessageDTO#avg()

    // 2.1)   TODO hold latest event with currency price to send latest price if new incoming event does not include price info
    //        HINT use .scan() + HashMap + .filter to ensure that after proper scan passed map include Price
    // 2.2)   TODO map to Statistic message using MessageDTO#price()

    // Visible for testing
    // TODO: Remove as should be implemented by trainees
    Flux<MessageDTO<Float>> averagePrice(
            Flux<Long> requestedInterval,
            Flux<MessageDTO<Float>> priceData
    ) {
        return Flux.never();
    }

}
