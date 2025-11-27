package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q460 {
  static class LFUCache {
    private static class Node {
      int key, value, freq;
      Node prev, next;

      Node(int key, int value){
        this.key = key;
        this.value = value;
        this.freq = 1;
      }
    }

    private static class DoublyLinkedList {
      Node head, tail;
      int size;

      DoublyLinkedList(){
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
      }

      void addNode(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
      }

      void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
      }

      Node removeLast(){
        if (size == 0) return null;
        Node node = tail.prev;
        removeNode(node);
        return node;
      }

      boolean isEmpty(){
        return size == 0;
      }
    }

    private int capacity;
    private int minFreq;
    private Map<Integer, Node> keyToNode;
    private Map<Integer, DoublyLinkedList> freqToNodes;

    public LFUCache(int capacity){
      this.capacity = capacity;
      this.minFreq = 0;
      this.keyToNode = new HashMap<>();
      this.freqToNodes = new HashMap<>();
    }

    private void updateFreq(Node node){
      int oldFreq = node.freq;
      DoublyLinkedList oldList = freqToNodes.get(oldFreq);
      oldList.removeNode(node);

      if (oldFreq == minFreq && oldList.isEmpty()){
        minFreq++;
      }

      node.freq++;
      freqToNodes
        .computeIfAbsent(node.freq, f -> new DoublyLinkedList())
        .addNode(node);
    }

    public int get(int key){
      if (!keyToNode.containsKey(key)) return -1;
      Node node = keyToNode.get(key);
      updateFreq(node);
      return node.value;
    }

    public void put(int key, int value){
      if (capacity == 0) return;

      if (keyToNode.containsKey(key)){
        Node node = keyToNode.get(key);
        node.value = value;
        updateFreq(node);
        return;
      }

      if (keyToNode.size() > capacity){
        DoublyLinkedList minList = freqToNodes.get(minFreq);
        Node toRemove = minList.removeLast();
        if (toRemove != null) keyToNode.remove(toRemove.key);
      }

      Node newNode = new Node(key, value);
      keyToNode.put(key, newNode);
      freqToNodes.computeIfAbsent(1, f -> new DoublyLinkedList()).addNode(newNode);
      minFreq = 1;
    }
  }
}
