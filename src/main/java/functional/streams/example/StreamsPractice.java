package functional.streams.example;

import functional.enums.EmployeeTypeCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsPractice {

  public int sumOfIntegers(List<Integer> integers) {
    Objects.requireNonNull(integers, "list must not be null");
    return integers.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
  }

  public Double averageOfIntegers(List<Integer> integers) {
    Objects.requireNonNull(integers, "list must not be null");
    return integers.stream()
        .filter(Objects::nonNull)
        .mapToInt(Integer::valueOf)
        .average()
        .orElse(0.0);
  }

  public Boolean isAllMultiplesOfFive(List<Integer> integers) {
    Objects.requireNonNull(integers, "list must not be null");
    return integers.stream()
        .filter(Objects::nonNull)
        .allMatch(e -> Integer.remainderUnsigned(e, 5) == 0);
  }

  public Boolean checkIfOddElementsArePresent(List<Integer> integers) {
    Objects.requireNonNull(integers, "list must not be null");
    return integers.stream()
        .filter(Objects::nonNull)
        .anyMatch(e -> Integer.remainderUnsigned(e, 2) > 0);
  }

  public List<Integer> doubleElements(List<Integer> integers) {
    Objects.requireNonNull(integers, "list must not be null");
    return integers.stream()
        .filter(Objects::nonNull)
        .map(e -> e * 2)
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  public List<Integer> multiplyElementsWithCollector(List<Integer> integers, int multiple) {
    // TODO: Range problem
    Objects.requireNonNull(integers, "list must not be null");
    return integers.stream().map(e -> e * multiple).collect(Collectors.toList());
  }

  public long countElementsThatMatchCriteria(List<Integer> integers, Predicate<Integer> criteria) {
    Objects.requireNonNull(integers, "list must not be null");
    Objects.requireNonNull(criteria, "criteria must not be null");
    return integers.stream().filter(Objects::nonNull).filter(criteria).count();
  }

  public List<String> sortTwoList(List<String> words1, List<String> words2) {
    Objects.requireNonNull(words1, "first list must not be null");
    Objects.requireNonNull(words2, "second list must not be null");
    Stream<String> words = Stream.concat(words1.stream(), words2.stream());
    return words.filter(Objects::nonNull).sorted().toList();
  }

  public Boolean checkIfUniqueElementsOnly(List<String> words) {
    Objects.requireNonNull(words, "words must not be null");
    // TODO: Find a better way
    return words.stream()
        .filter(Objects::nonNull)
        .dropWhile(e -> Collections.frequency(words, e) == 1)
        .toList()
        .isEmpty();
  }

  public List<String> getUniqueWordsFromSentences(List<String> lines) {
    Objects.requireNonNull(lines, "lines must not be null");
    return lines.stream()
        .filter(Objects::nonNull)
        .flatMap(e -> Arrays.stream(e.split("\\s+")))
        .distinct()
        .toList();
  }

  public int min(List<Integer> integers) {
    Objects.requireNonNull(integers, "integers must not be null");
    return integers.stream()
        .min((Integer::compare))
        .orElseThrow(() -> new NoSuchElementException("No Min element found"));
  }

  public int max(List<Integer> integers) {
    Objects.requireNonNull(integers, "integers must not be null");
    return integers.stream()
        .max((Integer::compare))
        .orElseThrow(() -> new NoSuchElementException("No Max element found"));
  }

  public List<Integer> randomIntegersSupplier(long limit) {
    return Stream.generate(Random::new).mapToInt(Random::nextInt).limit(limit).boxed().toList();
  }

  public String[] transformIntegersToString(List<Integer> integers) {
    Objects.requireNonNull(integers, "integers must not be null");
    return integers.stream().map(Object::toString).toArray(String[]::new);
  }

  public List<Integer> generateFirstNFibonacciNumbers(long limit) {
    return Stream.iterate(new int[] {0, 1}, n -> new int[] {n[1], n[0] + n[1]})
        .limit(limit)
        .map(n -> n[0])
        .toList();
  }

  public List<String> convertAnyCaseToTitleCase(List<String> words) {
    Objects.requireNonNull(words, "words must not be null");
    return words.stream()
        .filter(Objects::nonNull)
        .filter(
            word -> word.length() > 0 && !word.isBlank() && Character.isAlphabetic(word.charAt(0)))
        .map(word -> Character.toTitleCase(word.charAt(0)) + word.substring(1).toLowerCase())
        .toList();
  }

  public List<EmployeeTypeCode> getEmployeeTypeCodeWithRankAbove(Integer rankLimit) {
    return Arrays.stream(EmployeeTypeCode.values())
        .filter(e -> rankLimit > 0)
        .filter(e -> e.getRank() < rankLimit)
        .toList();
  }

  public List<String> suffixWord(List<String> words, String suffix) {
    Objects.requireNonNull(words, "words must not be null");
    return words.stream()
        .filter(
            word ->
                Objects.nonNull(word)
                    && Objects.nonNull(suffix)
                    && !word.isBlank()
                    && !suffix.isBlank())
        .map(word -> String.join(" ", word, suffix))
        .toList();
  }

  // Valid Phone Number Format : ddd-ddd-dddd
  public List<String> getValidPhoneNumbersFromFile(List<String> sentences) {
    return sentences.stream()
        .flatMap(lines -> Arrays.stream(lines.split("\\s+")))
        .filter(word -> word.matches(".*\\d{3}-\\d{3}-\\d{4}.*"))
        .map(
            word -> {
              String onlyDigits = word.replaceAll("\\D", "");
              return onlyDigits.substring(onlyDigits.length() - 10);
            })
        .toList();
  }
}
