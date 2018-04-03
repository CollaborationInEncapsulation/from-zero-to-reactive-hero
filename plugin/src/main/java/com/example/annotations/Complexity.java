package com.example.annotations;

public @interface Complexity {

    Level value();

    enum Level {
        EASY, MEDIUM, HARD, HARDCORE
    }
}
