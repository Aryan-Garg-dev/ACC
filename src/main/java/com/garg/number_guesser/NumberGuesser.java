package com.garg.number_guesser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.Random;

public class NumberGuesser extends Application {
  int numberOfTries;
  int magicNumber;

  @Override
  public void start(Stage stage) throws Exception {
    var promptLabel = new Label("Guess a number between 1 and 10");
    var inputField = new TextField("");
    var guessButton = new Button("Guess");
    var feedbackLabel = new Label();

    var layout = new VBox(10,
      promptLabel,
      inputField,
      guessButton,
      feedbackLabel
    );

    var scene = new Scene(layout, 300, 400);

    stage.setTitle("Guess the number!");
    stage.setScene(scene);
    stage.show();

    Random random = new Random();
    magicNumber = random.nextInt(10) + 1;

    guessButton.setOnAction(e -> {
      String input = inputField.getText();
      int guess = Integer.parseInt(input);
      numberOfTries++;
      if (guess < magicNumber){
        feedbackLabel.setText("Guess higher!");
        guessButton.setText("Guess Again!");
      }
      else if (guess > magicNumber){
        feedbackLabel.setText("Guess Lower!");
        guessButton.setText("Guess Again!");
      }
      else {
        feedbackLabel.setText("You guessed it right in " + numberOfTries + " tries.");
        guessButton.setText("Play Again?");
        magicNumber = random.nextInt(10) + 1;
        numberOfTries = 0;
      }
      inputField.clear();
    });
  }
}
