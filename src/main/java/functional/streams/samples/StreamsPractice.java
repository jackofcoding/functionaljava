package org.streams.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsPractice {

    public int sumOfIntegers(List<Integer> integers) {
        Objects.requireNonNull(integers, "list must not be null");
        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    public Double averageOfIntegers(List<Integer> integers) {
        Objects.requireNonNull(integers, "list must not be null");
        return integers.stream().mapToInt(Integer::valueOf).average().orElse(0.0);
    }

    public Boolean isAllMultiplesOfFive(List<Integer> integers) {
        Objects.requireNonNull(integers, "list must not be null");
        return integers.stream().allMatch( e -> Integer.remainderUnsigned(e, 5) == 0);
    }

    public Boolean checkIfOddElementsArePresent(List<Integer> integers) {
        Objects.requireNonNull(integers, "list must not be null");
        return integers.stream().anyMatch( e -> Integer.remainderUnsigned(e, 2) >  0);
    }

    public List<Integer> doubleElements(List<Integer> integers) {
        Objects.requireNonNull(integers, "list must not be null");
        return integers.stream().map(e -> e*2).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /*
    Same as above, but with collectors
     */
    public List<Integer> doubleElementsWithCollector(List<Integer> integers) {
        Objects.requireNonNull(integers, "list must not be null");
        return integers.stream().map(e -> e*2).collect(Collectors.toList());
    }

    public long countElementsThatMatchCriteria(List<Integer> integers, Predicate<Integer> criteria) {
        Objects.requireNonNull(integers, "list must not be null");
        Objects.requireNonNull(criteria, "criteria must not be null");
        return integers.stream().filter(criteria).count();
    }
}