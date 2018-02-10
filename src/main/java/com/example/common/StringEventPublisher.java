package com.example.common;

import java.util.function.Consumer;

public interface StringEventPublisher {

    void registerEventListener(Consumer<String> eventListener);
}
