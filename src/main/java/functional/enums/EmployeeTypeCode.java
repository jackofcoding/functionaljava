package functional.enums;

public enum EmployeeTypeCode {
  C_SUITE("CSE", 1),
  DIRECTOR("DIR", 3),
  ANALYST("AYT", 9),
  DEVELOPER("DEV", 7),
  SUBJECT_MATTER_EXPERT("SME", 6),
  TESTER("QAT", 8),
  SCRUM_MASTER("SM", 11),
  RELEASE_TRAIN_ENGINEER("RTE", 4);

  private final String employeeCode;
  private final Integer rank;

  EmployeeTypeCode(String employeeCode, Integer rank) {
    this.employeeCode = employeeCode;
    this.rank = rank;
  }

  public Integer getRank() {
    return this.rank;
  }
}
