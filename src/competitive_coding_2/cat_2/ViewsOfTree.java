package competitive_coding_2.cat_2;

import utility.logger.Logger;

import java.util.*;

public class ViewsOfTree {
  public static class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val){
      this.val = val;
    }
  }

  public static TreeNode buildTree(int[] arr, int idx){
    if (idx >= arr.length || arr[idx] == -1) return null;
    TreeNode root = new TreeNode(arr[idx]);
    root.left = buildTree(arr, 2 * idx + 1);
    root.right = buildTree(arr, 2 * idx + 2);
    return root;
  }

  public static void printHorizontalView(TreeNode root){
    if (root == null) return;

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()){
      TreeNode node = q.poll();
      System.out.print(node.val + " ");
      if (node.left != null) q.offer(node.left);
      if (node.right != null) q.offer(node.right);
    }

    System.out.println();
  }

  public static void printVerticalView(TreeNode root){
    if (root == null) return;
    Map<Integer, List<Integer>> verticalMap = new TreeMap<>();
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    Queue<Integer> hdQueue = new LinkedList<>();
    nodeQueue.offer(root);
    hdQueue.offer(0);

    while (!nodeQueue.isEmpty()){
      TreeNode node = nodeQueue.poll();
      int hd = hdQueue.poll();

      verticalMap.computeIfAbsent(hd, (k) -> new ArrayList<>()).add(node.val);

      if (node.left != null) {
        nodeQueue.offer(node.left);
        hdQueue.offer(hd - 1);
      }

      if (node.right != null) {
        nodeQueue.offer(node.right);
        hdQueue.offer(hd + 1);
      }
    }

    for (List<Integer> values: verticalMap.values())
      for (int val: values) System.out.print(val + " ");

    System.out.println();
  }

  public static void printTopView(TreeNode root){
    if (root == null) return;

    Map<Integer, Integer> map = new TreeMap<>();
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    Queue<Integer> hdQueue = new LinkedList<>();

    nodeQueue.offer(root);
    hdQueue.offer(0);

    while (!nodeQueue.isEmpty()){
      TreeNode node = nodeQueue.poll();
      int hd = hdQueue.poll();

      if (!map.containsKey(hd))
        map.put(hd, node.val);

      if (node.left != null) {
        nodeQueue.offer(node.left);
        hdQueue.offer(hd - 1);
      }

      if (node.right != null) {
        nodeQueue.offer(node.right);
        hdQueue.offer(hd + 1);
      }
    }

    for (int val: map.values())
      System.out.print(val + " ");

    System.out.println();
  }

  public static void printLeftView(TreeNode root){
    if (root == null) return;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()){
      int size = q.size();
      for (int i = 0; i < size; i++){
        TreeNode node = q.poll();
        if (i == 0) System.out.print(node.val + " ");
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
      }
    }
    System.out.println();
  }

  public static void printRightView(TreeNode root){
    if (root == null) return;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()){
      int size = q.size();
      for (int i = 0; i < size; i++){
        TreeNode node = q.poll();
        if (i == size - 1) System.out.print(node.val + " ");
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
      }
    }
    System.out.println();
  }

  public static void printBottomView(TreeNode root){
    if (root == null) return;

    Map<Integer, Integer> map = new TreeMap<>();
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    Queue<Integer> hdQueue = new LinkedList<>();

    nodeQueue.offer(root);
    hdQueue.offer(0);

    while (!nodeQueue.isEmpty()){
      TreeNode node = nodeQueue.poll();
      int hd = hdQueue.poll();

      map.put(hd, node.val);

      if (node.left != null) {
        nodeQueue.offer(node.left);
        hdQueue.offer(hd - 1);
      }

      if (node.right != null) {
        nodeQueue.offer(node.right);
        hdQueue.offer(hd + 1);
      }
    }

    for (int val: map.values())
      System.out.print(val + " ");

    System.out.println();
  }

  private static void printLeftBoundary(TreeNode root){
    if (root == null || (root.left == null && root.right == null)) return;
    System.out.print(root.val +  " ");
    if (root.left != null) printLeftBoundary(root.left);
    else printLeftBoundary(root.right);
  }

  private static void printRightBoundary(TreeNode root){
    if (root == null || (root.left == null && root.right == null)) return;
    System.out.print(root.val +  " ");
    if (root.right != null) printRightBoundary(root.right);
    else printRightBoundary(root.left);
  }

  private static void printLeaves(TreeNode root){
    if (root == null) return;
    if (root.left == null && root.right == null){
      System.out.print(root.val + " ");
      return;
    }
    printLeaves(root.left);
    printLeaves(root.right);
  }

  public static void printBoundary(TreeNode root){
    if (root == null) return;
    System.out.print(root.val + " ");
    printLeftBoundary(root.left);
    printLeaves(root.left);
    printLeaves(root.right);
    printRightBoundary(root.right);
    System.out.println();
  }

  public static void main(String[] args) {
    int[] treeNodes = { 1, 2, 3, -1, 4, -1, -1 };
    TreeNode root = buildTree(treeNodes, 0);
    printHorizontalView(root);
    printVerticalView(root);
    printLeftView(root);
    printRightView(root);
    printTopView(root);
    printBottomView(root);
    printBoundary(root);
  }
}
