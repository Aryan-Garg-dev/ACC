package competitive_coding_2.fat.practice;

import utility.Input;

public class TowerOfHanoi {
  static void towerOfHanoi(int n, char source, char destination, char auxillary){
    if (n == 0) return;
    towerOfHanoi(n - 1, source, auxillary, destination);
    System.out.printf("Disk %d moved from %c to %c\n", n, source, destination);
    towerOfHanoi(n - 1, auxillary, destination, source);
  }

  public static void main(String[] args) {
    Input input = new Input();
    towerOfHanoi(
      input.prompt("No. of disks: ").readInt(),
      'A', 'B', 'C'
    );
  }
}
