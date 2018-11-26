package com.example.part_10;

import com.example.part_10.controller.WSHandler;
import com.example.part_10.repository.TradeRepository;
import com.example.part_10.service.CryptoService;
import com.example.part_10.service.PriceService;
import com.example.part_10.service.TradeService;
import com.example.part_10.service.external.CryptoCompareService;
import com.example.part_10.service.impl.DefaultPriceService;
import com.example.part_10.repository.impl.DefaultTradeRepository;
import com.example.part_10.service.impl.DefaultTradeService;
import com.example.part_10.utils.EmbeddedMongo;
import com.example.part_10.utils.JsonUtils;
import com.example.part_10.utils.LoggerConfigurationTrait;
import com.example.part_10.utils.NettyUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.logging.Logger;

import static com.example.part_10.utils.HttpResourceResolver.resourcePath;

public class Part10CryptoPlatform extends LoggerConfigurationTrait {

	private static final Logger logger = Logger.getLogger("http-server");

	public static void main(String[] args) throws IOException {
		CryptoService cryptoCompareService = new CryptoCompareService();
		TradeRepository tradeRepository = new DefaultTradeRepository();
		PriceService defaultPriceService = new DefaultPriceService(cryptoCompareService);
		TradeService defaultTradeService = new DefaultTradeService(cryptoCompareService, tradeRepository);
		WSHandler handler = new WSHandler(defaultPriceService, defaultTradeService);

		EmbeddedMongo.run();
		HttpServer.create()
		          .host("localhost")
		          .port(8080)
		          .route(hsr ->
			          hsr.ws("/stream", handleWebsocket(handler))
                         .file("/favicon.ico", resourcePath("ui/favicon.ico"))
                         .file("/main.js", resourcePath("ui/main.js"))
                         .file("/**", resourcePath("ui/index.html"))
		          )
		          .bindNow()
		          .onDispose()
		          .block();
	}

	private static BiFunction<WebsocketInbound, WebsocketOutbound, Publisher<Void>> handleWebsocket(WSHandler handler) {
		return (req, res) ->
			NettyUtils.prepareInput(req)
			          .doOnNext(inMessage -> logger.info("[WS] >> " + inMessage))
			          .transform(Part10CryptoPlatform::handleRequestedAveragePriceIntervalValue)
			          .transform(handler::handle)
			          .map(JsonUtils::writeAsString)
			          .doOnNext(outMessage -> logger.info("[WS] << " + outMessage))
			          .transform(Part10CryptoPlatform::handleOutgoingStreamBackpressure)
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
