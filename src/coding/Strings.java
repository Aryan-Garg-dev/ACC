package coding;

import utility.Input;

public class Strings {
  public static class Urlify {
    public static String replaceSpaces(char[] str, int trueLength){
      int spaceCount = 0, index, i = 0;
      for (i = 0; i < trueLength; i++){
        if (str[i] == ' ') spaceCount++;
      }
      index = trueLength + spaceCount * 2;
      return "";
    }
  }

  public static void main(String...args){

  }
}
