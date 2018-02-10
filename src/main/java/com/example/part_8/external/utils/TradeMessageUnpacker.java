package com.example.part_8.external.utils;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class TradeMessageUnpacker implements MessageUnpacker {

    @SuppressWarnings("unchecked")
    private static final Tuple2<String, Integer>[] FIELDS = new Tuple2[]{
            Tuples.of("TYPE", 0x0),  // hex for binary 0, it is a special case of fields that are always there          TYPE
            Tuples.of("MARKET", 0x0),  // hex for binary 0, it is a special case of fields that are always there        MARKET
            Tuples.of("FROMSYMBOL", 0x0), // hex for binary 0, it is a special case of fields that are always there     FROM SYMBOL
            Tuples.of("TOSYMBOL", 0x0), // hex for binary 0, it is a special case of fields that are always there       TO SYMBOL
            Tuples.of("FLAGS", 0x0), // hex for binary 0, it is a special case of fields that are always there          FLAGS
            Tuples.of("ID", 0x1), // hex for binary 1                                                                   ID
            Tuples.of("TIMESTAMP", 0x2), // hex for binary 10                                                           TIMESTAMP
            Tuples.of("QUANTITY", 0x4), // hex for binary 100                                                           QUANTITY
            Tuples.of("PRICE", 0x8),// hex for binary 1000                                                              PRICE
            Tuples.of("TOTAL", 0x10) // hex for binary 10000                                                            TOTAL
    };

    public boolean supports(String messageType) {
        return messageType.equals("0");
    }

    public Map<String, Object> unpack(String message) {
        String[] valuesArray = message.split("~");
        int valuesArrayLenght = valuesArray.length;
        String mask = valuesArray[valuesArrayLenght - 1];
        int maskInt = parseInt(mask, 16);
        Map<String, Object> unpackedTrade = new HashMap<>();
        final int[] currentField = {0};

        Stream.of(FIELDS).forEach(t -> {
            String k = t.getT1();
            Integer v = t.getT2();
            if (v == 0) {
                unpackedTrade.put(k, valuesArray[currentField[0]]);
                currentField[0]++;
            } else if ((maskInt & v) > 0) {
                unpackedTrade.put(k, parseFloat(valuesArray[currentField[0]]));
                currentField[0]++;
            }
        });

        return unpackedTrade;
    }
}
