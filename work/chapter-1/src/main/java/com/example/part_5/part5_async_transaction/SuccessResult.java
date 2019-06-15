package com.example.part_5.part5_async_transaction;

public class SuccessResult implements Result {

    private final long transactionId;

    public SuccessResult(long transactionId) {

        this.transactionId = transactionId;
    }

    @Override
    public long transactionId() {
        return transactionId;
    }

    @Override
    public Throwable error() {
        return null;
    }
}
