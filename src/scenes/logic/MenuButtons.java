package scenes.logic;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.ReceiptScene;

import java.util.List;

public class MenuButtons {

    public static Button[] createButtons(Pane layout, Items.Item[] items, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt, Stage primaryStage) {
        Button[] buttons = new Button[items.length];

        for (int i = 0; i < items.length; i++) {
            double x = 0.325 + (i % 3) * 0.2;
            double y = 0.25 + (i / 3) * 0.1;
            buttons[i] = createButton(layout, items[i], x, y, "menu-button", orderList, orderListView, receipt, primaryStage);
        }

        return buttons;
    }

    private static Button createButton(Pane layout, Items.Item item, double x, double y, String styleClass, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt, Stage primaryStage) {
        double paddingX = 50;
        double paddingY = 50;

        // Style and position buttons
        Button button = new Button(item.getItemName());
        button.setOnAction(e -> handleButtonClick(item, orderList, orderListView, receipt));
        button.prefWidthProperty().bind(layout.widthProperty().multiply(0.15));
        button.prefHeightProperty().bind(layout.heightProperty().multiply(0.05));
        button.layoutXProperty().bind(layout.widthProperty().multiply(x).add(paddingX));
        button.layoutYProperty().bind(layout.heightProperty().multiply(y).add(paddingY));
        button.getStyleClass().add(styleClass);
        layout.getChildren().add(button);

        return button;
    }

    private static void handleButtonClick(Items.Item item, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt) {
        // Add the selected item to the order list
        orderList.add(item);

        // Add the selected item to the receipt
        receipt.addItemToReceipt(item);

        // Update the UI
        updateOrderListView(orderListView, orderList);

        // Update receipt display
        receipt.updateReceiptDisplay();

        System.out.println(item.getItemName() + " added to the order! Price: $" + item.getItemPrice());
    }

    public static void updateOrderListView(ListView<String> orderListView, List<Items.Item> orderList) {
        // Clear the existing items in the order list view
        orderListView.getItems().clear();

        // Add the items from the order list to the order list view
        for (Items.Item orderedItem : orderList) {
            orderListView.getItems().add(orderedItem.getItemName() + " - $" + orderedItem.getItemPrice());
        }
    }

    // Create the finish button
    public static Button createFinishButton(Pane layout, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt, Stage primaryStage) {
        Button finishBtn = new Button("Finish");
        finishBtn.setOnAction(e -> switchToReceiptScene(primaryStage, orderList));
        finishBtn.prefWidthProperty().bind(layout.widthProperty().multiply(0.15));
        finishBtn.prefHeightProperty().bind(layout.heightProperty().multiply(0.05));
        finishBtn.layoutXProperty().bind(layout.widthProperty().multiply(0.8));
        finishBtn.layoutYProperty().bind(layout.heightProperty().multiply(0.9));
        finishBtn.getStyleClass().add("finish-button");

        return finishBtn;
    }

    private static void switchToReceiptScene(Stage primaryStage, List<Items.Item> orderList) {
        ReceiptScene.showReceipt(primaryStage, orderList);
    }


}
