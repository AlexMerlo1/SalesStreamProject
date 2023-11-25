package scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;

public class LoginScene {

    private TextField userTextField;
    private PasswordField passwordField;
    private Text invalidPassword;
    private Stage primaryStage;

    public LoginScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeComponents();
    }

    private void initializeComponents() {
        userTextField = new TextField();
        passwordField = new PasswordField();
        invalidPassword = new Text("Invalid username or password");
        invalidPassword.setVisible(false);

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> handleLogin());

        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(javafx.geometry.Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.add(userTextField, 0, 0);
        loginGrid.add(passwordField, 0, 1);
        loginGrid.add(loginBtn, 0, 2);
        loginGrid.add(invalidPassword, 0, 3);

        Scene loginScene = new Scene(loginGrid, 600, 400);
        primaryStage.setScene(loginScene);
    }

    public void handleLogin() {
        String username = userTextField.getText();
        String password = passwordField.getText();

        List<String> validUsernames = Arrays.asList("amerlo", "user2", "user3");
        List<String> validPasswords = Arrays.asList("password", "pass2", "pass3");

        invalidPassword.setVisible(false);
        boolean isValid = false;

        for (int i = 0; i < validUsernames.size(); i++) {
            if (username.equals(validUsernames.get(i)) && password.equals(validPasswords.get(i))) {
                isValid = true;
                break;
            }
        }

        if (isValid) {
            MenuScene menuScene = new MenuScene(primaryStage, username);
            menuScene.show();
        } else {
            invalidPassword.setVisible(true);
        }
    }
    public Scene createLoginScene() {
        initializeComponents();

        Button loginBtn = new Button("Login");
        loginBtn.getStyleClass().add("login-button");
        loginBtn.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        loginBtn.setOnAction(e -> handleLogin());

        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(javafx.geometry.Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.add(userTextField, 0, 0);
        loginGrid.add(passwordField, 0, 1);
        loginGrid.add(loginBtn, 1, 2);
        loginGrid.add(invalidPassword, 0, 3);

        loginBtn.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Scene loginScene = new Scene(loginGrid, 600, 500);
        primaryStage.setScene(loginScene);

        return loginScene;
    }
    public Scene getLoginScene() {
        return primaryStage.getScene();
    }
}