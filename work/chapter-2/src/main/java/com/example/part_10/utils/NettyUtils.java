package com.example.part_10.utils;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;

import java.nio.charset.Charset;
import java.util.function.Function;

public class NettyUtils {

    public static Flux<String> prepareInput(WebsocketInbound inbound) {
        return inbound
                .receiveFrames()
                .map(hc -> hc.content().toString(Charset.forName("UTF-8")));
    }

    public static Function<Flux<String>, Publisher<Void>> prepareOutbound(WebsocketOutbound outbound) {
        return output -> outbound.
                options(s -> s.flushOnEach(true))
                .sendString(output, Charset.forName("UTF-8"));
    }
}
