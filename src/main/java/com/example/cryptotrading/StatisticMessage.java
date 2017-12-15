package com.example.cryptotrading;

import lombok.Value;

@Value
public class StatisticMessage {
    private final float data;
    private final String currency;
    private final StatisticMessage.Type type;

    public enum Type {
        PRICE, AVG
    }

    public static StatisticMessage avg(float avg, String currency) {
        return new StatisticMessage(avg, currency, Type.AVG);
    }

    public static StatisticMessage price(float price, String currency) {
        return new StatisticMessage(price, currency, Type.PRICE);
    }
}
