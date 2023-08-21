package functional.streams.samples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import functional.enums.EmployeeTypeCode;
import functional.utils.UTF8FileReaderUtility;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamsPracticeTest {

  private StreamsPractice streamsPractice;

  @BeforeEach
  void setUp() {
    streamsPractice = new StreamsPractice();
  }

  @Test
  void test_sum_of_integers_should_return_sum_when_valid_integers_is_passed() {
    int sum = streamsPractice.sumOfIntegers(List.of(1, 2, 3, 4, 6, 7));
    assertThat(sum).isEqualTo(23);
  }

  @Test
  void test_sum_of_integers_should_return_zero_when_empty_list_is_passed() {
    int sum = streamsPractice.sumOfIntegers(Collections.emptyList());
    assertThat(sum).isZero();
  }

  @Test
  void test_sum_of_integers_should_throw_exception_when_null_is_passed() {
    assertThatThrownBy(() -> streamsPractice.sumOfIntegers(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("list must not be null");
  }

  @Test
  void test_average_of_integers_should_return_average_when_valid_integers_is_passed() {
    List<Integer> integers = List.of(1, 2, 3, 4, 6, 7);
    var average = streamsPractice.averageOfIntegers(integers);
    assertThat(average - integers.stream().mapToInt(Integer::valueOf).average().orElse(0))
        .isLessThan(1e-9);
  }

  @Test
  void test_average_of_integers_should_return_zero_when_empty_list_is_passed() {
    List<Integer> integers = List.of(1, 2, 3, 4, 6, 7);
    var average = streamsPractice.averageOfIntegers(integers);
    assertThat(average - integers.stream().mapToInt(Integer::valueOf).average().orElse(0))
        .isLessThan(1e-9);
  }

  @Test
  void test_average_of_integers_should_throw_exception_when_null_is_passed() {
    assertThatThrownBy(() -> streamsPractice.averageOfIntegers(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("list must not be null");
  }

  @Test
  void test_factors_of_five_should_return_true_when_all_are_multiples_of_5_passed() {
    List<Integer> integers = List.of(5, 20, 100, 350, 60);
    assertThat(streamsPractice.isAllMultiplesOfFive(integers)).isTrue();
  }

  @Test
  void test_factors_of_five_should_return_true_when_all_are_multiples_of_5_and_has_null_element_passed() {
    List<Integer> integers = Arrays.asList(5, 20, 100, null, 350, 60);
    assertThat(streamsPractice.isAllMultiplesOfFive(integers)).isTrue();
  }

  @Test
  void test_factors_of_five_should_return_false_when_all_are_not_multiples_of_5_passed() {
    List<Integer> integers = List.of(51, 202, 100, 350, 60);
    assertThat(streamsPractice.isAllMultiplesOfFive(integers)).isFalse();
  }

  @Test
  void test_factors_of_five_should_throw_exception_when_null_is_passed() {
    assertThatThrownBy(() -> streamsPractice.averageOfIntegers(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("list must not be null");
  }

  @Test
  void test_check_odd_elements_are_present_should_return_true_when_list_contains_odd_element_are_passed() {
    List<Integer> integers = List.of(-5, 0, 10, -134, 9);
    assertThat(streamsPractice.checkIfOddElementsArePresent(integers)).isTrue();
  }

  @Test
  void test_check_odd_elements_are_present_should_return_false_when_list_does_not_contain_odd_element_are_passed() {
    List<Integer> integers = List.of(44, -10, 10, 90);
    assertThat(streamsPractice.checkIfOddElementsArePresent(integers)).isFalse();
  }

  @Test
  void test_double_elements_should_double_each_element_when_valid_integers_are_passed() {
    List<Integer> integers = List.of(-5, 10, 0, 34, 9);
    assertThat(streamsPractice.doubleElements(integers))
        .hasSameElementsAs(List.of(-10, 20, 0, 68, 18));
  }

  @Test
  void test_double_elements_should_return_empty_list_when_empty_integers_are_passed() {
    assertThat(streamsPractice.doubleElements(Collections.emptyList())).isEmpty();
  }

  @Test
  void test_double_elements_with_collectors_should_double_each_element_when_valid_integers_are_passed() {
    List<Integer> integers = List.of(-5, 10, 0, 34, 9);
    assertThat(streamsPractice.multiplyElementsWithCollector(integers, 2)).hasSize(5)
        .isEqualTo(List.of(-10, 20, 0, 68, 18));
  }

  @Test
  void test_double_elements_with_collectors_should_return_empty_list_when_empty_integers_are_passed() {
    assertThat(streamsPractice.multiplyElementsWithCollector(Collections.emptyList(), 3)).isEmpty();
  }

  @Test
  void test_count_elements_that_match_criteria_should_return_correct_count_when_valid_integers_even_number_criteria_are_passed() {
    List<Integer> integers = List.of(-5, 10, 0, 34, 9);
    Predicate<Integer> evenNumberCriteria = (element) -> element % 2 == 0;
    assertThat(streamsPractice.countElementsThatMatchCriteria(integers, evenNumberCriteria))
        .isEqualTo(3);
  }

  @Test
  void test_count_elements_that_match_criteria_should_return_zero_when_only_odd_integers_with_even_number_criteria_are_passed() {
    List<Integer> integers = List.of(-15, 101, 9, 341, 91);
    Predicate<Integer> evenNumberCriteria = (element) -> element % 2 == 0;
    assertThat(
        streamsPractice.countElementsThatMatchCriteria(integers, evenNumberCriteria)).isZero();
  }

  @Test
  void test_sort_two_list_must_return_sorted_list_when_two_list_are_provided() {
    List<String> words1 = List.of("Zebra", "Kangaroo", "Elephant");
    List<String> words2 = List.of("Zoom", "Apple", "Brick");
    assertThat(streamsPractice.sortTwoList(words1, words2)).isSorted()
        .isEqualTo(List.of("Apple", "Brick", "Elephant", "Kangaroo", "Zebra", "Zoom"));
  }

  @Test
  void test_sort_two_list_must_return_sorted_list_when_one_list_is_provided() {
    List<String> words1 = List.of("Zebra", "Kangaroo", "Elephant");
    List<String> words2 = Collections.emptyList();
    assertThat(streamsPractice.sortTwoList(words1, words2)).isSorted()
        .hasSameElementsAs(List.of("Elephant", "Kangaroo", "Zebra"));
  }

  @Test
  void test_sort_two_list_must_return_empty_list_when_empty_lists_are_provided() {
    assertThat(
        streamsPractice.sortTwoList(Collections.emptyList(), Collections.emptyList())).isEmpty();
  }

  @Test
  void test_sort_two_list_must_throw_exception_when_first_list_is_null() {
    List<String> words2 = List.of("Zebra", "Kangaroo", "Elephant");
    List<String> words1 = null;
    assertThatThrownBy(() -> streamsPractice.sortTwoList(words1, words2)).isInstanceOf(
            NullPointerException.class)
        .hasMessage("first list must not be null");
  }

  @Test
  void test_sort_two_list_must_throw_exception_when_second_list_is_null() {
    List<String> words1 = List.of("Zebra", "Kangaroo", "Elephant");
    List<String> words2 = null;
    assertThatThrownBy(() -> streamsPractice.sortTwoList(words1, words2))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("second list must not be null");
  }

  @Test
  void test_check_if_unique_elements_only_should_return_false_when_list_contains_duplicate() {
    List<String> words = List.of("Zebra", "Kangaroo", "Elephant", "Kangaroo");
    assertThat(streamsPractice.checkIfUniqueElementsOnly(words)).isFalse();
  }

  @Test
  void test_check_if_unique_elements_only_should_return_true_when_list_does_not_contains_duplicate() {
    List<String> words = List.of("Zebra", "Kangaroo", "Elephant");
    assertThat(streamsPractice.checkIfUniqueElementsOnly(words)).isTrue();
  }

  @Test
  void test_check_if_unique_elements_only_should_return_true_when_list_is_empty() {
    List<String> words = Collections.emptyList();
    assertThat(streamsPractice.checkIfUniqueElementsOnly(words)).isTrue();
  }

  @Test
  void test_check_if_unique_elements_only_should_throw_exception_when_list_is_null() {
    assertThatThrownBy(() -> streamsPractice.checkIfUniqueElementsOnly(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("words must not be null");
  }

  @Test
  void test_get_unique_words_from_sentences_should_return_unique_words_when_valid_list_of_sentences_are_passed() {
    List<String> lines = List.of(
        "This is first sentence of sample statement",
        "This is a test data for unique words function",
        "Sample data will help in verifying data");
    assertThat(streamsPractice.getUniqueWordsFromSentences(lines))
        .isEqualTo(
            List.of(
                "This",
                "is",
                "first",
                "sentence",
                "of",
                "sample",
                "statement",
                "a",
                "test",
                "data",
                "for",
                "unique",
                "words",
                "function",
                "Sample",
                "will",
                "help",
                "in",
                "verifying"));
  }

  @Test
  void test_max_should_return_max_value_when_valid_list_is_passed() {
    assertThat(streamsPractice.max(List.of(10, -10, 0, 2, 24, 10))).isEqualTo(24);
  }

  @Test
  void test_max_should_return_max_value_when_all_elements_are_same() {
    assertThat(streamsPractice.max(Arrays.asList(10, 10, 10, 10, 10, 10))).isEqualTo(10);
  }

  @Test
  void test_max_should_throw_null_pointer_exception_when_one_element_is_null() {
    assertThatThrownBy(() -> streamsPractice.max(Arrays.asList(510, -110, -23, -1, -10, null)))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void test_min_should_return_min_value_when_valid_list_is_passed() {
    assertThat(streamsPractice.min(List.of(10, -10, 0, -2, 24, 10))).isEqualTo(-10);
  }

  @Test
  void test_min_should_return_min_value_when_all_elements_are_same() {
    assertThat(streamsPractice.min(Arrays.asList(10, 10, 10, 10, 10, 10))).isEqualTo(10);
  }

  @Test
  void test_min_should_throw_null_pointer_exception_when_one_element_is_null() {
    assertThatThrownBy(() -> streamsPractice.min(Arrays.asList(510, -110, -23, -1, -10, null)))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void test_random_integers_supplier_should_return_5_integers_when_limit_is_5() {
    assertThat(streamsPractice.randomIntegersSupplier(5)).hasSize(5)
        .isInstanceOf(List.class)
        .hasOnlyElementsOfType(Integer.class);
  }

  @Test
  void test_random_integers_supplier_should_return_empty_list_when_limit_is_zero() {
    assertThat(streamsPractice.randomIntegersSupplier(0)).isInstanceOf(List.class).isEmpty();
  }

  @Test
  void test_random_integers_supplier_should_throw_exception_when_limit_is_negative() {
    assertThatThrownBy(() -> streamsPractice.randomIntegersSupplier(-12))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test_transform_integers_to_string_should_return_string_array_when_integer_list_is_passed() {
    assertThat(streamsPractice.transformIntegersToString(List.of(20, 34, 3, 21, -9)))
        .hasSize(5)
        .isInstanceOf(String[].class);
  }

  @Test
  void test_transform_integers_to_string_should_return_empty_array_when_empty_integer_list_is_passed() {
    assertThat(streamsPractice.transformIntegersToString(Collections.emptyList()))
        .isInstanceOf(String[].class)
        .isEmpty();
  }

  @Test
  void test_generate_first_n_fibonacci_numbers_should_return_first_n_fibonacci_numbers_when_limit_is_positive() {
    assertThat(streamsPractice.generateFirstNFibonacciNumbers(10))
        .hasSize(10)
        .isEqualTo(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34));
  }

  @Test
  void test_generate_first_n_fibonacci_numbers_should_return_empty_list_when_limit_is_zero() {
    assertThat(streamsPractice.generateFirstNFibonacciNumbers(0)).isEmpty();
  }

  @Test
  void test_generate_first_n_fibonacci_numbers_should_throw_exception_when_limit_is_negative() {
    assertThatThrownBy(() -> streamsPractice.generateFirstNFibonacciNumbers(-4))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test_convert_any_case_to_title_case_should_return_title_case_string_when_any_case_string_is_passed() {
    assertThat(streamsPractice.convertAnyCaseToTitleCase(List.of("firstNAmE", "laStName")))
        .hasSize(2)
        .isEqualTo(List.of("Firstname", "Lastname"));
  }

  @Test
  void test_convert_any_case_to_title_case_should_ignore_invalid_string_when_invalid_string_is_passed() {
    assertThat(streamsPractice.convertAnyCaseToTitleCase(Arrays.asList(null, "", ";", "\\name")))
        .isEmpty();
  }

  @Test
  void test_convert_any_case_to_title_case_should_ignore_invalid_character_when_string_with_invalid_character_is_not_at_beginning_is_passed() {
    assertThat(streamsPractice.convertAnyCaseToTitleCase(List.of("n\\ame", "Title()")))
        .hasSize(2)
        .isEqualTo(List.of("N\\ame", "Title()"));
  }

  @Test
  void test_convert_any_case_to_title_case_should_return_empty_list_when_empty_list_is_passed() {
    assertThat(streamsPractice.convertAnyCaseToTitleCase(Collections.emptyList())).isEmpty();
  }

  @Test
  void test_get_employee_type_code_with_rank_above_should_return_empty_list_when_rank_limit_is_positive() {
    assertThat(streamsPractice.getEmployeeTypeCodeWithRankAbove(6)).hasSize(3).
        doesNotContain(EmployeeTypeCode.SUBJECT_MATTER_EXPERT)
        .isEqualTo(
            List.of(
                EmployeeTypeCode.C_SUITE,
                EmployeeTypeCode.DIRECTOR,
            EmployeeTypeCode.RELEASE_TRAIN_ENGINEER));
  }

  @Test
  void test_get_employee_type_code_with_rank_above_should_return_empty_list_when_rank_limit_is_less_than_one() {
    assertThat(streamsPractice.getEmployeeTypeCodeWithRankAbove(-6)).isEmpty();
    assertThat(streamsPractice.getEmployeeTypeCodeWithRankAbove(0)).isEmpty();
  }

  @Test
  void test_suffix_word_should_return_tree_names_with_suffix_when_valid_list_is_passed() {
    assertThat(streamsPractice.suffixWord(List.of("Teak", "Sandal"), "wood"))
        .hasSize(2)
        .isEqualTo(List.of("Teak wood", "Sandal wood"));
  }

  @Test
  void test_suffix_word_should_not_return_empty_list_when_invalid_list_is_passed() {
    assertThat(streamsPractice.suffixWord(Arrays.asList("", null), "wood")).isEmpty();
  }

  @Test
  void test_suffix_word_should_return_list_as_is_when_null_suffix_is_passed() {
    assertThat(streamsPractice.suffixWord(List.of("Teak", "Sandal"), null)).isEmpty();
  }

  @Test
  void test_suffix_word_should_return_empty_list_when_empty_suffix_is_passed() {
    assertThat(streamsPractice.suffixWord(List.of("Teak", "Sandal"), "")).isEmpty();
  }

  @Test
  void test_get_valid_phone_numbers_from_file_should_return_phone_numbers_when_valid_file_is_passed() {
    List<String> lines =
        UTF8FileReaderUtility.getFileContentsAsSentencesFromResources("PhoneNumberSample.txt");
    List<String> phoneNumbersFromFile = streamsPractice.getValidPhoneNumbersFromFile(lines);
    assertThat(phoneNumbersFromFile)
        .hasSize(3)
        .hasSameElementsAs(List.of("0230349821", "0230202010", "2343452413"));
  }

  @Test
  void test_get_valid_phone_numbers_from_file_should_return_empty_list_when_empty_list_is_passed() {
    List<String> phoneNumbersFromFile =
        streamsPractice.getValidPhoneNumbersFromFile(Collections.emptyList());
    assertThat(phoneNumbersFromFile).isEmpty();
  }

}

