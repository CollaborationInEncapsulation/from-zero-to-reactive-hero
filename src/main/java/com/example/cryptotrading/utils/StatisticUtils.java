package com.example.cryptotrading.utils;

import com.example.cryptotrading.StatisticMessage;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Flux;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class StatisticUtils {
    static String PRICE_KEY = "PRICE";
    static String CURRENCY_KEY = "FROMSYMBOL";

    public static Flux<StatisticMessage> transform(Flux<Map<String, Object>> input, Flux<Long> averagesTimeInterval) {
        throw new RuntimeException("Not implemented yet");


        // 1) JUST FOR WARM UP: .map() incoming Map<String, Object> to StatisticMessage. For that purpose use StatisticMessage.price()
        //    NOTE: Incoming Map<String, Object> contains keys PRICE_KEY and CURRENCY_KEY
        //    NOTE: PRICE_KEY is already float type, so you need just cast its value: (float) map.get(PRICE_KEY)

//        return Flux.merge(


        // --------------------------------------------------------------------------------------------------

        // 1.1)   TODO Collect crypto currency price during the interval of seconds
        //        HINT consider corner case when client did not send any info about interval (add initial interval (mergeWith(...)))
        //        HINT use window + switchMap
        // 1.2)   TODO group collected Maps result by currency
        //        HINT to get currency name from the Map use CURRENCY_KEY constant
        //        HINT for reduce consider to reuse Sum.empty and Sum#add
        // 1.3.1) TODO Filter grouped stream on map without price info
        // 1.3.2) TODO Grouped filtered stream reduce calculate average
        // 1.3.3) TODO map to Statistic message using StatisticMessage#avg()
        //input...,

        //
        // 2.1)   TODO hold latest event with currency price to send latest price if new incoming event does not include price info
        //        HINT use .scan() + HashMap + .filter to ensure that after proper scan passed map include Price
        // 2.2)   TODO map to Statistic message using StatisticMessage#price()
        //input...
//        );
    }

}
