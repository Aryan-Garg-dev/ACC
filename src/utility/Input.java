package utility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
  private final Scanner sc;
  private final InputStream source;

  public Input(){
    this.source = System.in;
    sc = new Scanner(source);
  }

  public Input(InputStream source){
    this.source = source;
    sc = new Scanner(this.source);
  }

  public int readInt(){
    int input = sc.nextInt();
    sc.nextLine(); // consumes newline left by nextInt
    return input;
  }

  public String readLine(){
    return sc.nextLine();

  }


  public int[] readIntArray(int size){
    int[] arr = new int[size];
    for (int i = 0; i < size; i++){
      arr[i] = sc.nextInt();
    }
    sc.nextLine();
    return arr;
  }

  public List<Integer> readIntArray(){
    if (!sc.hasNextLine()) return List.of();
    String line = sc.nextLine().trim();
    if (line.isEmpty()) return List.of();
    return Arrays.stream(line.split("\\s+"))
      .map(Integer::parseInt)
      .toList();
  }

  public String[] readWordArray(int size){
    String[] arr = new String[size];
    for (int i = 0; i < size; i++){
      arr[i] = sc.next();
    }
    return arr;
  }

  public List<String> readWordArray(){
    List<String> arr = new ArrayList<>();
    if (!sc.hasNextLine()) return List.of();
    return Arrays.asList(sc.nextLine().trim().split("\\s+"));
  }

  public String[] readLineArray(int size){
    String[] arr = new String[size];
    for (int i = 0; i < size; i++){
      arr[i] = sc.nextLine();
    }
    return arr;
  }

  public char[] readCharArray(){
    return this.readLine().toCharArray();
  }

  public Input prompt(String message) {
    System.out.print(message);
    return this;
  }

  public void close() {
    sc.close();
  }
}
