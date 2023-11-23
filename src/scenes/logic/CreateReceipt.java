package scenes.logic;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;

public class CreateReceipt {

    private VBox receiptLayout;
    private Label receiptLabel;
    private ListView<String> orderListView;
    private ArrayList<Items.Item> receiptItems = new ArrayList<>();

    public CreateReceipt() {

    // Create the layout for the receipt
    receiptLayout = new VBox();
    receiptLabel = new Label("Receipt");

    receiptLayout.getChildren().add(receiptLabel);
    }

    public void addItemToReceipt(Items.Item item) {
        receiptItems.add(item);
        updateReceiptDisplay();
    }

    public void updateReceiptDisplay() {
        // Clear existing items in the receipt layout
        receiptLayout.getChildren().clear();

        // Add items to the receipt layout
        receiptLayout.getChildren().add(receiptLabel);
        for (Items.Item item : receiptItems) {
            Label itemLabel = new Label(item.getItemName() + " - $" + item.getItemPrice());
            receiptLayout.getChildren().add(itemLabel);
        }
    }
    // Update the order list view based on the items in the receipt
    public void updateOrderListView() {
        if (orderListView != null) {
            orderListView.getItems().clear();

            for (Items.Item orderedItem : receiptItems) {
                orderListView.getItems().add(orderedItem.getItemName() + " - $" + orderedItem.getItemPrice());
            }
        }
    }
}