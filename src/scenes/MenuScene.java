package scenes;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MenuScene {

    private Stage primaryStage;
    private String username;

    public MenuScene(Stage primaryStage, String username) {
        this.primaryStage = primaryStage;
        this.username = username;
    }

    public void show() {
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Button logoutBtn = new Button("Logout");

        logoutBtn.setOnAction(e -> switchToLoginScene());

        GridPane menuGrid = new GridPane();
        menuGrid.setAlignment(javafx.geometry.Pos.CENTER);
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.setPadding(new Insets(25, 25, 25, 25));
        menuGrid.add(welcomeLabel, 0, 0);
        menuGrid.add(logoutBtn, 1, 0);

        Scene menuScene = new Scene(menuGrid, 600, 400);
        primaryStage.setScene(menuScene);
        
    }

    private void switchToLoginScene() {
        LoginScene loginScene = new LoginScene(primaryStage);
        loginScene.createLoginScene();
    }
}