package com.example.part_11;

public class WebSocketHandlerTemplate  {

}

//public class CryptoChannel implements WebSocketHandler {
//
//	final WebSocketMessageMapper          mapper;
//	final ConfigurableListableBeanFactory beanFactory;
//
//	@Override
//	public Mono<Void> handle(WebSocketSession session) {
//		return session.receive()
//		              .map(WebSocketMessage::retain)
//		              .map(WebSocketMessage::getPayload)
//		              .publishOn(Schedulers.parallel())
//		              .transform(mapper::decode)
//		              .transform(this::doHandle)
//		              .onBackpressureBuffer()
//		              .transform(m -> mapper.encode(m, session.bufferFactory()))
//		              .map(db -> new WebSocketMessage(WebSocketMessage.Type.TEXT, db))
//		              .as(session::send);
//	}
//
//	private Flux<?> doHandle(Flux<String> inbound) {
//
//	}
//}

//@Service
//public class WebSocketMessageMapper {
//
//	private final Jackson2JsonEncoder encoder;
//	private final Jackson2JsonDecoder decoder;
//
//	public WebSocketMessageMapper(ObjectMapper mapper) {
//		encoder = new Jackson2JsonEncoder(mapper);
//		decoder = new Jackson2JsonDecoder(mapper);
//	}
//
//	public Flux<DataBuffer> encode(Flux<?> outbound, DataBufferFactory dataBufferFactory) {
//		return outbound
//				.flatMap(i -> encoder.encode(
//						Mono.just(i),
//						dataBufferFactory,
//						ResolvableType.forType(Object.class),
//						MediaType.APPLICATION_JSON,
//						Collections.emptyMap()
//				));
//
//	}
//
//	@SuppressWarnings("unchecked")
//	public Flux<Long> decode(Flux<DataBuffer> inbound) {
//      // TODO: port old logic from previous example
//		return inbound.map(String:;
//	}
//}
