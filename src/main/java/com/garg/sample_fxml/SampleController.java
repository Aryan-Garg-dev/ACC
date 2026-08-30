package com.garg.sample_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SampleController {


  @FXML public Button button;
  @FXML public Label label;

  public void handleClick(ActionEvent actionEvent) {
    label.setText("Hey...");
    button.setText("Not Again!");
  }
}
