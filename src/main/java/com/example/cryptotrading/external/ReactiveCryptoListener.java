package com.example.cryptotrading.external;

import io.socket.client.IO;
import io.socket.client.Socket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

class ReactiveCryptoListener {

    static Flux<Map<String, Object>> connect(Flux<String> input) {
        return Flux.create(sink -> {
            Socket socket;

            try {
                socket = IO.socket("https://streamer.cryptocompare.com/");
            } catch (URISyntaxException e) {
                sink.error(e);

                return;
            }

            socket
                    .on(Socket.EVENT_CONNECT, args -> input.subscribe(
                            v -> {
                                String[] subscription = {"5~CCCAGG~" + v + "~USD"};
                                Map<String, Object> subs = new HashMap<>();
                                subs.put("subs", subscription);
                                socket.emit("SubAdd", subs);
                            },
                            sink::error
                    ))
                    .on("m", args -> {
                        String message = args[0].toString();
                        String messageType = message.substring(0, message.indexOf("~"));
                        if (messageType.equals("5")) {
                            try {
                                sink.next(unpuck(message));
                            } catch (Throwable t) {
                                sink.error(t);
                                socket.close();
                            }
                        }
                    })
                    .on(Socket.EVENT_DISCONNECT, args -> sink.complete());

            sink.onCancel(socket::close);

            socket.connect();
        }, FluxSink.OverflowStrategy.ERROR);
    }

    @SuppressWarnings("unchecked")
    private static final Tuple2<String, Integer>[] FIELDS = new Tuple2[]{
            Tuples.of("TYPE", 0x0),       // hex for binary 0, it is a special case of fields that are always there
            Tuples.of("MARKET", 0x0),      // hex for binary 0, it is a special case of fields that are always there
            Tuples.of("FROMSYMBOL", 0x0),      // hex for binary 0, it is a special case of fields that are always there
            Tuples.of("TOSYMBOL", 0x0),      // hex for binary 0, it is a special case of fields that are always there
            Tuples.of("FLAGS", 0x0),    // hex for binary 0, it is a special case of fields that are always there
            Tuples.of("PRICE", 0x1),     // hex for binary 1
            Tuples.of("BID", 0x2),   // hex for binary 10
            Tuples.of("OFFER", 0x4),     // hex for binary 100
            Tuples.of("LASTUPDATE", 0x8),     // hex for binary 1000
            Tuples.of("AVG", 0x10),   // hex for binary 10000
            Tuples.of("LASTVOLUME", 0x20),     // hex for binary 100000
            Tuples.of("LASTVOLUMETO", 0x40),    // hex for binary 1000000
            Tuples.of("LASTTRADEID", 0x80),   // hex for binary 10000000
            Tuples.of("VOLUMEHOUR", 0x100),   // hex for binary 100000000
            Tuples.of("VOLUMEHOURTO", 0x200),    // hex for binary 1000000000
            Tuples.of("VOLUME24HOUR", 0x400),   // hex for binary 10000000000
            Tuples.of("VOLUME24HOURTO", 0x800),   // hex for binary 100000000000
            Tuples.of("OPENHOUR", 0x1000),  // hex for binary 1000000000000
            Tuples.of("HIGHHOUR", 0x2000),   // hex for binary 10000000000000
            Tuples.of("LOWHOUR", 0x4000), // hex for binary 100000000000000
            Tuples.of("OPEN24HOUR", 0x8000),  // hex for binary 1000000000000000
            Tuples.of("HIGH24HOUR", 0x10000),  // hex for binary 10000000000000000
            Tuples.of("LOW24HOUR", 0x20000),  // hex for binary 100000000000000000
            Tuples.of("LASTMARKET", 0x40000)  // hex for binary 1000000000000000000, this is a special case and will only appear on CCCAGG messages
    };

    private static Map<String, Object> unpuck(String value) {
        String[] valuesArray = value.split("~");
        int valuesArrayLenght = valuesArray.length;
        String mask = valuesArray[valuesArrayLenght - 1];
        int maskInt = parseInt(mask, 16);
        Map<String, Object> unpackedCurrent = new HashMap<>();
        final int[] currentField = {0};
        Stream.of(FIELDS).forEach(t -> {
            String k = t.getT1();
            Integer v = t.getT2();
            if (v == 0) {
                unpackedCurrent.put(k, valuesArray[currentField[0]]);
                currentField[0]++;
            } else if ((maskInt & v) > 0) {
                //i know this is a hack, for cccagg, future code please don't hate me:(, i did this to avoid
                //subscribing to trades as well in order to show the last market
                if (k.equals("LASTMARKET")) {
                    unpackedCurrent.put(k, valuesArray[currentField[0]]);
                } else {
                    unpackedCurrent.put(k, parseFloat(valuesArray[currentField[0]]));
                }
                currentField[0]++;
            }
        });

        return unpackedCurrent;
    }
}
