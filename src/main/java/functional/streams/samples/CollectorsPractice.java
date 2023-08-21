package functional.streams.samples;

import functional.model.Person;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsPractice {

  public Map<String, Long> getWordsFrequency(List<String> words) {
    Objects.requireNonNull(words, "words must not be null");
    return words.stream()
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  public Map<Integer, Set<String>> groupWordsByFrequency(List<String> words) {
    Objects.requireNonNull(words, "words must not be null");
    return words.stream()
        .filter(Objects::nonNull)
        .collect(
            Collectors.groupingBy(e -> Collections.frequency(words, e), HashMap::new, Collectors.toSet()));
  }

  public Map<String, Integer> getPersonFullNameAndAge(List<Person> people) {
    // Assumption: no field of model is null
    return people.stream().collect(
        Collectors.toMap(
            person -> String.join(" ", person.getFirstName(), person.getLastName()),
            person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears()
        ));
  }

  public Map<Boolean, List<String>> partitionPeopleByAgeGroupAbove18(List<Person> people) {
    // Assumption: no field of model is null
    return people.stream().collect(Collectors.partitioningBy(
        person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 18,
        Collectors.mapping(
            person -> String.join(" ", person.getFirstName(), person.getLastName()),
            Collectors.toList())));
  }
}
