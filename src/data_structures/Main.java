package data_structures;

import java.util.*;

class TreeNode {
  int val;
  TreeNode left, right;

  TreeNode(int v) {
    this.val = v;
  }
}

public class Main {

  // TODO
  // Build the binary tree from level order input ("N" represents null)
  private static TreeNode buildTree(String[] arr) {
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    Queue<Integer> idxQueue = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
    nodeQueue.offer(root);
    idxQueue.offer(0);

    while (!nodeQueue.isEmpty()){
      TreeNode node = nodeQueue.poll();
      int idx = idxQueue.poll();
      int l = idx * 2 + 1, r = l + 1;
      if (l < arr.length && !arr[l].equals("N")){
        node.left = new TreeNode(Integer.parseInt(arr[l]));
        nodeQueue.add(node.left);
        idxQueue.add(l);
      }
      if (r < arr.length && !arr[r].equals("N")){
        node.right = new TreeNode(Integer.parseInt(arr[r]));
        nodeQueue.add(node.right);
        idxQueue.add(r);
      }
    }
    return root;
  }

  // TODO
  // Perform zig-zag (spiral) level order traversal
  private static List<Integer> zigZagTraversal(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    List<Integer> store = new ArrayList<>();
    boolean rev = false;
    while (!q.isEmpty()){
      int size = q.size();
      List<Integer> temp = new ArrayList<>();
      for (int i = 0; i < size; i++){
        TreeNode node = q.poll();
        temp.add(node.val);
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
      }
      if (rev) Collections.reverse(temp);
      store.addAll(temp);
      rev = !rev;
    }
    return store;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    String line = sc.nextLine().trim();
    String[] arr = line.split("\\s+");

    // TODO
    // Build the tree
    TreeNode root = buildTree(arr);

    // TODO
    // Perform zig-zag traversal and print the result
    List<Integer> trav = zigZagTraversal(root);
    for (int i: trav) System.out.print(i + " ");
    System.out.println();
  }
}