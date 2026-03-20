package competitive_coding_2.cat_2;

public class WinnerTree {
  static class TreeNode {
    int val;
    int idx;
    TreeNode left, right;

    TreeNode(int value, int index){
      val = value; idx = index;
    }
  }

  private final TreeNode root;

  public WinnerTree(int[] players){
    root = build(players, 0, players.length - 1);
  }

  private TreeNode build(int[] players, int l, int r){
    if (l == r) return new TreeNode(players[l], l); // leaf node

    int mid = (l + r) / 2;
    TreeNode left = build(players, l, mid);
    TreeNode right = build(players, mid + 1, r);

    TreeNode parent;
    if (left.val > right.val) parent = new TreeNode(left.val, left.idx);
    else parent = new TreeNode(right.val, right.idx);
    parent.left = left;
    parent.right = right;
    return parent;
  }

  public int getWinner(){ return root.val; }

  public int getWinnerIdx(){ return root.idx; }

  public static void main(String[] args) {
    int[] players = { 3, 7, 1, 9, 4, 2, 8, 5 };
    WinnerTree wt = new WinnerTree(players);
    System.out.println(wt.getWinner());
    System.out.println(wt.getWinnerIdx());
  }
}
