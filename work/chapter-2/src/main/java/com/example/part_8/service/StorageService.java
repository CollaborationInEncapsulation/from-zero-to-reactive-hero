package com.example.part_8.service;

import java.util.List;

import com.example.part_8.domain.Trade;
import com.example.part_8.dto.MessageDTO;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StorageService {
	private static final String DB_NAME = "crypto";
	private static final String COLLECTION_NAME = "trades";


	private final MongoCollection<Trade> collection;

	public StorageService() {
		MongoClient client = MongoClients.create();

		collection = client.getDatabase(DB_NAME)
		                   .getCollection(COLLECTION_NAME, Trade.class);
	}

	public Flux<MessageDTO> storeTrades(Flux<MessageDTO> trades) {
		 return trades.transform(this::enableStreamMultiplexing)
		              .transform(source -> Flux.merge(
	                      source,
			              source.transform(this::mapToDomain)
			                    .transform(this::batchData)
			                    .flatMap(this::storeInMongo)
			                    .then(Mono.empty())
					  ));
	}

	private Flux<MessageDTO> enableStreamMultiplexing(Flux<MessageDTO> trades) {
		// TODO enable multiplexing
		return trades;
	}

	private Flux<Trade> mapToDomain(Flux<MessageDTO> tradeMessagesFlux) {
		// TODO: provide correct mapping

		return Flux.empty();
	}

	private Flux<List<Trade>> batchData(Flux<Trade> tradesFlux) {
		// TODO: provide batching of data

		return Flux.empty();
	}

	private Mono<Success> storeInMongo(List<Trade> trades) {
		return Mono.from(collection.insertMany(trades));
	}
}
