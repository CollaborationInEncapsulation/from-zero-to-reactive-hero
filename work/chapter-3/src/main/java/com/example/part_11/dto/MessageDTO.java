package com.example.part_11.dto;

import java.time.Instant;

public class MessageDTO<T> {
    private final long timestamp;
    private final T data;
    private final String currency;
    private final String market;
    private final MessageDTO.Type type;

    public MessageDTO(long timestamp, T data, String currency, String market, Type type) {
        this.timestamp = timestamp;
        this.data = data;
        this.currency = currency;
        this.market = market;
        this.type = type;
    }

    public T getData() {
        return this.data;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Type getType() {
        return this.type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMarket() {
        return market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageDTO<?> that = (MessageDTO<?>) o;

        if (timestamp != that.timestamp) return false;
        if (!data.equals(that.data)) return false;
        if (!currency.equals(that.currency)) return false;
        if (!market.equals(that.market)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + data.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + market.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "timestamp=" + timestamp +
                ", data=" + data +
                ", currency='" + currency + '\'' +
                ", market='" + market + '\'' +
                ", type=" + type +
                '}';
    }

    public static MessageDTO<Float> avg(float avg, String currency, String market) {
        return new MessageDTO<>(Instant.now().toEpochMilli(), avg, currency, market, Type.AVG);
    }

    public static MessageDTO<Float> price(float price, String currency, String market) {
        return new MessageDTO<>(Instant.now().toEpochMilli(), price, currency, market, Type.PRICE);
    }

    public static MessageDTO<Trade> trade(long timestamp, float price, float amount, String currency, String market) {
        return new MessageDTO<>(timestamp, new Trade(price, amount), currency, market, Type.TRADE);
    }


    public enum Type {
        PRICE, AVG, TRADE
    }

    public static class Trade {
        private final float price;
        private final float amount;

        public Trade(float price, float amount) {
            this.price = price;
            this.amount = amount;
        }

        public float getPrice() {
            return price;
        }

        public float getAmount() {
            return amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Trade trade = (Trade) o;

            if (Float.compare(trade.price, price) != 0) return false;
            return Float.compare(trade.amount, amount) == 0;
        }

        @Override
        public int hashCode() {
            int result = (price != +0.0f ? Float.floatToIntBits(price) : 0);
            result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
            return result;
        }
    }
}
