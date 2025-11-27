package competitive_coding;

public class Move {
  private final char ch;

  public Move(char ch){
    this.ch = ch;
  }

  public String toFront(String string){
    char[] str = string.toCharArray();

     int i = str.length - 1;
     for (int j = i; j >= 0; j--) if (str[j] != ch) str[i--] = str[j];
     while (i >= 0) str[i--] = ch;

     return String.valueOf(str);
  }
}
