import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import scenes.MenuScene;
import scenes.ReceiptScene;

public class ReceiptButtons {
  public ReceiptButtons(MenuScene menuScene, ReceiptScene receiptScene) {
    }
public Button createPayWithCardButton(Pane layout) {
    //payWithCard.setOnAction(e -> checkPayment(orderListView, orderList));
    payWithCard.prefWidthProperty().bind(layout.widthProperty().multiply(0.2));
    payWithCard.prefHeightProperty().bind(layout.heightProperty().multiply(0.1));
    payWithCard.layoutXProperty().bind(layout.widthProperty().multiply(0.4));
    payWithCard.layoutYProperty().bind(layout.heightProperty().multiply(0.3));
    // Customize the button properties as needed
    return payWithCard;
  }
  public Button createPayWithCashButton(Pane layout) {
    Button payWithCash = new Button("CASH");
    //payWithCard.setOnAction(e -> checkPayment(orderListView, orderList));
    payWithCash.prefWidthProperty().bind(layout.widthProperty().multiply(0.2));
    payWithCash.prefHeightProperty().bind(layout.heightProperty().multiply(0.1));
    payWithCash.layoutXProperty().bind(layout.widthProperty().multiply(0.4));
    payWithCash.layoutYProperty().bind(layout.heightProperty().multiply(0.5));
    // Customize the button properties as needed
    return payWithCash;
  }
  public Button createCancelOrderButton(Pane layout) {
    Button cancelButton = new Button("CANCEL ORDER");
    //payWithCard.setOnAction(e -> checkPayment(orderListView, orderList));
    cancelButton.prefWidthProperty().bind(layout.widthProperty().multiply(0.2));
    cancelButton.prefHeightProperty().bind(layout.heightProperty().multiply(0.1));
    cancelButton.layoutXProperty().bind(layout.widthProperty().multiply(0.2));
    cancelButton.layoutYProperty().bind(layout.heightProperty().multiply(0.8));
    // Customize the button properties as needed
    return cancelButton;
  }
    //private checkPayment() {
    //}
}