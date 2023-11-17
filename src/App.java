import scenes.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");

        // Create the initial login scene
        LoginScene loginScene = new LoginScene(primaryStage);
        primaryStage.setScene(loginScene.createLoginScene());

        primaryStage.show();
    }

}