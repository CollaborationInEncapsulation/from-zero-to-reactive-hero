package com.example.part_8.service.utils;

import com.example.part_8.dto.MessageDTO;

import java.util.Map;

public class MessageMapper {
    public static final String TYPE_KEY = "TYPE";
    public static final String TIMESTAMP_KEY = "TIMESTAMP";
    public static final String PRICE_KEY = "PRICE";
    public static final String QUANTITY_KEY = "QUANTITY";
    public static final String CURRENCY_KEY = "FROMSYMBOL";
    public static final String MARKET_KEY = "MARKET";
    public static final String FLAGS_KEY = "FLAGS";

    public static MessageDTO<Float> mapToPriceMessage(Map<String, Object> event) {
        return MessageDTO.price(
                (float) event.get(PRICE_KEY),
                (String) event.get(CURRENCY_KEY),
                (String) event.get(MARKET_KEY)
        );
    }
    
    public static MessageDTO<MessageDTO.Trade> mapToTradeMessage(Map<String, Object> event) {
        return MessageDTO.trade(
                (long)(float) event.get(TIMESTAMP_KEY) * 1000,
                (float) event.get(PRICE_KEY),
                event.get(FLAGS_KEY).equals("1") ? (float) event.get(QUANTITY_KEY) : -(float) event.get(QUANTITY_KEY),
                (String) event.get(CURRENCY_KEY),
                (String) event.get(MARKET_KEY)
        );
    }

    public static boolean isPriceMessageType(Map<String, Object> event) {
        return event.containsKey(TYPE_KEY) &&
                event.get(TYPE_KEY).equals("5");
    }

    public static boolean isValidPriceMessage(Map<String, Object> event) {
        return event.containsKey(PRICE_KEY) &&
                event.containsKey(CURRENCY_KEY) &&
                event.containsKey(MARKET_KEY);
    }

    public static boolean isTradeMessageType(Map<String, Object> event) {
        return event.containsKey(TYPE_KEY) &&
                event.get(TYPE_KEY).equals("0");
    }
}
