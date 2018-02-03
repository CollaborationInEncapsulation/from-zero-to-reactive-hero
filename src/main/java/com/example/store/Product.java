package com.example.store;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Product {
    @NonNull
    String id;
    @NonNull
    String name;
    long price;
}
