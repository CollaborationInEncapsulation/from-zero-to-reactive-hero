package com.example.store;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProductsCatalog {
    private static Random random = new Random();
    private static List<String> PRODUCTS_NAMES = Arrays.asList(
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
