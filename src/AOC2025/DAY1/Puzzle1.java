package AOC2025.DAY1;

import AOC2025.PuzzleLoader;
import AOC2025.Timer;
import utility.Console;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle1 {

  public record Move(char direction, int steps) {}

  public static List<Move> loadPuzzle(String filePath){
    PuzzleLoader puzzleLoader = new PuzzleLoader(filePath);
    return puzzleLoader.parse((lines)->{
      Pattern pattern = Pattern.compile("([LR])(\\d+)");
      List<Move> moves = new ArrayList<>();

      for (String line : lines) {
        Matcher m = pattern.matcher(line.trim());
        if (m.matches())
          moves.add(new Move(m.group(1).charAt(0), Integer.parseInt(m.group(2))));
      }

      return moves;
    });
  }

  public static int password(int start, List<Move> moves){
    int count = 0;
    for (Move move : moves) {
      if (move.direction == 'L') start = (100 + start - move.steps) % 100;
      else if (move.direction == 'R') start = (start + move.steps) % 100;
      if (start == 0) count++;
    }
    return count;
  }

  public static int password2(int start, List<Move> moves) {
    int count = 0;
    for (Move move : moves) {
      count += move.steps / 100;
      int steps = move.steps % 100;
      if (move.direction == 'L') {
        if (start > 0 && start - steps <= 0) count++;
        start = (100 + start - steps) % 100;
      } else if (move.direction == 'R') {
        if (start + steps >= 100) count++;
        start = (start + steps) % 100;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    List<Move> input = loadPuzzle("src/files/input1.txt");
    Timer.measure(()->{
      Console.log()
        .println(password(50, input))
        .println(password2(50, input));
    });
  }
}
