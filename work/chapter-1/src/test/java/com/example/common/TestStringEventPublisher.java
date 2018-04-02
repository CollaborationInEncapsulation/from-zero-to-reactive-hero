package com.example.common;

import java.util.function.Consumer;

public class TestStringEventPublisher implements StringEventPublisher {

   public Consumer<String> consumer;

    @Override
    public void registerEventListener(Consumer<String> eventListener) {
        this.consumer = eventListener;
    }
}
