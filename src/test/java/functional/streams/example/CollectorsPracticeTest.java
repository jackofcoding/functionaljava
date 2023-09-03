package functional.streams.example;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import functional.model.Person;
import functional.utils.SampleDataProvider;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollectorsPracticeTest {

  private CollectorsPractice collectorsPractice;

  @BeforeEach
  void setUp() {
    collectorsPractice = new CollectorsPractice();
  }

  @Test
  void
      test_get_words_frequency_should_return_map_with_correct_frequency_when_valid_list_is_passed() {
    List<String> words = List.of("apple", "zebra", "oranges", "oranges");
    Map<String, Long> frequencyMap = collectorsPractice.getWordsFrequency(words);
    Map<String, Long> resultantMap = Map.of("apple", 1L, "zebra", 1L, "oranges", 2L);
    assertThat(frequencyMap).isEqualTo(resultantMap);
  }

  @Test
  void test_get_words_frequency_should_return_empty_map_with_when_empty_list_is_passed() {
    assertThat(collectorsPractice.getWordsFrequency(Collections.emptyList()))
        .isEqualTo(Collections.emptyMap());
  }

  @Test
  void
      test_group_words_by_frequency_should_return_map_with_correct_frequency_when_valid_list_is_passed() {
    List<String> words = List.of("apple", "zebra", "oranges", "oranges");
    Map<Integer, Set<String>> frequencyMap = collectorsPractice.groupWordsByFrequency(words);
    Map<Integer, Set<String>> resultantMap =
        Map.of(1, Set.of("apple", "zebra"), 2, Set.of("oranges"));
    assertThat(frequencyMap).isEqualTo(resultantMap);
  }

  @Test
  void test_group_words_by_frequency_should_return_empty_map_when_empty_list_is_passed() {
    assertThat(collectorsPractice.groupWordsByFrequency(Collections.emptyList()))
        .isEqualTo(Collections.emptyMap());
  }

  @Test
  void
      test_get_person_full_name_and_age_should_return_map_with_name_and_age_when_valid_list_is_passed() {
    List<Person> people = SampleDataProvider.getPeople().stream().limit(5).toList();
    assertThat(collectorsPractice.getPersonFullNameAndAge(people))
        .isEqualTo(
            Map.of(
                "Jane Doe", 31,
                "Jason Smith", 22,
                "Anton Jackson", 33,
                "Arthur Flintstone", 27,
                "Mary Stewart", 19));
  }

  @Test
  void test_get_person_full_name_and_age_should_return_empty_map_when_empty_list_is_passed() {
    assertThat(collectorsPractice.getPersonFullNameAndAge(Collections.emptyList()))
        .isEqualTo(Collections.emptyMap());
  }

  @Test
  void
      test_partition_people_by_age_group_above_18_should_return_name_in_correct_group_when_valid_list_is_passed() {
    assertThat(collectorsPractice.partitionPeopleByAgeGroupAbove18(SampleDataProvider.getPeople()))
        .isEqualTo(
            Map.of(
                true,
                List.of(
                    "Jane Doe",
                    "Jason Smith",
                    "Anton Jackson",
                    "Arthur Flintstone",
                    "Mary Stewart",
                    "Kevin Malone",
                    "Jim Patrick",
                    "Lori Jamieson"),
                false,
                List.of("Mary Jane", "Prince Ray", "Samuel Jackson")));
  }

  @Test
  void
      test_partition_people_by_age_group_above_18_should_return_map_with_empty_list_when_empty_list_is_passed() {
    assertThat(collectorsPractice.partitionPeopleByAgeGroupAbove18(Collections.emptyList()))
        .isEqualTo(
            Map.of(
                true, Collections.emptyList(),
                false, Collections.emptyList()));
  }

  @Test
  void
      test_partition_list_by_range_should_return_sublist_when_valid_even_list_count_is_passed_with_odd_valid_range() {
    Collection<List<Integer>> partitionedByRange =
        collectorsPractice.partitionListByRange(List.of(1, 3, 4, 5, 6, 0, 10, 11), 3);
    List<List<Integer>> resultant = List.of(List.of(1, 3, 4), List.of(5, 6, 0), List.of(10, 11));

    assertThat(partitionedByRange.stream().toList()).isEqualTo(resultant);
  }

  @Test
  void
      test_partition_list_by_range_should_return_sublist_when_valid_even_list_count_is_passed_with_even_valid_range() {
    Collection<List<Integer>> partitionedByRange =
        collectorsPractice.partitionListByRange(List.of(1, 3, 4, 5, 6, 0, 10, 11), 4);
    List<List<Integer>> resultant = List.of(List.of(1, 3, 4, 5), List.of(6, 0, 10, 11));

    assertThat(partitionedByRange.stream().toList()).isEqualTo(resultant);
  }

  @Test
  void
      test_partition_list_by_range_should_return_sublist_when_valid_odd_list_count_is_passed_with_odd_valid_range() {
    Collection<List<Integer>> partitionedByRange =
        collectorsPractice.partitionListByRange(List.of(1, 3, 4, 12, 5, 6, 0, 10, 11), 3);
    List<List<Integer>> resultant =
        List.of(List.of(1, 3, 4), List.of(12, 5, 6), List.of(0, 10, 11));

    assertThat(partitionedByRange.stream().toList()).isEqualTo(resultant);
  }

  @Test
  void
      test_partition_list_by_range_should_return_sublist_when_valid_odd_list_count_is_passed_with_even_valid_range() {
    Collection<List<Integer>> partitionedByRange =
        collectorsPractice.partitionListByRange(List.of(1, 3, 4, 12, 5, 6, 0, 10, 11), 4);
    List<List<Integer>> resultant =
        List.of(List.of(1, 3, 4, 12), List.of(5, 6, 0, 10), List.of(11));

    assertThat(partitionedByRange.stream().toList()).isEqualTo(resultant);
  }


  @Test
  void test_partition_list_by_range_should_return_empty_list_when_empty_list_is_passed() {
    Collection<List<Integer>> partitionedByRange =
        collectorsPractice.partitionListByRange(Collections.emptyList(), 0);

    assertThat(partitionedByRange.stream().toList()).isEqualTo(Collections.emptyList());
  }

  @Test
  void
      test_partition_list_by_range_should_throw_exception_when_range_is_invalid_and_input_is_empty() {
    assertThatThrownBy(() -> collectorsPractice.partitionListByRange(Collections.emptyList(), 1))
        .isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("index (1) must not be greater than size (0)");
  }

  @Test
  void
      test_partition_list_by_range_should_throw_exception_when_valid_list_is_passed_with_negative_range() {
    assertThatThrownBy(
            () -> collectorsPractice.partitionListByRange(List.of(1, 3, 4, 5, 6, 0, 10, 11), -1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("range should be positive");
  }

  @Test
  void
      test_partition_list_by_range_should_return_same_list_when_valid_list_is_passed_with_range_is_equal_to_size_less_by_1() {
    Collection<List<Integer>> partitionedByRange =
        collectorsPractice.partitionListByRange(List.of(1, 3, 4, 5, 6, 0, 10, 11), 8);

    assertThat(partitionedByRange.stream().toList())
        .isEqualTo(List.of(List.of(1, 3, 4, 5, 6, 0, 10, 11)));
  }

  @Test
  void
      test_partition_list_by_range_should_return_throw_exception_when_valid_list_is_passed_with_range_is_equal_to_zero() {
    assertThatThrownBy(
            () -> collectorsPractice.partitionListByRange(List.of(1, 3, 4, 5, 6, 0, 10, 11), -1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("range should be positive");
  }
}
