package com.example.part_2;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.Duration;

import static com.example.part_2.Part2ExtraExercises_Optional.*;

public class Part2ExtraExercises_OptionalTest {

    @Test
    public void firstElementFromSourceTest() {
        String element = firstElementFromSource(Flux.just("Hello", "World"));
        Assert.assertEquals("Expected 'Hello' but was [" + element + "]", "Hello", element);
    }

    @Test
    public void mergeSeveralSourcesSequentialTest() {
        PublisherProbe[] probes = new PublisherProbe[2];

        StepVerifier
                .withVirtualTime(() -> {
                    PublisherProbe<String> probeA = PublisherProbe.of(Mono.fromCallable(() -> "VANILLA").delaySubscription(Duration.ofSeconds(1)));
                    PublisherProbe<String> probeB = PublisherProbe.of(Mono.fromCallable(() -> "CHOCOLATE"));

                    probes[0] = probeA;
                    probes[1] = probeB;

                    return mergeSeveralSourcesSequential(
                            probeA.mono(),
                            probeB.mono()
                    );
                }, 0)
                .expectSubscription()
                .then(() -> probes[0].assertWasSubscribed())
                .then(() -> probes[1].assertWasSubscribed())
                .thenRequest(2)
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNext("VANILLA")
                .expectNext("CHOCOLATE")
                .verifyComplete();
    }

    @Test
    public void concatSeveralSourcesOrderedTest() {
        PublisherProbe[] probes = new PublisherProbe[2];

        StepVerifier
                .withVirtualTime(() -> {
                    PublisherProbe<String> probeA = PublisherProbe.of(Mono.fromCallable(() -> "VANILLA").delaySubscription(Duration.ofSeconds(1)));
                    PublisherProbe<String> probeB = PublisherProbe.of(Mono.fromCallable(() -> "CHOCOLATE"));

                    probes[0] = probeA;
                    probes[1] = probeB;

                    return concatSeveralSourcesOrdered(
                            probeA.mono(),
                            probeB.mono()
                    );
                }, 0)
                .expectSubscription()
                .then(() -> probes[0].assertWasSubscribed())
                .then(() -> probes[1].assertWasNotSubscribed())
                .thenRequest(1)
                .then(() -> probes[0].assertWasRequested())
                .then(() -> probes[1].assertWasNotSubscribed())
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNext("VANILLA")
                .thenRequest(1)
                .then(() -> probes[1].assertWasSubscribed())
                .then(() -> probes[1].assertWasRequested())
                .expectNext("CHOCOLATE")
                .verifyComplete();
    }

    @Test
    public void readFileTest() throws URISyntaxException {
        URI resourceUri = ClassLoader.getSystemResource("logging.properties").toURI();

        StepVerifier.create(readFile(Paths.get(resourceUri).toAbsolutePath().toString()))
                    .expectSubscription()
                    .expectNextCount(5)
                    .verifyComplete();
    }

}