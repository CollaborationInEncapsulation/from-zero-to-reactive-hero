package com.example.essential;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class IndexedWord {
    int index;
    @NonNull
    String word;
}
