package AOC2025.DAY2;

import AOC2025.PuzzleLoader;
import utility.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle1 {
  public static List<long[]> loadPuzzle(String filePath){
    PuzzleLoader puzzleLoader = new PuzzleLoader(filePath);
    return puzzleLoader.parse((lines)->{
      String line = lines.getFirst();
      String[] ranges = line.split(",");

      List<long[]> result = new ArrayList<>();

      Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");

      for (String range : ranges){
        Matcher m = pattern.matcher(range);
        if (!m.matches()) throw new IllegalArgumentException("Invalid range: " + range);
        long start = Long.parseLong(m.group(1));
        long end = Long.parseLong(m.group(2));
        result.add(new long[]{ start, end });
      }

      return result;
    });    
  }

  private static boolean isValidRange(long[] range){
    int startDigitCount = (int) Math.log10(range[0]) + 1;
    int endDigitCount = (int) Math.log10(range[1]) + 1;
    if (endDigitCount - startDigitCount > 1) return true;
    return (startDigitCount % 2 == 1) && (endDigitCount % 2 == 1);
  }

  private static long _start(long start){
    int startDigitCount = (int) Math.log10(start) + 1;
    if (startDigitCount % 2 == 0) return start;
    else return (long) Math.pow(10, startDigitCount + 1);
  }

  private static long _end(long end){
    int endDigitCount = (int) Math.log10(end) + 1;
    if (endDigitCount % 2 == 0) return end;
    else return (long) Math.pow(10, endDigitCount - 1) - 1;
  }

  public static long getInvalidIdsInRange(long[] range){
    if (isValidRange(range)) return 0;
    long start = _start(range[0]), end = _end(range[1]);
    if (start > end) return 0;
    long startDigitCount = (int) Math.log10(start) + 1, endDigitCount = (int) Math.log10(end) + 1;
    


    return 0;
  }


  public static void main(String[] args) {
    List<long[]> input =  loadPuzzle("src/files/input2.txt");

    for (long[] range: input){
      if (isValidRange(range)) Logger.log().println("Valid range: " + range[0] + " - " + range[1]);
      else {
        long start = _start(range[0]), end = _end(range[1]);
        Logger.log().println(start + " - " + end);
      }
    }

  }
}
