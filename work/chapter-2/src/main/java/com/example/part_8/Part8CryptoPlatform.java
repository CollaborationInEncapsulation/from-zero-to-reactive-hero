package com.example.part_8;

import com.example.part_8.external.CryptoConnectionHolder;
import com.example.part_8.service.PriceService;
import com.example.part_8.service.StorageService;
import com.example.part_8.service.TradeService;
import com.example.part_8.utils.EmbeddedMongo;
import com.example.part_8.utils.JsonUtils;
import com.example.part_8.utils.LoggerConfigurationTrait;
import com.example.part_8.utils.NettyUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.ipc.netty.http.server.HttpServer;
import reactor.ipc.netty.http.websocket.WebsocketInbound;
import reactor.ipc.netty.http.websocket.WebsocketOutbound;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.logging.Logger;

import static com.example.part_8.utils.HttpResourceResolver.resourcePath;

public class Part8CryptoPlatform extends LoggerConfigurationTrait {
    private static final Logger logger = Logger.getLogger("http-server");

    public static void main(String[] args) throws IOException {
        CryptoConnectionHolder externalSystemDataStream = new CryptoConnectionHolder();
        PriceService priceService = new PriceService();
        TradeService tradeService = new TradeService();
        StorageService storageService = new StorageService();

        EmbeddedMongo.run();
        HttpServer.create(8080)
                .startRouterAndAwait(hsr -> hsr
                        .ws(
                                "/stream",
                                handleWebsocket(externalSystemDataStream, priceService, tradeService, storageService)
                        )
                        .file("/favicon.ico", resourcePath("ui/favicon.ico"))
                        .file("/main.js", resourcePath("ui/main.js"))
                        .file("/**", resourcePath("ui/index.html"))
                );
    }

    private static BiFunction<WebsocketInbound, WebsocketOutbound, Publisher<Void>> handleWebsocket(
            CryptoConnectionHolder externalSystemDataStream,
            PriceService priceService,
            TradeService tradeService,
            StorageService storageService) {
        return (req, res) ->
                externalSystemDataStream.listenForExternalEvents()
                        .transform(tradingDataStream -> {
                            Flux<Long> priceAverageIntervalCommands = NettyUtils
                                    .prepareInput(req)
                                    .doOnNext(inMessage -> logger.info("[WS] >> " + inMessage))
                                    .transform(Part8CryptoPlatform::handleRequestedAveragePriceIntervalValue);

                            return Flux.merge(
                                    priceService.calculatePriceAndAveragePrice(
                                            tradingDataStream,
                                            priceAverageIntervalCommands
                                    ),
                                    tradeService.tradingEvents(tradingDataStream)
                                                .transform(storageService::storeTrades)
                            );
                        })
                        .map(JsonUtils::writeAsString)
                        .doOnNext(outMessage -> logger.info("[WS] << " + outMessage))
                        .transform(Part8CryptoPlatform::handleOutgoingStreamBackpressure)
                        .transform(NettyUtils.prepareOutbound(res));
    }

    // Visible for testing
    public static Flux<Long> handleRequestedAveragePriceIntervalValue(Flux<String> requestedInterval) {
        // TODO: input may be incorrect, pass only correct interval
        // TODO: ignore invalid values (empty, non number, <= 0, > 60)
        return Flux.never();
    }

    // Visible for testing
    public static Flux<String> handleOutgoingStreamBackpressure(Flux<String> outgoingStream) {
        // TODO: Add backpressure handling
        // It is possible that writing data to output may be slower than rate of
        // incoming output data

        return outgoingStream;
    }


}
