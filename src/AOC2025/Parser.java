package AOC2025;

import java.util.List;

@FunctionalInterface
public interface Parser<T> {
  T parse(List<String> lines);
}
