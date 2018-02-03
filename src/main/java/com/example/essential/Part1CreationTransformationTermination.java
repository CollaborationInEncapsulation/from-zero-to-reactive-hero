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
        // HINT: rx.Observable.just(T)
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> fromArray(String... args) {
        // TODO: return Observable of input args
        // HINT: rx.Observable.from(T[])
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> fromFutureInIOScheduler(Future<String> future) {
        // TODO: return Observable from future scheduled on IO scheduler
        // HINT: rx.Observable.from(java.util.concurrent.Future<? extends T>, rx.Scheduler)
        // HINT: for IO Scheduler take a look at rx.schedulers.Schedulers.*
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> error(Throwable t) {
        // TODO: return error Observable with given Throwable
        // HINT: consider usage of rx.Observable.error()
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> emptyIfInputIsGreaterThenZero(int input) {
        // TODO: return empty Observable in case if input > 0 or return string representation of input
        // HINT: rx.Observable.empty()
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> neverIfInputIsGreaterThenZero(int input) {
        // TODO: in case if input > 0 return Observable which will NEVER emit any signals or return string representation of input
        // HINT: use simple if else statement here
        // HINT: rx.Observable.never()
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> deferCalculation(Func0<Observable<String>> calculation) {
        // TODO: return deferred Observable
        // HINT: rx.Observable.defer()
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
        // TODO: when subscriber of the returned Observable<String> has subscribed,
        //       they should receive data emitted from the StringEmitter

        // NOTE: StringEmitter is a simple data source to which we may subscribe in the plain java in the next way:
        //
        //       emitter.onString(new Consumer<String>() {
        //           @Override
        //           public void accept(String s) {
        //               System.out.println(s);
        //           }
        //       });

        // NOTE: When you use Observable.unsafeCreate the parameter is also function which looks like next:
        //
        //        Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
        //            @Override
        //            public void call(Subscriber<? super String> subscriber) {
        //
        //            }
        //        });

        // NOTE: As we learned earlier, Subscriber has method onNext which should be called every time
        //       emitter.onString(new Consumer<String>()... emits new value

        // TODO: adapt to Observable; consider Observable#unsafeCreate
        // HINT: combine emitter.onString( with OnSubscribe::onNext )
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> mapToString(Observable<Long> input) {
        // TODO: map to String;
        // HINT: Use String::valueOf or Object::toString as mapping function
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<String> findAllWordsWithPrefixABC(Observable<String> input) {
        // TODO: filter strings
        // HINT: use String#startsWith
        throw new RuntimeException("Not implemented yet");
    }

    public static Observable<Character> flatMapWordsToCharacters(Observable<String> input) {
        // TODO: flat map strings to character
        // HINT: to split string on characters use string.split("")
        // HINT: remind how to wrap array to Observable
        // HINT: consider string.charAt(0) for mapping one letter string to character
        throw new RuntimeException("Not implemented yet");
    }


    public static Observable<String> flattenObservablesOrdered(Observable<Observable<String>> input) {
        // TODO: flatten map ordered strings to character
        // HINT: rx.Observable#concatMap
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Write a program that transform the numbers from 1 to 100 to String representation.
     * But:
     * * For multiples of three map to “Fizz” instead of the number.
     * * For the multiples of five map to “Buzz”.
     * * For numbers which are multiples of both three and five map to “FizzBuzz”.
     * * For the case when non of above statements are true return string representation of a number
     *
     * @param input Input of numbers from 1 to 100
     * @return Observable with mapped numbers
     */
    public static Observable<String> fizzBuzz(Observable<Integer> input) {
        // TODO: use several subsequent map and IndexWord.java for solving that problem
        throw new RuntimeException("Not implemented yet");
    }
}
