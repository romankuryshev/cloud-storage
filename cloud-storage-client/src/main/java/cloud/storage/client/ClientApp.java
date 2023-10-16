package cloud.storage.client;

import cloud.storage.client.controller.AuthController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ClientApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("AuthPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        AuthController controller = fxmlLoader.getController();
        stage.setOnCloseRequest(event -> controller.exitAction());
        stage.setTitle("Вход");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}