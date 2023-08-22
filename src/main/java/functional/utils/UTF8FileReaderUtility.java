package functional.utils;

import io.vavr.control.Try;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UTF8FileReaderUtility {

  public static List<String> getFileContentsAsSentencesFromResources(String directory, String fileName) {
    Objects.requireNonNull(directory, "directory cannot be null");
    Objects.requireNonNull(fileName, "fileName cannot be null");
    return Try.of(
            () ->
                Files.readAllLines(Path.of(directory, fileName))
                    .stream()
                    .flatMap(lines -> Arrays.stream(lines.split("[.]")))
                    .map(String::trim)
                    .toList())
        .onFailure(ex -> log.error("Exception occurred {0}", ex))
        .getOrElseGet(e -> Collections.emptyList());
  }
}
