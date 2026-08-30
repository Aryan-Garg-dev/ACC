package com.garg.shopping_cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ShoppingCartController {
  @FXML public TextField productField;
  @FXML public ListView cartListView;
  @FXML public Label statusLabel;

  private final ObservableList<String> cartItems = FXCollections.observableArrayList();

  @FXML
  public void initialize(){
    cartListView.setItems(cartItems);
  }

  @FXML
  public void handleAddToCart(ActionEvent actionEvent) {
    String product = productField.getText().trim();
    if (product.isEmpty()){
      statusLabel.setText("Please enter a valid product name.");
      return;
    }
    cartItems.add(product);
    statusLabel.setText("\"" + product + "\" added to cart.");
    productField.clear();
  }

  @FXML
  public void handleRemoveFromCart(ActionEvent actionEvent) {
    String selected = cartListView.getSelectionModel().getSelectedItem().toString();
    if (selected == null){
      statusLabel.setText("Please select a product in the list to remove.");
      return;
    }
    cartItems.remove(selected);
    statusLabel.setText("\"" + selected + "\" removed from cart.");
  }

  @FXML
  public void handleClearCart(ActionEvent actionEvent) {
    cartItems.clear();
    statusLabel.setText("Cart cleared.");
  }
}
