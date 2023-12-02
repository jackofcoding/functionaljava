package functional.vavr.concepts;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import io.vavr.control.Try;
import java.net.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FunctionalExceptionHandlingTest {

  private FunctionalExceptionHandling classUnderTest;

  @BeforeEach
  public void setUp() {
    classUnderTest = new FunctionalExceptionHandling();
  }

  @Test
  public void test_divide_should_not_throw_exception() {
    assertThatCode(() -> classUnderTest.divide(2, 0)).doesNotThrowAnyException();
  }

  @Test
  public void test_divide_should_return_valid_result() {
    assertThat(classUnderTest.divide(5, 2).get()).isEqualTo(2.5);
  }

  @Test
  public void test_validateURL_should_not_throw_exception_when_url_is_empty() {
    assertThatCode(() -> classUnderTest.validateURL("")).doesNotThrowAnyException();
  }

  @Test
  public void test_validateURL_should_not_throw_exception_when_url_is_invalid() {
    assertThatCode(() -> classUnderTest.validateURL("www.google.com:")).doesNotThrowAnyException();

    assertThatCode(() -> classUnderTest.validateURL("//:www.google.com/path"))
        .doesNotThrowAnyException();
  }

  @Test
  public void test_validateURL_should_return_valid_url_when_input_is_valid() {
    assertThat(classUnderTest.validateURL("http://www.google.com").get())
        .isInstanceOf(URL.class)
        .isEqualTo(Try.of(() -> new URL("http://www.google.com")).get());
  }
}
