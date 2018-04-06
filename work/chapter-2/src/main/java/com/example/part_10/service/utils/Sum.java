package com.example.part_10.service.utils;

public class Sum {
    private final float value;
    private final int counter;

    public Sum(float value, int counter) {
        this.value = value;
        this.counter = counter;
    }

    public Sum add(float value) {
        return new Sum(this.value + value, this.counter + 1);
    }

    public float avg() {
        return value / counter;
    }

    public static Sum empty() {
        return new Sum(0, 0);
    }

    public float getValue() {
        return this.value;
    }

    public int getCounter() {
        return this.counter;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Sum)) return false;
        final Sum other = (Sum) o;
        if (Float.compare(this.getValue(), other.getValue()) != 0) return false;
        if (this.getCounter() != other.getCounter()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Float.floatToIntBits(this.getValue());
        result = result * PRIME + this.getCounter();
        return result;
    }

    public String toString() {
        return "Sum(value=" + this.getValue() + ", counter=" + this.getCounter() + ")";
    }
}
