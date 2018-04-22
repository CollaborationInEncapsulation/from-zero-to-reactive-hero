package com.example.part_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.Observable;
import rx.functions.Func0;
import rx.observers.AssertableSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.TestScheduler;

import static com.example.part_1.Part1CreationTransformationTermination.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(PowerMockRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@PrepareForTest(Observable.class)
public class Part1CreationTransformationTerminationTest {

    @Test
    public void justABCTest() {
        justABC()
                .test()
                .assertValue("ABC")
                .assertCompleted()
                .awaitTerminalEvent();
    }

    @Test
    public void fromArrayOfABCTest() {
        fromArray("A", "B", "C")
                .test()
                .assertValues("A", "B", "C")
                .assertCompleted()
                .awaitTerminalEvent();
    }

    @Test
    public void fromFutureTest() {
        AssertableSubscriber<String> assertable = fromFutureInIOScheduler(CompletableFuture.completedFuture("test"))
                .test()
                .awaitValueCount(1, 10, TimeUnit.SECONDS)
                .assertValue("test")
                .assertCompleted()
                .awaitTerminalEvent();
        Thread lastSeenThread = assertable.getLastSeenThread();

        Assert.assertTrue("Expect execution on I/O thread", lastSeenThread.getName().contains("RxIoScheduler-"));
    }

    @Test
    public void errorObservableTest() {
        NullPointerException testException = new NullPointerException("test");

        error(testException)
                .test()
                .assertError(testException)
                .awaitTerminalEvent();
    }

    @Test
    public void emptyObservableTest() {
        convertNullableValueToObservable(1)
                .test()
                .assertValue(1)
                .assertCompleted()
                .awaitTerminalEvent();


        convertNullableValueToObservable(null)
                .test()
                .assertNoValues()
                .assertCompleted()
                .awaitTerminalEvent();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deferCalculationObservableTest() {
        Func0<Observable<String>> factory = Mockito.mock(Func0.class);
        Mockito.when(factory.call()).thenReturn(Observable.just("Hello")).thenReturn(Observable.just("World"));


        Observable deferCalculation = deferCalculation(factory);
        Mockito.verifyZeroInteractions(factory);

        deferCalculation
                .test()
                .assertValue("Hello")
                .assertCompleted()
                .awaitTerminalEvent();

        Mockito.verify(factory).call();

        deferCalculation
                .test()
                .assertValue("World")
                .assertCompleted()
                .awaitTerminalEvent();

        Mockito.verify(factory, Mockito.times(2)).call();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void intervalTest() {
        TestScheduler testScheduler = new TestScheduler();
        RxJavaHooks.setOnIOScheduler(s -> testScheduler);
        RxJavaHooks.setOnComputationScheduler(s -> testScheduler);
        RxJavaHooks.setOnNewThreadScheduler(s -> testScheduler);


        interval(1, TimeUnit.MINUTES)
                .test()
                .assertNoValues()
                .perform(() -> testScheduler.advanceTimeBy(1, TimeUnit.MINUTES))
                .assertValue(0L)
                .perform(() -> testScheduler.advanceTimeBy(5, TimeUnit.MINUTES))
                .assertValueCount(6)
                .assertNotCompleted()
                .awaitTerminalEventAndUnsubscribeOnTimeout(1, TimeUnit.MILLISECONDS);
    }

    @Test
    public void iterate10TimesTest() throws Exception {
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        PowerMockito.spy(Observable.class);
        PowerMockito
                .doAnswer(InvocationOnMock::callRealMethod)
                .when(Observable.class, "range", anyInt(), captor.capture());
        AtomicInteger counter = new AtomicInteger();

        iterateNTimes(10, counter);

        Assert.assertEquals("Atomic counter must be increased to 10", 10, counter.get());
        Assert.assertEquals("Observable#range(int, int) should be called", 1, captor.getAllValues().size());
    }

    @Test
    public void mapToStringTest() {
        mapToString(Observable.just(1L, 2L, 3L, 4L))
                .test()
                .assertValues("1", "2", "3", "4")
                .assertCompleted()
                .awaitTerminalEvent();
    }

    @Test
    public void flatMapWordsToCharactersTest() {
        flatMapWordsToCharacters(Observable.just("ABC", "DEFG", "HJKL"))
                .test()
                .assertValues('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L')
                .assertCompleted()
                .awaitTerminalEvent();
    }

    @Test
    public void filterTest() {
        findAllWordsWithPrefixABC(Observable.just("asdas", "gdfgsdfg", "ABCasda"))
                .test()
                .assertValue("ABCasda")
                .assertCompleted()
                .awaitTerminalEvent();
    }
}
