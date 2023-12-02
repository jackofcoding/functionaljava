package functional.vavr.concepts;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import io.vavr.Function1;
import io.vavr.control.Option;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VavrFunctionsFeaturesTest {

  private VavrFunctionsFeatures vavrFunctionsFeatures;

  @BeforeEach
  void setUp() {
    vavrFunctionsFeatures = new VavrFunctionsFeatures();
  }

  @Test
  void test_compose_items_must_not_be_null() {
    Function1<String, Integer> f1 = Integer::parseInt;
    Function1<Integer, Integer> f2 = (a) -> a * 10;
    assertThatThrownBy(() -> vavrFunctionsFeatures.compose(null, f1, f2))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("items cannot be null");
  }

  @Test
  void test_compose_first_ops_must_not_be_null() {
    Function1<Integer, Integer> f2 = (a) -> a * 10;
    assertThatThrownBy(() -> vavrFunctionsFeatures.compose(List.of("1", "2", "5", "100"), null, f2))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("firstOps cannot be null");
  }

  @Test
  void test_compose_second_ops_must_not_be_null() {
    Function1<String, Integer> f1 = Integer::parseInt;
    assertThatThrownBy(() -> vavrFunctionsFeatures.compose(List.of("1", "2", "5", "100"), f1, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("secondOps cannot be null");
  }

  @Test
  void test_compose_when_string_integers_passed_must_return_10_times_value() {
    Function1<String, Integer> f1 = Integer::parseInt;
    Function1<Integer, Integer> f2 = (a) -> a * 10;
    List<Integer> composed = vavrFunctionsFeatures.compose(List.of("1", "2", "5", "100"), f1, f2);
    assertThat(composed).isEqualTo(List.of(10, 20, 50, 1000));
  }

  @Test
  void test_lift_when_integers_in_string_form_passed_must_return_parsed_integer_else_none() {
    Function1<String, Integer> f1 = Integer::parseInt;
    List<Option<Integer>> integers =
        vavrFunctionsFeatures.lift(List.of("1", "1g", "5", "1e00"), f1);
    assertThat(integers)
        .isEqualTo(List.of(Option.of(1), Option.none(), Option.of(5), Option.none()));
  }

  @Test
  void test_lift_items_must_not_be_null() {
    Function1<String, Integer> f1 = Integer::parseInt;
    assertThatThrownBy(() -> vavrFunctionsFeatures.lift(null, f1))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("items cannot be null");
  }

  @Test
  void test_lift_lift_operation_must_not_be_null() {
    assertThatThrownBy(() -> vavrFunctionsFeatures.lift(List.of("1", "2", "5", "100"), null))
        .isInstanceOf(NullPointerException.class)
        .hasMessage("liftOperation cannot be null");
  }
}