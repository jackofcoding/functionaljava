package functional.utils;

import functional.model.Person;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class SampleDataProvider {

  public static List<Person> getPeople() {
    return List.of(
        Person.builder().withFirstName("Jane").withLastName("Doe").withDateOfBirth(LocalDate.of(1992, Month.FEBRUARY, 1)).build(),
        Person.builder().withFirstName("Jason").withLastName("Smith").withDateOfBirth(LocalDate.of(2000, Month.DECEMBER, 21)).build(),
        Person.builder().withFirstName("Anton").withLastName("Jackson").withDateOfBirth(LocalDate.of(1990, Month.MARCH, 21)).build(),
        Person.builder().withFirstName("Arthur").withLastName("Flintstone").withDateOfBirth(LocalDate.of(1996, Month.JULY, 6)).build(),
        Person.builder().withFirstName("Mary").withLastName("Stewart").withDateOfBirth(LocalDate.of(2004, Month.JUNE, 4)).build(),
        Person.builder().withFirstName("Kevin").withLastName("Malone").withDateOfBirth(LocalDate.of(1989, Month.SEPTEMBER, 11)).build(),
        Person.builder().withFirstName("Mary").withLastName("Jane").withDateOfBirth(LocalDate.of(2005, Month.OCTOBER, 15)).build(),
        Person.builder().withFirstName("Prince").withLastName("Ray").withDateOfBirth(LocalDate.of(2009, Month.NOVEMBER, 25)).build(),
        Person.builder().withFirstName("Jim").withLastName("Patrick").withDateOfBirth(LocalDate.of(1991, Month.MAY, 17)).build(),
        Person.builder().withFirstName("Lori").withLastName("Jamieson").withDateOfBirth(LocalDate.of(2003, Month.MARCH, 13)).build(),
        Person.builder().withFirstName("Samuel").withLastName("Jackson").withDateOfBirth(LocalDate.of(2007, Month.OCTOBER, 3)).build()
    );
  }
}
