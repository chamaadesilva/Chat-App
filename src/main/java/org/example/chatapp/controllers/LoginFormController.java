package org.example.chatapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane LoginAnchorPane;

    public PasswordField passwordField;

    public TextField userNameTxt;

    @FXML
    void loginOnAction(ActionEvent actionEvent) throws IOException {
        String username = userNameTxt.getText().trim();
        String password = passwordField.getText().trim();
        String userPassCombo = username + ":" + password;

        switch (userPassCombo) {
            case "Sithari:1234":
                loadScene("ClientForm.fxml");
                break;
            case "Maneesha:1234":
                loadScene("ClientForm2.fxml");
                break;
            case "Dakshina:1234":
                loadScene("ClientForm3.fxml");
                break;
            default:
                System.out.println("Invalid username or password");
                break;
        }
    }

    private void loadScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginFormController.class.getResource("/org/example/chatapp/"+fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        userNameTxt.clear();
        passwordField.clear();
    }

}