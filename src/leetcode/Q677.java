package leetcode;

import data_structures.Trie;

public class Q677 {
  static class MapSum {
    private static final int CHILDREN_LIMIT = 26;

    private static class TrieNode {
      TrieNode[] children;
      boolean EOW;
      int val;

      TrieNode(){
        children = new TrieNode[CHILDREN_LIMIT];
      }
    }

    private final TrieNode root;

    public MapSum() {
      root = new TrieNode();
    }

    public void insert(String word, int val){
      TrieNode iter = root;
      for (int i = 0; i < word.length(); i++){
        int idx = word.charAt(i) - 'a';
        if (iter.children[idx] == null)
          iter.children[idx] = new TrieNode();
        if (i == word.length() - 1){
          iter.children[idx].EOW = true;
          iter.children[idx].val = val;
        }
        iter = iter.children[idx];
      }
    }

    public int sum(String prefix) {
      int sum = 0;
      TrieNode iter = root;
      for (int i = 0; i < prefix.length(); i++){
        int idx = prefix.charAt(i) - 'a';
        if (iter.children[idx] == null) return 0;
        iter = iter.children[idx];
      }
      return getSum(iter);
    }

    private int getSum(TrieNode node){
      if (node == null) return 0;
      int sum = node.EOW ? node.val : 0;
      for (TrieNode child: node.children){
        sum += getSum(child);
      }
      return sum;
    }
  }
}
