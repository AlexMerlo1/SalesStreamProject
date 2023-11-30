package scenes;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scenes.logic.*;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ListView;
public class MenuScene {
    private Stage primaryStage;
    private String username;
    private List<Items.Item> orderList = new ArrayList<>();
    private ListView<String> orderListView = new ListView<>();
    private CreateReceipt receipt;
    private Pane menuLayout;
    private Label totalSpendingLabel;
    private MenuButtons menuButtons;
    private MenuButtons removeButton;


    public MenuScene(Stage primaryStage, String username) {
        this.primaryStage = primaryStage;
        this.username = username;
        this.receipt = new CreateReceipt();
        this.totalSpendingLabel = new Label();
        this.menuButtons = new MenuButtons(this);
        this.removeButton = new MenuButtons(this);

    }


    public void show() {

        menuLayout = new Pane();
        menuLayout.setPadding(new Insets(25, 25, 25, 25));
        // Style rectangle where receipt will be shown
        menuLayout.getChildren().add(createReceiptRectangle());
        // Create the logout button
        menuButtons.createLogoutButton(menuLayout, primaryStage);
        // Create the reciept to be shown on the screen
        receipt = new CreateReceipt();
        // Create items
        Items.Item item1 = Products.item1;
        Items.Item item2 = Products.item2;
        Items.Item item3 = Products.item3;
        Items.Item item4 = Products.item4;
        Items.Item item5 = Products.item5;
        Items.Item item6 = Products.item6;
        Items.Item item7 = Products.item7;
        Items.Item item8 = Products.item8;
        Items.Item item9 = Products.item9;

        // Create buttons using MenuButtons logic
        menuButtons.createButtons(menuLayout, new Items.Item[]{item1, item2, item3, item4, item5, item6, item7, item8, item9}, orderList, orderListView, receipt, primaryStage);
        menuButtons.createButtons(menuLayout,
                new Items.Item[] { item1, item2, item3, item4, item5, item6, item7, item8, item9 }, orderList,
                orderListView, receipt, primaryStage);

        // Add the order list to the layout
        menuLayout.getChildren().add(createOrderListView());

        // Add the Finish button to the layout
        menuLayout.getChildren().add(MenuButtons.createFinishButton(menuLayout, orderList, orderListView, receipt, primaryStage));
        menuLayout.getChildren()
                .add(MenuButtons.createFinishButton(menuLayout, orderList, orderListView, receipt, primaryStage));

        totalSpendingLabel.layoutXProperty().bind(menuLayout.widthProperty().multiply(0.025));
        totalSpendingLabel.layoutYProperty().bind(menuLayout.heightProperty().multiply(0.9));
        menuLayout.getChildren().add(totalSpendingLabel);



        // Create the remove Button
        menuLayout.getChildren().add(removeButton.createRemoveButton(menuLayout, orderListView, orderList));


        Scene menuScene = new Scene(menuLayout, 600, 500);
        menuScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Menu");
        primaryStage.setScene(menuScene);
    }


    private ListView<String> createOrderListView() {
        orderListView.prefWidthProperty().bind(menuLayout.widthProperty().multiply(0.25));
        orderListView.prefHeightProperty().bind(menuLayout.heightProperty().multiply(0.8));
        orderListView.layoutXProperty().bind(menuLayout.widthProperty().multiply(0.025));
        orderListView.layoutYProperty().bind(menuLayout.heightProperty().multiply(0.05));
        return orderListView;
    }
    private Rectangle createReceiptRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(primaryStage.widthProperty().multiply(0.3));
        rectangle.heightProperty().bind(primaryStage.heightProperty().multiply(1));
        rectangle.setFill(Color.GRAY);
        return rectangle;
    }
    public void updateTotalSpendingLabel() {
        totalSpendingLabel.setText("Total: $" + receipt.calculateTotal(orderList));
    }

    public void resetOrderList() {
        // Clear the order list
        orderList.clear();

        // Show the menu scene again
        show();
    }

}