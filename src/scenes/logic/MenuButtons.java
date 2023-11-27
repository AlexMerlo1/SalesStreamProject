package scenes.logic;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.ReceiptScene;
import scenes.LoginScene;
import scenes.MenuScene;
import java.util.List;

public class MenuButtons {

    private MenuScene menuScene;
    public MenuButtons(MenuScene menuScene) {
        this.menuScene = menuScene;
    }
    public static Button createLoginButton(Pane layout, double x, double y, double paddingX, double paddingY, String styleClass) {
        Button loginBtn = new Button("Login");
        loginBtn.prefWidthProperty().bind(layout.widthProperty().multiply(0.15));
        loginBtn.prefHeightProperty().bind(layout.heightProperty().multiply(0.05));
        loginBtn.layoutXProperty().bind(layout.widthProperty().multiply(x).add(paddingX));
        loginBtn.layoutYProperty().bind(layout.heightProperty().multiply(y).add(paddingY));
        loginBtn.getStyleClass().add(styleClass);
        return loginBtn;
    }

    public Button[] createButtons(Pane layout, Items.Item[] items, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt, Stage primaryStage) {
        Button[] buttons = new Button[items.length];

        for (int i = 0; i < items.length; i++) {
            double x = 0.325 + (i % 3) * 0.2;
            double y = 0.25 + (i / 3) * 0.1;
            buttons[i] = createButton(layout, items[i], x, y, "menu-button", orderList, orderListView, receipt, primaryStage);
        }

        return buttons;
    }

    private Button createButton(Pane layout, Items.Item item, double x, double y, String styleClass, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt, Stage primaryStage) {
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

    private void handleButtonClick(Items.Item item, List<Items.Item> orderList, ListView<String> orderListView, CreateReceipt receipt) {
        // Add the selected item to the order list
        orderList.add(item);

        // Add the selected item to the receipt
        receipt.addItemToReceipt(item);

        // Update the UI
        updateOrderListView(orderListView, orderList);

        // Update receipt display
        receipt.updateReceiptDisplay();

        // Get the total amount of order
        receipt.calculateTotal(orderList);

        // Update the receipt display
        menuScene.updateTotalSpendingLabel();
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

    // Create Remove button and functionality
    public Button createRemoveButton(Pane layout, ListView<String> orderListView, List<Items.Item> orderList) {
        Button removeBtn = new Button("Remove");
        removeBtn.setOnAction(e -> removeSelectedItem(orderListView, orderList));
        removeBtn.prefWidthProperty().bind(layout.widthProperty().multiply(0.1));
        removeBtn.prefHeightProperty().bind(layout.heightProperty().multiply(0.05));
        removeBtn.layoutXProperty().bind(layout.widthProperty().multiply(0.6));
        removeBtn.layoutYProperty().bind(layout.heightProperty().multiply(0.9));
        // Customize the button properties as needed
        return removeBtn;
    }

    private void removeSelectedItem(ListView<String> orderListView, List<Items.Item> orderList) {
        // Get the selected item in the order list view
        String selectedItem = orderListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Find the corresponding item in the orderList and remove it
            Items.Item selectedOrderItem = findOrderItem(selectedItem, orderList);
            if (selectedOrderItem != null) {
                orderList.remove(selectedOrderItem);
            }
            

            // Update the order list view
            updateOrderListView(orderListView, orderList);
            menuScene.updateTotalSpendingLabel();
        }
    }

    private static Items.Item findOrderItem(String selectedItem, List<Items.Item> orderList) {
        for (Items.Item item : orderList) {
            if ((item.getItemName() + " - $" + item.getItemPrice()).equals(selectedItem)) {
                return item;
            }
        }
        return null;
    }

    private static void switchToReceiptScene(Stage primaryStage, List<Items.Item> orderList) {
        ReceiptScene.showReceipt(primaryStage, orderList);
    }
    
    public Button createLogoutButton(Pane menuLayout, Stage primaryStage) {
        Button logoutBtn = new Button("Logout");

        logoutBtn.prefWidthProperty().bind(menuLayout.widthProperty().multiply(0.1));
        logoutBtn.prefHeightProperty().bind(menuLayout.heightProperty().multiply(0.05));
    
        logoutBtn.layoutXProperty().bind(primaryStage.widthProperty().subtract(logoutBtn.widthProperty().add(30))); 
        logoutBtn.layoutYProperty().bind(primaryStage.heightProperty().multiply(0.05));
        
        logoutBtn.setOnAction(e -> switchToLoginScene(primaryStage));
    
        menuLayout.getChildren().add(logoutBtn);

        return logoutBtn;
    }

    private void switchToLoginScene(Stage primaryStage) {
        LoginScene loginScene = new LoginScene(primaryStage);
        loginScene.createLoginScene();
    }
}
