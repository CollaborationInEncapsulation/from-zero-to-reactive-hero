package com.example.store;

import lombok.NonNull;
import lombok.Value;
import rx.Observable;

@Value
public class Order {
    private final @NonNull String id;
    private final @NonNull String userId;
    private final @NonNull Iterable<String> productsIds;

    public Observable<Long> getTotalPrice() {
        // TODO: use reduce to find the sum; Use ProductCatalog to find corresponded Product by its id
        throw new RuntimeException("Not implemented yet");
    }
}
