package com.example.store;

import lombok.NonNull;
import lombok.Value;

@Value
public class Product {
    private final @NonNull String id;
    private final @NonNull String name;
    private final long price;
}
