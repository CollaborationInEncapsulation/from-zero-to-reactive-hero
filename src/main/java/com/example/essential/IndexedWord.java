package com.example.essential;

import lombok.NonNull;
import lombok.Value;

@Value
public class IndexedWord {
    private final int index;
    private final @NonNull String word;
}
