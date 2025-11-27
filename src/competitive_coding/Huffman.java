package competitive_coding;

import utility.Console;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
  private static class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
      this.ch = ch;
      this.freq = freq;
    }

    Node(int freq, Node left, Node right){
      this.freq = freq;
      this.left = left;
      this.right = right;
    }
  }

  private Node root;
  private final Map<Character, Integer> freq = new HashMap<>();
  private final Map<Character, String> charCode = new HashMap<>();
  private final Map<String, Character> codeChar = new HashMap<>();

  public Huffman(String str){
    for (char ch: str.toCharArray()) freq.put(ch, freq.getOrDefault(ch, 0) + 1);
    buildTree();
  }

  private void buildTree(){
    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));
    for (char ch: freq.keySet()) pq.add(new Node(ch, freq.get(ch)));
    while (pq.size() > 1){
      Node left = pq.poll();
      Node right = pq.poll();
      Node p = new Node(left.freq + right.freq, left, right);
      pq.offer(p);
    }
    this.root = pq.poll();
  }

  private String encodeChar(char ch, Node root, StringBuilder sb){
    if (root == null) return null;
    if (root.left == null && root.right == null && root.ch == ch) return sb.toString();

    sb.append('0');
    String left = encodeChar(ch, root.left, sb);
    sb.setLength(sb.length() - 1);
    if (left != null) return left;

    sb.append('1');
    String right = encodeChar(ch, root.right, sb);
    sb.setLength(sb.length() - 1);
    return right;

  }

  public String encodeString(String string){
    StringBuilder sb = new StringBuilder();
    for (char ch: string.toCharArray()){
      if (!charCode.containsKey(ch)) {
        String encoded = encodeChar(ch, root, new StringBuilder());
        charCode.put(ch, encoded);
        codeChar.put(encoded, ch);
      }
      sb.append(charCode.get(ch));
    }
    return sb.toString();
  }

  public String decodeString(String string){
    StringBuilder sb = new StringBuilder();
    StringBuilder temp = new StringBuilder();
    for (char ch: string.toCharArray()){
      temp.append(ch);
      String key = temp.toString();
      if (codeChar.containsKey(key)){
        sb.append(codeChar.get(key));
        temp.setLength(0);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Huffman huff = new Huffman("AryanGarg");
    String encoded = huff.encodeString("GAry");
    String decoded = huff.decodeString(encoded);
    Console.log()
      .print("Encoded: ").println(encoded)
      .print("Decoded: ").println(decoded);
  }
}
