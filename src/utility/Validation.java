package utility;


import utility.exceptions.ValidationException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Validation<T> {

  private final Validator<T> validator;

  private Validation(Validator<T> validator) {
    this.validator = Objects.requireNonNull(validator);
  }

  public static <T> Validation<T> custom(Validator<T> validator) {
    return new Validation<>(validator);
  }

  public Validation<T> and(Validation<T> other) {
    return Validation.custom(value -> {
      this.validate(value);
      other.validate(value);
    });
  }

  public static Validation<String> regex(Pattern pattern, String errorMessage) {
    Objects.requireNonNull(pattern);
    Objects.requireNonNull(errorMessage);
    return custom(regexValidator(pattern, errorMessage));
  }

  public static Validation<String> pattern(Pattern pattern) {
    Objects.requireNonNull(pattern);
    return custom(regexValidator(pattern, "Invalid value"));
  }

  public static Validation<String> regex(String regex) {
    Objects.requireNonNull(regex);
    return custom(regexValidator(Pattern.compile(regex), "Invalid value"));
  }

  private static Validator<String> regexValidator(
    Pattern pattern,
    String errorMessage
  ) {
    return value -> {
      if (value == null || !pattern.matcher(value).matches()) {
        throw new ValidationException(errorMessage);
      }
    };
  }

  public void validate(T value) throws ValidationException {
    validator.validate(value);
  }
}
