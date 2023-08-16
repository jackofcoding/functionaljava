package org.streams.samples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StreamsPracticeTest {

    private StreamsPractice streamsPractice;

    @BeforeEach
    void setUp() {
        streamsPractice = new StreamsPractice();
    }

    @Test
    void test_of_sum_of_integers_should_return_sum_when_valid_integers_is_passed() {
        int sum = streamsPractice.sumOfIntegers(List.of(1, 2, 3, 4, 6, 7));
        assertThat(sum).isEqualTo(23);
    }

    @Test
    void test_of_sum_of_integers_should_return_zero_when_empty_list_is_passed() {
        int sum = streamsPractice.sumOfIntegers(Collections.emptyList());
        assertThat(sum).isZero();
    }

    @Test
    void test_of_sum_of_integers_should_throw_exception_when_null_is_passed() {
        assertThatThrownBy(() -> streamsPractice.sumOfIntegers(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("list must not be null");
    }

    @Test
    void test_of_average_of_integers_should_return_average_when_valid_integers_is_passed() {
        List<Integer> integers = List.of(1, 2, 3, 4, 6, 7);
        var average = streamsPractice.averageOfIntegers(integers);
        assertThat(average-integers.stream().mapToInt(Integer::valueOf).average().orElse(0))
                .isLessThan(1e-9);
    }

    @Test
    void test_of_average_of_integers_should_return_zero_when_empty_list_is_passed() {
        List<Integer> integers = List.of(1, 2, 3, 4, 6, 7);
        var average = streamsPractice.averageOfIntegers(integers);
        assertThat(average-integers.stream().mapToInt(Integer::valueOf).average().orElse(0))
                .isLessThan(1e-9);
    }

    @Test
    void test_of_average_of_integers_should_throw_exception_when_null_is_passed() {
        assertThatThrownBy(() -> streamsPractice.averageOfIntegers(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("list must not be null");
    }

    @Test
    void test_of_factors_of_five_should_return_true_when_all_are_multiples_of_5_passed() {
        List<Integer> integers = List.of(5, 20, 100, 350, 60);
        assertThat(streamsPractice.isAllMultiplesOfFive(integers)).isTrue();
    }

    @Test
    void test_of_factors_of_five_should_return_false_when_all_are_not_multiples_of_5_passed() {
        List<Integer> integers = List.of(51, 202, 100, 350, 60);
        assertThat(streamsPractice.isAllMultiplesOfFive(integers)).isFalse();
    }

    @Test
    void test_of_factors_of_five_should_throw_exception_when_null_is_passed() {
        assertThatThrownBy(() -> streamsPractice.averageOfIntegers(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("list must not be null");
    }

    @Test
    void test_of_check_odd_elements_are_present_should_return_true_when_list_contains_odd_element_are_passed() {
        List<Integer> integers = List.of(-5, 0, 10, -134, 9);
        assertThat(streamsPractice.checkIfOddElementsArePresent(integers)).isTrue();
    }

    @Test
    void test_of_check_odd_elements_are_present_should_return_false_when_list_does_not_contain_odd_element_are_passed() {
        List<Integer> integers = List.of(44, -10, 10, 90);
        assertThat(streamsPractice.checkIfOddElementsArePresent(integers)).isFalse();
    }

    @Test
    void test_of_double_elements_should_double_each_element_when_valid_integers_are_passed() {
        List<Integer> integers = List.of(-5, 10, 0, 34, 9);
        assertThat(streamsPractice.doubleElements(integers))
                .hasSameElementsAs(List.of(-10, 20, 0, 68, 18));
    }

    @Test
    void test_of_double_elements_should_return_empty_list_when_empty_integers_are_passed() {
        assertThat(streamsPractice.doubleElements(Collections.emptyList())).isEmpty();
    }

    @Test
    void test_of_double_elements_with_collectors_should_double_each_element_when_valid_integers_are_passed() {
        List<Integer> integers = List.of(-5, 10, 0, 34, 9);
        assertThat(streamsPractice.doubleElements(integers)).hasSize(5)
                .hasSameElementsAs(List.of(-10, 20, 0, 68, 18));
    }

    @Test
    void test_of_double_elements_with_collectors_should_return_empty_list_when_empty_integers_are_passed() {
        assertThat(streamsPractice.doubleElements(Collections.emptyList())).isEmpty();
    }

    @Test
    void test_of_count_elements_that_match_criteria_should_return_correct_count_when_valid_integers_even_number_criteria_are_passed() {
        List<Integer> integers = List.of(-5, 10, 0, 34, 9);
        Predicate<Integer> evenNumberCriteria = (element) -> element % 2 == 0;
        assertThat(streamsPractice.countElementsThatMatchCriteria(integers, evenNumberCriteria))
                .isEqualTo(3);
    }

    @Test
    void test_of_count_elements_that_match_criteria_should_return_zero_when_only_odd_integers_with_even_number_criteria_are_passed() {
        List<Integer> integers = List.of(-15, 101, 9, 341, 91);
        Predicate<Integer> evenNumberCriteria = (element) -> element % 2 == 0;
        assertThat(streamsPractice.countElementsThatMatchCriteria(integers, evenNumberCriteria)).isZero();
    }
}