package com.example.part_1;

import com.example.annotations.Complexity;
import com.example.annotations.Optional;
import rx.Observable;

import static com.example.annotations.Complexity.Level.EASY;
import static com.example.annotations.Complexity.Level.HARD;

public class Part1ExtraExercises_Optional {

    @Optional
    @Complexity(EASY)
    public static Observable<String> flattenObservablesOrdered(Observable<Observable<String>> input) {
        // TODO: flatten map ordered strings to character
        // HINT: rx.Observable#concatMap

        throw new RuntimeException("Not implemented");
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
     * @see IndexedWord
     * @return Observable with mapped numbers
     */
    @Optional
    @Complexity(HARD)
    public static Observable<String> fizzBuzz(Observable<Integer> input) {
        throw new RuntimeException("Not implemented");
    }
}
