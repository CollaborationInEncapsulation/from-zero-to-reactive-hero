package com.example.cryptotrading;

public class StatisticMessage {
    private final float data;
    private final String currency;
    private final StatisticMessage.Type type;

    public StatisticMessage(float data, String currency, Type type) {
        this.data = data;
        this.currency = currency;
        this.type = type;
    }

    public float getData() {
        return this.data;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Type getType() {
        return this.type;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StatisticMessage)) return false;
        final StatisticMessage other = (StatisticMessage) o;
        if (Float.compare(this.getData(), other.getData()) != 0) return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Float.floatToIntBits(this.getData());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    public String toString() {
        return "StatisticMessage(data=" + this.getData() + ", currency=" + this.getCurrency() + ", type=" + this.getType() + ")";
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
