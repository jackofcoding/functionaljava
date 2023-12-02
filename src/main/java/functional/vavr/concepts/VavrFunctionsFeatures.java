package functional.vavr.concepts;

import io.vavr.Function1;
import io.vavr.control.Option;
import java.util.List;
import java.util.Objects;

public class VavrFunctionsFeatures {

  /*
  * Composition Function example
  */
  public <T, U, R> List<R> compose(List<T> items, Function1<T, U> firstOps, Function1<U, R> secondOps) {
    Objects.requireNonNull(items, "items cannot be null");
    Objects.requireNonNull(firstOps, "firstOps cannot be null");
    Objects.requireNonNull(secondOps, "secondOps cannot be null");
    return items.stream().map(e -> secondOps.compose(firstOps).apply(e))
          .toList();
  }

  /*
  * Lifting Function example
  */
  public <T, U> List<Option<U>> lift(List<T> items, Function1<T, U> liftOperation) {
    Objects.requireNonNull(items, "items cannot be null");
    Objects.requireNonNull(liftOperation, "liftOperation cannot be null");
    Function1<T, Option<U>> liftedOps = Function1.lift(liftOperation);
    return items.stream().map(liftedOps).toList();
  }

}
