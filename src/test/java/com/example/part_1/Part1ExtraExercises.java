package com.example.part_1;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.Observable;
import rx.plugins.RxJavaHooks;
import rx.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;

import static com.example.part_1.Part1ExtraExercises_Optional.fizzBuzz;
import static com.example.part_1.Part1ExtraExercises_Optional.flattenObservablesOrdered;

@RunWith(PowerMockRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@PrepareForTest(Observable.class)
public class Part1ExtraExercises {

    @Test
    public void flattenObservablesOrderedTest() {
        TestScheduler testScheduler = new TestScheduler();
        RxJavaHooks.setOnIOScheduler(s -> testScheduler);
        RxJavaHooks.setOnComputationScheduler(s -> testScheduler);
        RxJavaHooks.setOnNewThreadScheduler(s -> testScheduler);

        flattenObservablesOrdered(Observable.just(
                Observable.just("ABC").delaySubscription(100, TimeUnit.MILLISECONDS),
                Observable.just("DEFG").delaySubscription(10, TimeUnit.MILLISECONDS),
                Observable.just("HJKL")
        ))
                .test()
                .perform(() -> testScheduler.advanceTimeBy(1, TimeUnit.SECONDS))
                .assertValues("ABC", "DEFG", "HJKL")
                .awaitTerminalEvent()
                .assertCompleted();
    }

    @Test
    public void fizzBuzzTest() {
        fizzBuzz(Observable.range(1, 100))
                .zipWith(Observable.range(1, 100), (word, index) -> new IndexedWord(index, word))
                .subscribe(new FizzBuzzVerifier());
    }
}
