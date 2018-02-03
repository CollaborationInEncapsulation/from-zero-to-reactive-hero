package com.example.store;

import lombok.experimental.FieldDefaults;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductsCatalog {
    static Random random = new SecureRandom();
    static List<String> PRODUCTS_NAMES = Arrays.asList(
            "Phone",
            "TV",
            "SonyPlayStation",
            "XBox",
            "Battery",
            "Lamp",
            "Toaster"
    );

    public static Product findById(String id) {
        return new Product(
                id,
                PRODUCTS_NAMES.get(random.nextInt(PRODUCTS_NAMES.size())),
                random.nextInt(10000)
        );
    }
}
