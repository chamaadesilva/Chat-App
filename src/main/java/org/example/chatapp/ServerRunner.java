package org.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(ServerRunner.class.getResource("ServerForm.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 600, 400);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Hello!");
        secondaryStage.setScene(scene2);
        secondaryStage.show();
    }
}