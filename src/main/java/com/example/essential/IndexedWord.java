package com.example.essential;

import java.util.Objects;

public class IndexedWord {
    private final int index;
    private final String word;

    public IndexedWord(int index, String word) {
        this.index = index;
        this.word = Objects.requireNonNull(word);
    }

    public int getIndex() {
        return this.index;
    }

    public String getWord() {
        return this.word;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof IndexedWord)) return false;
        final IndexedWord other = (IndexedWord) o;
        if (this.getIndex() != other.getIndex()) return false;
        final Object this$word = this.getWord();
        final Object other$word = other.getWord();
        if (this$word == null ? other$word != null : !this$word.equals(other$word)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getIndex();
        final Object $word = this.getWord();
        result = result * PRIME + ($word == null ? 43 : $word.hashCode());
        return result;
    }

    public String toString() {
        return "IndexedWord(index=" + this.getIndex() + ", word=" + this.getWord() + ")";
    }
}
