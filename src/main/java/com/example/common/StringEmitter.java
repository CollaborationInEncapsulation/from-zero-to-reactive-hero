package com.example.common;

import java.util.function.Consumer;

public interface StringEmitter {

    void onString(Consumer<String> consumer);
}
