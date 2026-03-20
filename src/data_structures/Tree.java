package data_structures;

import java.util.*;

public class Tree {
  private static class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(){}
    TreeNode(int val){
      this.value = val;
      this.left = this.right = null;
    }
  }

  private TreeNode root;

  Tree(){}
  Tree(List<Integer> nums){
    root = new TreeNode(nums.getFirst());
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);
    Queue<Integer> idxQueue = new LinkedList<>();
    idxQueue.add(0);
    while (!nodeQueue.isEmpty()){
      TreeNode node = nodeQueue.poll();
      int idx = idxQueue.poll();
      int l = idx * 2 + 1, r = l + 1;
      if (l < nums.size() && nums.get(l) != null){
        node.left = new TreeNode(nums.get(l));
        nodeQueue.add(node.left);
        idxQueue.add(l);
      }
      if (r < nums.size() && nums.get(r) != null){
        node.right = new TreeNode(nums.get(r));
        nodeQueue.add(node.right);
        idxQueue.add(r);
      }
    }
  }

  private void _getInorder(TreeNode root, List<Integer> store){
    if (root == null) return;
    _getInorder(root.left, store);
    store.add(root.value);
    _getInorder(root.right, store);
  }

  public List<Integer> getInorder(){
    List<Integer> store = new ArrayList<>();
    _getInorder(root, store);
    return store;
  }

  private void _getPreorder(TreeNode root, List<Integer> store){
    if (root == null) return;
    store.add(root.value);
    _getPreorder(root.left, store);
    _getPreorder(root.right, store);
  }

  public List<Integer> getPreorder(){
    List<Integer> store = new ArrayList<>();
    _getPreorder(root, store);
    return store;
  }

  private void _getPostorder(TreeNode root, List<Integer> store){
    if (root == null) return;
    _getPostorder(root.left, store);
    _getPostorder(root.right, store);
    store.add(root.value);
  }

  public List<Integer> getPostorder() {
    List<Integer> store = new ArrayList<>();
    _getPostorder(root, store);
    return store;
  }

  public List<List<Integer>> getLevelOrder() {
    List<List<Integer>> store = new ArrayList<>();
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);
    while (!nodeQueue.isEmpty()){
      int size = nodeQueue.size();
      List<Integer> temp = new ArrayList<>();
      for (int j = 0; j < size; j++){
        TreeNode node = nodeQueue.poll();
        temp.add(node.value);
        if (node.left != null) nodeQueue.add(node.left);
        if (node.right != null) nodeQueue.add(node.right);
      }
      store.add(temp);
    }

    return store;
  }

  public static void main(String[] args) {
    Tree tree = new Tree(Arrays.asList(1, 2, 3, 4, null, 5, 6, null));
    System.out.println(tree.getLevelOrder());
  }
}
