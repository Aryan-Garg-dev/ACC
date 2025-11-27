package leetcode;

import utility.Console;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Q211 {
  static class WordDictionary {

    private static class Trie {
      private static final int CHILDREN_LIMIT = 26;

      private static class Node {
        Node[] children;
        boolean EOW;

        Node(){
          children = new Node[CHILDREN_LIMIT];
        }
      }

      private final Node root;

      public Trie(){
        root = new Node();
      }

      public void insert(String word){
        Node iter = root;
        for (int i = 0; i < word.length(); i++){
          int idx = word.charAt(i) - 'a';
          if (iter.children[idx] == null) iter.children[idx] = new Node();
          if (i == word.length() - 1) iter.children[idx].EOW = true;
          iter = iter.children[idx];
        }
      }

      public boolean search(String word){
       return searchHelper(word, 0, root);
      }

      public boolean searchHelper(String word, int index, Node node){
        if (node == null) return false;
        if (index == word.length()) return node.EOW;
        char ch = word.charAt(index);
        if (ch == '.'){
          for (Node child: node.children){
            if (searchHelper(word, index + 1, child)){
              return true;
            }
          }
          return false;
        } else {
          int idx = ch - 'a';
          return searchHelper(word, index + 1, node.children[idx]);
        }
      }
    }

    private final Trie trie;

    public WordDictionary() {
      trie = new Trie();
    }

    public void addWord(String word) {
      trie.insert(word);
    }

    public boolean search(String word) {
      return trie.search(word);
    }
  }

}
