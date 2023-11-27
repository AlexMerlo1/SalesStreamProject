package scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scenes.logic.MenuButtons;
import javafx.scene.layout.Pane;
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
        userTextField.setPromptText("Username");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        invalidPassword = new Text("Invalid username or password");
        invalidPassword.setVisible(false);

        // Create a Pane for the layout
        Pane layoutPane = new Pane();

        // Use the modified createLoginButton method from MenuButtons
        Button loginBtn = MenuButtons.createLoginButton(layoutPane, 0, 0, 0, 0, "login-button");
        loginBtn.setOnAction(e -> handleLogin());


        layoutPane.getChildren().addAll(userTextField, passwordField, loginBtn, invalidPassword);

        Scene loginScene = new Scene(layoutPane, 600, 400);
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
        Pane loginPane = new Pane();
        initializeComponents();

        // Create welcome/login text position and style
        Text welcomeText = new Text("Welcome To SalesStream");
        Text loginText = new Text("Please Login");

        welcomeText.getStyleClass().add("login-text");
        welcomeText.setStyle("login-text");

        loginText.getStyleClass().add("login-text");
        loginText.setStyle("login-text");
        

        welcomeText.layoutXProperty().bind(userTextField.layoutXProperty().add(userTextField.widthProperty().subtract(welcomeText.boundsInLocalProperty().get().getWidth()).divide(2)).subtract(75));
        welcomeText.layoutYProperty().bind(loginPane.heightProperty().multiply(0.3));
        
        loginText.layoutXProperty().bind(passwordField.layoutXProperty().add(passwordField.widthProperty().subtract(loginText.boundsInLocalProperty().get().getWidth()).divide(2)).subtract(35));
        loginText.layoutYProperty().bind(loginPane.heightProperty().multiply(0.35));
        
        // Create login button and style
        Button loginBtn = new Button("Login");
        loginBtn.getStyleClass().add("login-button");
        loginBtn.setOnAction(e -> handleLogin());
    
        // Make the background color
        loginPane.setStyle("-fx-background-color: #2B6C68;");

        // Create the login scene with percentage based positioning and sizes
        double widthPercentage = 0.5;
        double buttonWidthPercentage = 0.15;
        double paddingYPercentage = 0.05;
        
        // Bind text positions to the text field positions
        userTextField.prefWidthProperty().bind(loginPane.widthProperty().multiply(0.25));
        userTextField.prefHeightProperty().bind(loginPane.heightProperty().multiply(0.05));
        userTextField.layoutXProperty().bind(loginPane.widthProperty().multiply(0.4));
        userTextField.layoutYProperty().bind(loginPane.heightProperty().multiply(0.4));
        
        passwordField.prefWidthProperty().bind(loginPane.widthProperty().multiply(0.25));
        passwordField.prefHeightProperty().bind(loginPane.heightProperty().multiply(0.05));
        passwordField.layoutXProperty().bind(loginPane.widthProperty().multiply(0.4));
        passwordField.layoutYProperty().bind(userTextField.layoutYProperty().add(userTextField.heightProperty()).add(loginPane.heightProperty().multiply(paddingYPercentage)));
        
        loginBtn.prefWidthProperty().bind(loginPane.widthProperty().multiply(buttonWidthPercentage));
        loginBtn.prefHeightProperty().bind(loginPane.heightProperty().multiply(0.075));
        loginBtn.layoutXProperty().bind(loginPane.widthProperty().multiply(0.5));
        loginBtn.layoutYProperty().bind(passwordField.layoutYProperty().add(passwordField.heightProperty()).add(loginPane.heightProperty().multiply(paddingYPercentage)));
        
        invalidPassword.wrappingWidthProperty().bind(loginPane.widthProperty().multiply(widthPercentage));
        invalidPassword.layoutXProperty().bind(loginPane.widthProperty().multiply(0.45));
        invalidPassword.layoutYProperty().bind(loginBtn.layoutYProperty().add(loginBtn.heightProperty()).add(loginPane.heightProperty().multiply(paddingYPercentage)));
        
        // Add all elements to the scene so they appear on the screen
        loginPane.getChildren().addAll(userTextField, passwordField, loginBtn, invalidPassword, welcomeText, loginText);
        Scene loginScene = new Scene(loginPane, 600, 500);

        primaryStage.setScene(loginScene);
        loginScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        return loginScene;
    }
    

    public Scene getLoginScene() {
        return primaryStage.getScene();
    }
}