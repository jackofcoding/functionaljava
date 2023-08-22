package functional.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.Test;

class UTF8FileReaderUtilityTest {

  @Test
  void
      test_get_file_contents_as_sentences_from_resources_should_return_file_content_as_sentences_when_file_is_valid() {
    assertThat(
            UTF8FileReaderUtility.getFileContentsAsSentencesFromResources(
                "src/test/resources", "test_file.txt"))
        .asList()
        .hasSize(2)
        .hasSameElementsAs(List.of("This is a test file", "This contains sentences in one line"));
  }

  @Test
  void
      test_get_file_contents_as_sentences_from_resources_should_not_throw_exception_when_file_is_invalid() {
    assertThatCode(
            () ->
                UTF8FileReaderUtility.getFileContentsAsSentencesFromResources(
                    "src/test/resources", "invalid.txt"))
        .doesNotThrowAnyException();
  }
}
