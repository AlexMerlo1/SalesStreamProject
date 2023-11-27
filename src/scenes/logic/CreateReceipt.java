package scenes.logic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;

public class CreateReceipt {

    private VBox receiptLayout;
    private Label receiptLabel;
    private ListView<String> orderListView;
    private ArrayList<Items.Item> receiptItems = new ArrayList<>();
    private double total;

    public CreateReceipt() {
        // Create the layout for the receipt
        receiptLayout = new VBox();
        receiptLabel = new Label("Receipt");

        receiptLayout.getChildren().add(receiptLabel);
    }

    public VBox getReceiptLayout() {
        return receiptLayout;
    }

    public void addItemToReceipt(Items.Item item) {
        receiptItems.add(item);
        updateReceiptDisplay();
        updateOrderListView();
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
    private void updateOrderListView() {
        if (orderListView != null) {
            orderListView.getItems().clear();

            for (Items.Item orderedItem : receiptItems) {
                orderListView.getItems().add(orderedItem.getItemName() + " - $" + orderedItem.getItemPrice());
            }
        }
    }


    public double calculateTotal(List<Items.Item> orderList) {
        total = 0;
        for (Items.Item item : orderList) {
            total += item.getItemPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedTotal = decimalFormat.format(total);
        System.out.println("Calculated Total: " + formattedTotal); 

        // Parse the formatted total back to a double before returning
        return Double.parseDouble(formattedTotal);
    }

}
