package com.example.part_10.utils;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class NettyUtils {

    public static Flux<String> prepareInput(WebsocketInbound inbound) {
        return inbound
                .receiveFrames()
                .map(hc -> hc.content().toString(StandardCharsets.UTF_8));
    }

    public static Function<Flux<String>, Publisher<Void>> prepareOutbound(WebsocketOutbound outbound) {
        return output -> outbound
                .sendObject(output.map(TextWebSocketFrame::new), __ -> true);
    }
}
