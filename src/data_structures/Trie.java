package data_structures;

import utility.Console;

public class Trie {
  public static final int CHILDREN_LIMIT = 26;

  public static class Node {
    Node[] children;
    boolean EOW;

    Node(){
      children = new Node[CHILDREN_LIMIT];
    }
  }

  public final Node root;

  public Trie(){
    root = new Node();
  }

  public Trie(String[] words){
    root = new Node();
    this.insert(words);
  }

  void insert(String word){
    Node iter = root;
    for (int i = 0; i < word.length(); i++){
      int idx = word.charAt(i) - 'a';
      if (iter.children[idx] == null)
        iter.children[idx] = new Node();
      if (i == word.length() - 1)
        iter.children[idx].EOW = true;
      iter = iter.children[idx];
    }
  }

  void insert(String[] words){
    for (String word: words) this.insert(word);
  }

  boolean contains(String word){
    Node iter = root;
    for (int i = 0; i < word.length(); i++){
      int idx = word.charAt(i) - 'a';
      if (iter.children[idx] == null) return false;
      if (i == word.length() - 1 && !iter.children[idx].EOW) return false;
      iter = iter.children[idx];
    }
    return true;
  }

  boolean containsPrefix(String prefix){
    Node iter = root;
    for (int i = 0; i < prefix.length(); i++){
      int idx = prefix.charAt(i) - 'a';
      if (iter.children[idx] == null) return false;
      iter = iter.children[idx];
    }
    return true;
  }

  int countUniquePrefixes(){
    return __countNodes(root);
  }

  private static int __countNodes(Node root){
    int count = 0;
    for (int i = 0; i < CHILDREN_LIMIT; i++){
      if (root.children[i] != null)
        count += __countNodes(root.children[i]);
    }
    return count + 1;
  }
}

class Practice {
  static class WordBreakProblem {
    public static boolean wordBreak(String[] words, String key){
      Trie trie = new Trie();
      for (String word: words) trie.insert(word);
      return helper(key, trie);
    }

    private static boolean helper(String key, Trie trie){
      if (key.isEmpty()) return true;
      for (int i = 1; i <= key.length(); i++){
        String first = key.substring(0, i);
        String second = key.substring(i);
        if (trie.contains(first) && helper(second, trie)) return true;
      }
      return false;
    }

    public static void main(String[] args) {
      String[] words = { "i", "like", "sam", "samsung", "mobile", "ice" };
      String[] key = {"ilikesamsung", "ilikesung"};
      Console.log()
        .println(wordBreak(words, key[0]))
        .println(wordBreak(words, key[1]));
    }
  }

  static class StartsWithProblem {
    public static boolean startsWith(String[] words, String prefix){
      Trie trie = new Trie(words);
      return trie.containsPrefix(prefix);
    }

    public static void main(String[] args) {
      String[] words = { "apple", "app", "mango", "man", "woman" };
      String[] prefix = { "app", "moon" };

      Console.log()
        .println(startsWith(words, prefix[0]))
        .println(startsWith(words, prefix[1]));
    }
  }

  static class CountUniqueProblem {
    public static int countUniqueSubstrings(String word){
      String suffix;
      Trie trie = new Trie();
      for (int i = 0; i < word.length(); i++){
        suffix = word.substring(i);
        trie.insert(suffix);
      }
      return trie.countUniquePrefixes();
    }

    public static void main(String[] args) {
      Console.log()
        .println(countUniqueSubstrings("ababa"))
        .println(countUniqueSubstrings("apple"));
    }
  }

  static class LongestWordWithAllPrefixes {
    private static String longest = "";

    private static void helper(Trie.Node root, StringBuilder temp){
      if (root == null) return;
      for (int i = 0; i < Trie.CHILDREN_LIMIT; i++){
        if (root.children[i] != null && root.children[i].EOW){
          temp.append((char)(i + 'a'));
          if (temp.length() > longest.length()) longest = temp.toString();
          helper(root.children[i], temp);
          temp.deleteCharAt(temp.length() - 1);
        }
      }
    }
    
    public static String getLongestWordWithAllPrefixes(String[] words){
      Trie trie = new Trie(words);
      StringBuilder temp = new StringBuilder();
      helper(trie.root, temp);
      return longest;
    }

    public static void main(String[] args) {
      String[] words = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
      Console.log()
        .println(getLongestWordWithAllPrefixes(words));
    }
  }

  public static void main(String[] args) {
//    WordBreakProblem.main(args);
//    StartsWithProblem.main(args);
//    CountUniqueProblem.main(args);
    LongestWordWithAllPrefixes.main(args);

  }
}
