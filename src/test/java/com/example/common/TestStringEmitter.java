package com.example.common;

import java.util.function.Consumer;

public class TestStringEmitter implements StringEmitter {
    public Consumer<String> consumer;

    @Override
    public void onString(Consumer<String> consumer) {
        this.consumer = consumer;
    }
}
