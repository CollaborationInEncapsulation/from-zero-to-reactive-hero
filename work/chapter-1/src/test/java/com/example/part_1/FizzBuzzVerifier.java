package com.example.part_1;

import org.junit.Assert;
import rx.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzVerifier extends Subscriber<IndexedWord> {
    private final AtomicInteger counter = new AtomicInteger();


    @Override
    public void onCompleted() {
        if (counter.get() < 100) {
            Assert.fail("Unexpected termination, should be 100 elements emitted");
        }
    }

    @Override
    public void onError(Throwable e) {
        Assert.fail("Unexpected throwable [" + e + "]");
    }

    @Override
    public void onNext(IndexedWord indexedWord) {
        if (indexedWord.getIndex() % 5 == 0 && indexedWord.getIndex() % 3 == 0) {
            Assert.assertEquals(
                    "Should equal to FizzBuzz, but was [" + indexedWord.getWord() + "] instead",
                    "FizzBuzz",
                    indexedWord.getWord()
            );
        } else if (indexedWord.getIndex() % 3 == 0) {
            Assert.assertEquals(
                    "Should equal to Fizz, but was [" + indexedWord.getWord() + "] instead",
                    "Fizz",
                    indexedWord.getWord()
            );
        } else if (indexedWord.getIndex() % 5 == 0) {
            Assert.assertEquals(
                    "Should equal to Buzz, but was [" + indexedWord.getWord() + "] instead",
                    "Buzz",
                    indexedWord.getWord()
            );
        } else {
            try {
                Integer.valueOf(indexedWord.getWord()).toString();
            } catch (Exception e) {
                Assert.fail("Should be mapped to Number, but was [" + indexedWord.getWord() + "] instead");
            }
        }

        counter.incrementAndGet();
    }
}
