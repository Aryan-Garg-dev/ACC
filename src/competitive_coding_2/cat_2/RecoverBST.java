package competitive_coding_2.cat_2;

public class RecoverBST {
  static class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val){
      this.val = val;
    }
  }


  /*
   * Inorder Traversal and 2 Pointer Approach
   * Time: O(n)
   * Space: O(h)
   */
    static class TwoPointersSolution {
      private TreeNode first, second;
      private TreeNode prev = new TreeNode(Integer.MIN_VALUE);

      private void inorder(TreeNode node){
        if (node == null) return;
        inorder(node.left);
        if (prev.val > node.val){
          if (first == null) first = prev;
          second = node;
        }
        prev = node;
        inorder(node.right);
      }

      public void recoverTree(TreeNode root){
        inorder(root);
        if (first == null || second == null) return;
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
      }
  }

  private static void printTreeInorder(TreeNode root){
      if (root == null) return;
      printTreeInorder(root.left);
      System.out.print(root.val + " ");
      printTreeInorder(root.right);
  }


  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    root.right.left = new TreeNode(2);

    new TwoPointersSolution().recoverTree(root);
    printTreeInorder(root);

  }
}
