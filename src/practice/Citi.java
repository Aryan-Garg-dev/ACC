package practice;

class Solution {
  public int[] solution(int[] skills) {
    int n = skills.length;
    int[] result = new int[n];
    int round = 1;
    int[] players = new int[n];
    for (int i = 0; i < n; i++) players[i] = i; // store indices

    while (players.length > 1) {
      int nextSize = players.length / 2;
      int[] nextRound = new int[nextSize];
      for (int i = 0; i < players.length; i += 2) {
        int p1 = players[i], p2 = players[i + 1];
        if (skills[p1] > skills[p2]) {
          result[p2] = round;
          nextRound[i / 2] = p1;
        } else {
          result[p1] = round;
          nextRound[i / 2] = p2;
        }
      }
      players = nextRound;
      round++;
    }
    result[players[0]] = round - 1; // winner's last round
    return result;
  }
}