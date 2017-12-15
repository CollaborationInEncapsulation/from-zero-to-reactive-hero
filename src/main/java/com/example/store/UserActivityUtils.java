package com.example.store;


import rx.Observable;

public class UserActivityUtils {

    public static Observable<Product> findMostExpansivePurchase(Observable<Order> ordersHistory) {
        // TODO: flatten all Products inside Orders and using reduce find one with the highest price
        throw new RuntimeException("Not implemented yet");
    }
}
