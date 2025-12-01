package AOC2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleLoader {
  private final String filePath;
  private final List<String> lines = new ArrayList<>();

  public PuzzleLoader(String filePath){
    this.filePath = filePath;
    this.load();
  }

  public void load(){
    if (!lines.isEmpty()) return;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) lines.add(line);
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }

  public <T> T parse(Parser<T> parser){
    return parser.parse(lines);
  }
}
