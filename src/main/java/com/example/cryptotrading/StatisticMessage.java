package com.example.cryptotrading;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class StatisticMessage {
    long timestamp;
    float data;
    String currency;
    StatisticMessage.Type type;

    public StatisticMessage(float data, String currency, Type type) {
        this.timestamp = Instant.now().toEpochMilli();
        this.data = data;
        this.currency = currency;
        this.type = type;
    }

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
