package leetcode;

import utility.Console;

import java.util.HashMap;
import java.util.Map;

public class Q2241 {
  static class ATM {
    private final static int[] denomiations = {20, 50, 100, 200, 500};
    private int[] bankNotes;

    public ATM() {
      bankNotes = new int[]{ 0, 0, 0, 0, 0 };
    }

    public void deposit(int[] banknotesCount) {
      for (int i = 0; i < banknotesCount.length; i++){
        bankNotes[i] += banknotesCount[i];
      }
      log();
    }

    public int[] withdraw(int amount) {
      int[] bankNotes = this.bankNotes.clone();
      int[] result = new int[bankNotes.length];
      for (int i = bankNotes.length - 1; i >= 0; i--){
       if (denomiations[i] > amount) continue;
       if (bankNotes[i] == 0) continue;
       int reqCount = amount / denomiations[i];
       int remAmt = amount % denomiations[i];
       int availCount = bankNotes[i];
       if (reqCount >= availCount){
         result[i] = availCount;
         remAmt += denomiations[i] * (reqCount - availCount);
         availCount = 0;
       } else {
         result[i] = reqCount;
         availCount -= reqCount;
       }
       amount = remAmt;
       bankNotes[i] = availCount;
      }
      if (amount != 0) return new int[]{-1};
      this.bankNotes = bankNotes;
      log();
      return result;
    }

    private void log(){
      Console.debug()
        .println("Available")
        .print(denomiations)
        .print(bankNotes)
        .println();
    }
  }

  public static void main(String[] args) {
    ATM machine = new ATM();
    machine.deposit(new int[]{ 0, 0, 1, 2, 1 });
    Console.log().print(machine.withdraw(600));
    machine.deposit(new int[]{ 0, 1, 0, 1, 1 });
    Console.log().print(machine.withdraw(600));
    Console.log().print(machine.withdraw(550));

  }
}
