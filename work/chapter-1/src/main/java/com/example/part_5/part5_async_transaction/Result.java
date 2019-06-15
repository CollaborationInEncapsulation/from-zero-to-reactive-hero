package com.example.part_5.part5_async_transaction;

public interface Result {

    long transactionId();

    Throwable error();

    static Result ok(long transactionId) {
        return new SuccessResult(transactionId);
    }

    static Result error(Throwable t) {
        return new ErrorResult(t);
    }
}
