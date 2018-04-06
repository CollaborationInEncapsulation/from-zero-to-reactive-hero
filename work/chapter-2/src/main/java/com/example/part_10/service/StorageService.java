package com.example.part_10.service;

import java.util.List;

import com.example.part_10.dto.MessageDTO;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StorageService {

	private static final String DB_NAME         = "crypto";
	private static final String COLLECTION_NAME = "trades";

	private final MongoCollection<Document> collection;

	public StorageService() {
		MongoClient client = MongoClients.create();

		collection = client.getDatabase(DB_NAME)
		                   .getCollection(COLLECTION_NAME);
	}

	public Flux<MessageDTO> storeTrades(Flux<MessageDTO> trades) {
		return trades.transform(source -> Flux.merge(
			source,
            source.transform(this::mapToDocument)
                  .transform(this::batchData)
                  .flatMap(this::storeInMongo)
                  .then(Mono.empty())
		));
	}

	private Flux<Document> mapToDocument(Flux<MessageDTO> flux) {
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
