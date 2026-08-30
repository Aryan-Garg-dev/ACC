package competitive_coding_2.fat.practice;

import utility.Input;

import java.util.Arrays;

public class WinnerTree {
  private int[] tree;
  private int[] players;
  private int size;

  public WinnerTree(int[] players){
    int n = players.length;
    this.players = players;

    size = 1;
    while (size < n) size <<= 1;
    tree = new int[2 * size - 1];
    Arrays.fill(tree, -1);

    for (int i = 0; i < n; i++){
      tree[size - 1 + i] = i;
    }

    build(0, 0, size - 1);
  }

  public void build(int node, int left, int right){
    if (left == right) return;
    int mid = (left + right) / 2;
    build(2 * node + 1, left, mid);
    build(2 * node + 2, mid + 1, right);
    int leftIdx = tree[2 * node + 1];
    int rightIdx = tree[2 * node + 2];
    if (leftIdx == -1) tree[node] = rightIdx;
    else if (rightIdx == -1) tree[node] = leftIdx;
    else tree[node] = players[leftIdx] >= players[rightIdx] ? leftIdx : rightIdx;
  }

  public int getWinnerIdx(){
    return tree[0];
  }

  public static void main(String[] args) {
    Input input = new Input();
    WinnerTree tree = new WinnerTree(
      input.readIntArray(input.prompt("Enter size: ").readInt())
    );
    System.out.println(tree.getWinnerIdx());
  }

}
