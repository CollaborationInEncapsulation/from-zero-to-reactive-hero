package com.example.homework;

import java.math.BigInteger;

import com.example.annotations.Complexity;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static com.example.annotations.Complexity.Level.HARD;
import static com.example.annotations.Complexity.Level.MEDIUM;

/**
 *
 */
public class Homework {

	@Complexity(HARD)
	public static Flux<BigInteger> generate() {
		return Flux.empty();
	}

	@Test
	@Complexity(MEDIUM)
	public void testGeneration() {

	}

}
