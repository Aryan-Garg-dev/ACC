package competitive_coding;

import utility.Console;

import java.util.Scanner;

public class BoothMultiplier {
  
  public long multiply(int M, int Q) {
    int A = 0;      // Accumulator
    int Q_1 = 0;    // Previous bit
    int count = 32; // Number of bits in int

    while (count > 0) {
      int Q0 = Q & 1; // Current LSB of Q

      // Booth's decision logic
      if (Q0 == 0 && Q_1 == 1) {
        A += M;  // 1->0 transition: add multiplicand
      } else if (Q0 == 1 && Q_1 == 0) {
        A -= M;  // 0->1 transition: subtract multiplicand
      }

      // Arithmetic right shift of [A,Q,Q_1]
      Q = (Q >>> 1) | ((A & 1) << 31); // shift Q right, MSB of Q = LSB of A
      A = A >> 1;                        // arithmetic shift A
      Q_1 = Q0;

      count--;
    }

    return (((long) A) << 32) | (Q & 0xFFFFFFFFL);
  }
}
