package com.garg.shopping_cart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(App.class.getResource("/ShoppingCart.fxml"));
    stage.setScene(new Scene(root, 500, 450));
    stage.setTitle("Online shopping cart");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
