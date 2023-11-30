package scenes;

import scenes.logic.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import java.util.List;

public class ReceiptScene {
    private static String username;
    private static Stage receiptStage;
    private static MenuScene menuScene;
    private static MenuButtons menuButtons;

    public ReceiptScene(MenuScene menuScene) {
        ReceiptScene.menuScene = menuScene;
        this.menuButtons = menuButtons;
    }

    public static void showReceipt(Stage primaryStage, List<Items.Item> orderList) {
        CreateReceipt receipt = new CreateReceipt();

        double total = receipt.calculateTotal(orderList);

        Pane receiptLayout = createReceiptLayout(primaryStage, orderList);
        receiptLayout.setStyle("-fx-background-color: #2B6C68;");
        // Create a scene for the receipt
        Scene receiptScene = new Scene(receiptLayout, 400, 300);
        ReceiptButtons receiptButtons = new ReceiptButtons(menuScene, new ReceiptScene(menuScene));

        // Create a new stage for the receipt
        receiptStage = new Stage();
        receiptStage.setTitle("Receipt");
        receiptStage.setScene(receiptScene);

        // Show the receipt stage
        receiptStage.show();

    }

    private static Pane createReceiptLayout(Stage primaryStage, List<Items.Item> orderList) {
        Pane receiptLayout = new Pane();
        ReceiptButtons receiptButtons = new ReceiptButtons(menuScene, new ReceiptScene(menuScene));
        Button cardButton = receiptButtons.createPayWithCardButton(receiptLayout);

        // Attach event handler to the card button
        cardButton.setOnAction(event -> {
            // Perform actions when the card button is clicked
            try {
                showOrderCompleteScene(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button cancelButton = receiptButtons.createCancelOrderButton(receiptLayout);
        receiptLayout.getChildren().addAll(cardButton, cancelButton);

        // Attach event handler to the cancel button
        cancelButton.setOnAction(event -> {
            // Perform actions when the cancel button is clicked
            closeReceiptStage(primaryStage);
        });

        return receiptLayout;
    }

    private static void showOrderCompleteScene(Stage primaryStage) throws Exception {
        // Create a new stage for the order complete scene
        Stage orderCompleteStage = new Stage();
        orderCompleteStage.initModality(Modality.APPLICATION_MODAL);
        orderCompleteStage.initOwner(primaryStage);
        orderCompleteStage.setTitle("Order Complete");

        // Create a label with the order complete message
        Label orderCompleteLabel = new Label("Order Complete. Thank you!");

        // Create a layout for the order complete scene
        VBox orderCompleteLayout = new VBox(70);
        orderCompleteLayout.getChildren().add(orderCompleteLabel);
        orderCompleteLayout.setPadding(new Insets(30));

        // Set the layout for the order complete scene
        Scene orderCompleteScene = new Scene(orderCompleteLayout);

        // Set the scene and show the order complete stage
        orderCompleteStage.setScene(orderCompleteScene);
        orderCompleteStage.showAndWait();

        closeReceiptStage(primaryStage);
    }

    private static void closeReceiptStage(Stage primaryStage) {
        // Close the current stage (receipt stage)
        receiptStage.close();

        // Create a new MenuScene and show it
        MenuScene newMenuScene = new MenuScene(primaryStage, username);
        newMenuScene.show();
    }
}
