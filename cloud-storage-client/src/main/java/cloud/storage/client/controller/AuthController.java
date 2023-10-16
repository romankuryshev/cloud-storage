package cloud.storage.client.controller;

import cloud.storage.client.network.Network;
import cloud.storage.command.LoginCommand;
import cloud.storage.command.RegisterCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    private Network network;
    @FXML
    private Label textNotification;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    public void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("") || password.equals("")){
            textNotification.setText("Введены некорректные данные.");
            return;
        }
        network.sendCommand(new RegisterCommand(username, password));
    }

    @FXML
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        network.sendCommand(new LoginCommand(username, password));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new Network((args) -> textNotification.setText((String) args[0]));
    }

    public void exitAction() {
        network.close();
        Platform.exit();
    }
}