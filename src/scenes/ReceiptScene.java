package scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.logic.Items;
import java.util.List;
import java.util.ArrayList;


public class ReceiptScene {

    public static void showReceipt(Stage primaryStage, List<Items.Item> orderList) {
        VBox receiptLayout = createReceiptLayout(orderList);

        // Create a scene for the receipt
        Scene receiptScene = new Scene(receiptLayout, 400, 300);

        // Create a new stage for the receipt
        Stage receiptStage = new Stage();
        receiptStage.setTitle("Receipt");
        receiptStage.setScene(receiptScene);

        // Show the receipt stage
        receiptStage.show();
    }

    private static VBox createReceiptLayout(List<Items.Item> orderList) {
        VBox receiptLayout = new VBox();
        receiptLayout.setPadding(new Insets(25, 25, 25, 25));

        // Create a Label for the receipt
        Label receiptLabel = new Label("Receipt:");
        receiptLayout.getChildren().add(receiptLabel);

        // Add items to the receipt layout
        for (Items.Item item : orderList) {
            Label itemLabel = new Label(item.getItemName() + " - $" + item.getItemPrice());
            receiptLayout.getChildren().add(itemLabel);
        }

        return receiptLayout;
    }
}