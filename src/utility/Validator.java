package utility;

import utility.exceptions.ValidationException;

@FunctionalInterface
public interface Validator<T> {
  void validate(T value) throws ValidationException;
}
