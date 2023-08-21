package functional.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(setterPrefix = "with")
@Getter
@ToString
public class Person {

  private String firstName, lastName;
  LocalDate dateOfBirth;

}
