package functional.vavr.concepts;

import io.vavr.control.Try;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * https://docs.vavr.io/
 */
public class FunctionalExceptionHandling {

  private static final Logger logger = LoggerFactory.getLogger(FunctionalExceptionHandling.class);

  /*`
   * Side Effects, exception is not thrown
   * Interesting Read: http://wiki.c2.com/?DontUseExceptionsForFlowControl
   */
  public Try<Double> divide(double dividend, double divisor) {
    return Try.of(() -> dividend / divisor)
        .onSuccess(
            success -> logger.info("Result calculated successfully and result is: {}", success))
        .onFailure(error -> logger.error("Error occurred {} ", error.getMessage()));
  }

  public Try<URL> validateURL(String url) {
    return Try.of(() -> new URL(url))
        .onSuccess(success -> logger.info("URL {} parsed successfully", url))
        .onFailure(error -> logger.error("Error occurred {} ", error.getMessage()));
  }
}
