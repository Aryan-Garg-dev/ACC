package utility.env;

import utility.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Dotenv {
  private static Map<String, EnvVar<?>> _env = null;

  public static void config(Map<String, EnvVar<?>> env){
    if (_env != null) throw new RuntimeException("Dotenv already initialized");
    _env = env;
  }


  @SafeVarargs
  public static void config(Pair<String, EnvVar<?>>...envVars){
    if (_env != null) throw new RuntimeException("Dotenv already initialized");
    _env = new HashMap<>();
    for (Pair<String, EnvVar<?>> e: envVars) _env.put(e.first, e.second);
  }

  private static Map<String, String> _loadDotEnv() {
    try {
      Path envPath = Files.walk(Paths.get("").toAbsolutePath())
        .filter(p -> p.getFileName().toString().equals(".env"))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(".env file not found"));

      return Files.readAllLines(envPath).stream()
        .map(line -> line.trim().split("="))
        .collect(HashMap::new, (m, v) -> m.put(v[0], v.length == 1 ? "" : v[1]), HashMap::putAll);
    } catch (Exception e) {
      System.out.println("Error loading .env file: " + e.getMessage());
      return null;
    }
  }

  public static Dotenv load(){
    Map<String, String> env = _loadDotEnv();
    if (env == null) throw new RuntimeException("Error loading .env file");
    if (_env == null) throw new RuntimeException("Dotenv not initialized");
    for (var e: _env.entrySet()){
      if (!env.containsKey(e.getKey())) throw new RuntimeException("Missing env var: " + e.getKey());
      String value = env.get(e.getKey());
      try {
        e.getValue().parseValue(value.trim());
        e.getValue().validateValue();
      } catch (Exception ex) {
        throw new RuntimeException("Invalid env var: " + e.getKey());
      }
    }
    return new Dotenv();
  }

  public <T> T get(String key, Class<T> type) {
    Object value = _env.get(key).getValue();
    return type.cast(value);
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
     for (var e: _env.entrySet()) sb.append(e.getKey()).append("=").append(e.getValue()).append("\n");
     sb.setLength(sb.length() - 1);
     return sb.toString();
  }
}
