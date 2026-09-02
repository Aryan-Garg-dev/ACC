package utility.env;

import utility.Logger;
import utility.Parser;
import utility.Validation;
import utility.exceptions.ValidationException;


public class EnvVar<T> {
  private T value;
  private T defaultValue;

  Validation<T> nullValidation = Validation.custom((value) -> {
    if (value == null && defaultValue == null) throw new ValidationException("Value cannot be null");
  });

  private Parser<T> parser;
  private Validation<T> validation = nullValidation;


  public EnvVar(Parser<T> parser, T defaultValue, Validation<T> validation) {
    this.validation = nullValidation.and(validation);
    this.defaultValue = defaultValue;
    this.parser = parser;
  }

  public EnvVar(Parser<T> parser, T defaultValue) {
    this.parser = parser;
    this.defaultValue = defaultValue;
  }

  public EnvVar(Parser<T> parser) {
    this.parser = parser;
  }

  public EnvVar(Parser<T> parser, Validation<T> validation) {
    this.parser = parser;
    this.validation = nullValidation.and(validation);
  }

  public T getValue() {
    return value;
  }

  public void parseValue(String input) throws ValidationException {
    try {
      this.value = this.parser.parse(input);
    } catch (Exception e) {
      if (defaultValue != null) this.value = defaultValue;
      else throw new ValidationException("Invalid type");
    }
  }

  public void validateValue() throws ValidationException {
    this.validation.validate(this.value);
  }

  @Override
  public String toString() {
    return Logger.stringify(value == null ? defaultValue : value);
  }
}
