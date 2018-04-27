package com.example.part_1;

import java.util.concurrent.TimeUnit;

import com.example.common.TestStringEventPublisher;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.Observable;
import rx.plugins.RxJavaHooks;
import rx.schedulers.TestScheduler;

import static com.example.part_1.Part1ExtraExercises_Optional.*;

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


    @Test
    public void adaptToObservableTest() {
        TestStringEventPublisher emitter = new TestStringEventPublisher();

        adaptToObservable(emitter)
                .test()
                .assertNoValues()
                .perform(() -> emitter.consumer.accept("1"))
                .assertValue("1")
                .perform(() -> emitter.consumer.accept("2"))
                .assertValues("1", "2")
                .perform(() -> emitter.consumer.accept("3"))
                .assertValues("1", "2", "3")
                .assertNotCompleted()
                .awaitTerminalEventAndUnsubscribeOnTimeout(1, TimeUnit.MILLISECONDS)
                .assertUnsubscribed();
    }
}
