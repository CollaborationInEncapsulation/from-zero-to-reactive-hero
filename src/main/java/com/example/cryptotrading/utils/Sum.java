package com.example.cryptotrading.utils;

import lombok.Value;

@Value
public class Sum {
    private final float value;
    private final int counter;

    public Sum add(float value) {
        return new Sum(this.value + value, this.counter + 1);
    }

    public float avg() {
        return value / counter;
    }

    public static Sum empty() {
        return new Sum(0, 0);
    }
}
