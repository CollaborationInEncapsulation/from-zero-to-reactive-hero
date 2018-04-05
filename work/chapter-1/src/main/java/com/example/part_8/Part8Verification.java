package com.example.part_8;

import com.example.annotations.Complexity;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

import static com.example.annotations.Complexity.Level.HARD;
import static com.example.annotations.Complexity.Level.MEDIUM;

public class Part8Verification {

    @Complexity(MEDIUM)
    @Test
    public void verifyThen10ElementsEmitted() {
        Flux<Integer> toVerify = Flux.fromStream(new Random().ints().boxed())
                .take(15)
                .skip(5);
        //TODO: use StepVerifier to perform testing

        throw new RuntimeException("Not implemented");
    }

    @Complexity(HARD)
    @Test
    public void verifyEmissionWithVirtualTimeScheduler() {
        Supplier<Flux<Long>> toVerify = () -> Flux.interval(Duration.ofDays(1))
                .take(15)
                .skip(5);

        //TODO: use StepVerifier to perform testing

        throw new RuntimeException("Not implemented");
    }
}
