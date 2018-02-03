package com.example.store;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import rx.Observable;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Order {
    @NonNull
    String id;
    @NonNull
    String userId;
    @NonNull
    Iterable<String> productsIds;

    public Observable<Long> getTotalPrice() {
        // TODO: use reduce to find the sum;
        // TODO: Use ProductCatalog#findById to find corresponded Product by its id
        // HINT: 3 lines of code
        // HINT: Observable.from + Observable#map + Observable#reduce
        throw new RuntimeException("Not implemented yet");
    }
}
