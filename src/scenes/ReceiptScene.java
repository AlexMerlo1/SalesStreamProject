package scenes;
import scenes.logic.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import java.util.List;


public class ReceiptScene {

    public static void showReceipt(Stage primaryStage, List<Items.Item> orderList) {
        CreateReceipt receipt = new CreateReceipt();

        double total = receipt.calculateTotal(orderList);

        Pane receiptLayout = createReceiptLayout(orderList);

        // Create a scene for the receipt
        Scene receiptScene = new Scene(receiptLayout, 400, 300);
        ReceiptButtons receiptButtons = new ReceiptButtons();

        // Create a new stage for the receipt
        Stage receiptStage = new Stage();
        receiptStage.setTitle("Receipt");
        receiptStage.setScene(receiptScene);

        // Show the receipt stage
        receiptStage.show();
    }

    private static Pane createReceiptLayout(List<Items.Item> orderList) {
        Pane receiptLayout = new Pane();
        ReceiptButtons receiptButtons = new ReceiptButtons();
        Button cardButton = receiptButtons.createPayWithCardButton(receiptLayout);
        Button cashButton = receiptButtons.createPayWithCashButton(receiptLayout);
        Button cancelButton = receiptButtons.createCancelOrderButton(receiptLayout);
        receiptLayout.getChildren().addAll(cardButton, cashButton, cancelButton);

        return receiptLayout;
    }
}