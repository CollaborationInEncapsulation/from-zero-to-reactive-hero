package com.example.essential;

import com.example.common.StringEmitter;
import rx.Observable;
import rx.functions.Func0;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1CreationTransformationTermination {

    public static Observable<String> justABC() {
        // TODO: return "ABC" using Observable API
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> fromArray(String... args) {
        // TODO: return Observable of input args
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> fromFutureInIOScheduler(Future<String> future) {
        // TODO: return Observable from future scheduled on IO scheduler
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> error(Throwable t) {
        // TODO: return error Observable
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> emptyIfInputIsGreaterThenZero(int input) {
        // TODO: return empty Observable in case if input > 0 or return string representation of input
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> neverIfInputIsGreaterThenZero(int input) {
        // TODO: return never Observable in case if input > 0 or return string representation of input
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> deferCalculation(Func0<Observable<String>> calculation) {
        // TODO: return deferred Observable
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<Long> interval(long interval, TimeUnit timeUnit) {
        // TODO: return interval Observable
        throw new RuntimeException("Not implemented yet");
    }

    public static void iterateNTimes(int times, AtomicInteger counter) {
        // TODO: refactor using Observable#range and Observable#subscribe or Observable#doOnNext
        for (int i = 0; i < times; i++) {
            counter.incrementAndGet();
        }
    }

    public static Observable<String> adaptToObservable(StringEmitter emitter) {
        // TODO: adapt to Observable; consider unsafeCreate
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> mapToString(Observable<Long> input) {
        // TODO: map to String
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> findAllWordsWithPrefixABC(Observable<String> input) {
        // TODO: filter strings
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<Character> flatMapWordsToCharacters(Observable<String> input) {
        // TODO: flat map strings to character
        throw new RuntimeException("Not implemented yet");
    }


    public static Observable<String> flattenObservablesOrdered(Observable<Observable<String>> input) {
        // TODO: flatten map ordered strings to character
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Write a program that transform the numbers from 1 to 100 to String representation.
     * But:
     * * For multiples of three map to “Fizz” instead of the number.
     * * For the multiples of five map to “Buzz”.
     * * For numbers which are multiples of both three and five map to “FizzBuzz”.
     *
     * @param input Input of numbers from 1 to 100
     * @return Observable with mapped numbers
     */
    public static Observable<String> fizzBuzz(Observable<Integer> input) {
        // TODO: use several subsequent map and IndexWord.java for solving that problem
        throw new RuntimeException("Not implemented yet");
    }
}
