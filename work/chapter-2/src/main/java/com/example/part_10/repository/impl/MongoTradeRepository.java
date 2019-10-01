package com.example.part_10.repository.impl;

import java.util.List;

import com.example.part_10.domain.Trade;
import com.example.part_10.dto.MessageDTO;
import com.example.part_10.repository.TradeRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MongoTradeRepository implements TradeRepository {

	private static final String DB_NAME         = "crypto";
	private static final String COLLECTION_NAME = "trades";

	private final MongoCollection<Document> collection;

	public MongoTradeRepository() {
		MongoClient client = MongoClients.create();

		collection = client.getDatabase(DB_NAME)
		                   .getCollection(COLLECTION_NAME);
	}

	public Flux<Trade> saveAll(Flux<Trade> trades) {
		return trades.transform(source -> Flux.merge(
				trades,
				source.transform(this::mapToDocument)
				      .transform(this::batchData)
				      .flatMap(this::storeInMongo)
				      .then(Mono.empty())
		));
	}

	private Flux<Document> mapToDocument(Flux<Trade> flux) {
		// TODO: Replace with corresponding mapping

		return Flux.never();
	}

	private Flux<List<Document>> batchData(Flux<Document> tradesFlux) {
		// TODO: provide batching of data

		return Flux.empty();
	}

	private Mono<Success> storeInMongo(List<Document> trades) {
		return Mono.from(collection.insertMany(trades));
	}
}
