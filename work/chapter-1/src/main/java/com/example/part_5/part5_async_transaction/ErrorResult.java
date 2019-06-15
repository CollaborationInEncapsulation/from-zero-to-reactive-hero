package com.example.part_5.part5_async_transaction;

public class ErrorResult implements Result {

    private final Throwable error;

    public ErrorResult(Throwable throwable) {
        error = throwable;
    }

    @Override
    public long transactionId() {
        return -1;
    }

    @Override
    public Throwable error() {
        return error;
    }
}
