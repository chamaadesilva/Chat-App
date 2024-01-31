package org.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(ClientLogin.class.getResource("LoginForm.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 506, 731);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
