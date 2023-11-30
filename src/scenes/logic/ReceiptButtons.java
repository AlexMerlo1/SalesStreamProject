package scenes.logic;


import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import scenes.MenuScene;
import scenes.ReceiptScene;

public class ReceiptButtons {
  public ReceiptButtons(MenuScene menuScene, ReceiptScene receiptScene) {
    }
public Button createPayWithCardButton(Pane layout) {
    Button payWithCard = new Button("Complete Order");
    payWithCard.prefWidthProperty().bind(layout.widthProperty().multiply(0.5));
    payWithCard.prefHeightProperty().bind(layout.heightProperty().multiply(0.1));
    payWithCard.layoutXProperty().bind(layout.widthProperty().multiply(0.275));
    payWithCard.layoutYProperty().bind(layout.heightProperty().multiply(0.3));
    payWithCard.getStyleClass().add("menu-button");
    payWithCard.setStyle("menu-button");
    payWithCard.getStylesheets().add(getClass().getResource("/scenes/styles.css").toExternalForm());
    
    return payWithCard;
  }

  public Button createCancelOrderButton(Pane layout) {
    Button cancelButton = new Button("CANCEL ORDER");
    cancelButton.prefWidthProperty().bind(layout.widthProperty().multiply(0.5));
    cancelButton.prefHeightProperty().bind(layout.heightProperty().multiply(0.1));
    cancelButton.layoutXProperty().bind(layout.widthProperty().multiply(0.275));
    cancelButton.layoutYProperty().bind(layout.heightProperty().multiply(0.8));
    cancelButton.getStyleClass().add("finish-button");
    cancelButton.setStyle("finish-button");
    cancelButton.getStylesheets().add(getClass().getResource("/scenes/styles.css").toExternalForm());
    return cancelButton;
  }

}
