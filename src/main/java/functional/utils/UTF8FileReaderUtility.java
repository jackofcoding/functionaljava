package functional.utils;

import io.vavr.control.Try;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UTF8FileReaderUtility {

  private static final Logger logger =  LoggerFactory.getLogger(UTF8FileReaderUtility.class);

  public static List<String> getFileContentsAsSentencesFromResources(String fileName) {
    return Try.of(() -> Files.readAllLines(Path.of("src/main/resources", fileName))
            .stream().flatMap( lines -> Arrays.stream(lines.split("[.]"))).toList())
        .onFailure(ex -> logger.error("Exception occurred {0}", ex))
        .getOrElseGet(e -> Collections.emptyList());
  }

}
