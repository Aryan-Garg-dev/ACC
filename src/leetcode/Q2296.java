package leetcode;

import utility.Console;

public class Q2296 {
  static class TextEditor {
    StringBuilder sb;
    int cursorIdx;

    public TextEditor() {
      sb = new StringBuilder();
      cursorIdx = 0;
    }

    public void addText(String text) {
      sb.insert(cursorIdx, text);
      cursorIdx += text.length();
    }

    public int deleteText(int k) {
      int newIdx = Math.max(cursorIdx - k, 0);
      int actualLength = cursorIdx - newIdx;
      sb.delete(newIdx, cursorIdx);
      cursorIdx = newIdx;
      return actualLength;
    }

    public String cursorLeft(int k) {
      cursorIdx = Math.max(cursorIdx - k, 0);
      return sb.substring(cursorIdx - Math.min(10, cursorIdx), cursorIdx);
    }

    public String cursorRight(int k) {
      cursorIdx = Math.min(cursorIdx + k, sb.length());
      return sb.substring(cursorIdx - Math.min(10, cursorIdx), cursorIdx);
    }
  }

  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    editor.addText("leetcode");
    Console.log().println(editor.deleteText(4));
    editor.addText("practice");
    Console.log()
      .println(editor.cursorRight(3))
      .println(editor.cursorLeft(8))
      .println(editor.deleteText(10))
      .println(editor.cursorLeft(2))
      .println(editor.cursorRight(6));
  }
}
