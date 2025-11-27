package challenges;

import utility.Console;
import utility.Input;

final class GuessTheNumber {
  private final static Input input = new Input();
  private final int number;
  private final int max;
  private int attemptsLeft;

  public GuessTheNumber(int max, int maxAttempts){
    this.max = max;
    this.number = (int) Math.ceil(Math.random() * max);
    this.attemptsLeft = maxAttempts;
  }

  public boolean play(){
    int guess;
    do {
      if (this.attemptsLeft == 0) {
        Console.log().println("Unfortunately, you have ran out of attempts. GAME OVER");
        Console.log().print("The number was ").println(this.number);
        return false;
      }
      guess = input
        .prompt("Guess a number between 0 and " + max + ": ")
        .readInt();
      if (guess > (max + this.number) / 2){
        Console.warn().println("Your guess is far greater than the number");
      }
      else if ((guess > this.number) && (guess < (max + this.number) / 2)){
        Console.warn().println("Your guess is greater than the number");
      }
      else if ((guess > this.number / 2) && (guess < this.number)){
        Console.warn().println("Your guess is smaller than the number");
      }
      else if (guess < this.number / 2){
        Console.warn().println("Your guess is far smaller than the number");
      }
      this.attemptsLeft -= 1;
      if (guess != this.number)
        Console.log().printf("Attempts Left: %d\n", this.attemptsLeft).println();
    } while (guess != this.number);
    Console.log().println("Congratulations, you got it right!!!");
    return true;
  }
}

public class GTN {
  public static void main(String[] args) {
    GuessTheNumber game = new GuessTheNumber(100, 6);
    boolean isGameOn = game.play();
  }
}
