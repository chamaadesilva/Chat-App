package org.example.chatapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class is the controller for the third client form of the chat application.
 * It handles the sending and receiving of messages, as well as the display of emojis and images.
 */
public class ClientFormController3 {

    static String emo1 = "", emo2 = "", emo3 = "";
    final int port3 = 1234;
    public AnchorPane emojiPane;
    public AnchorPane clientAnchorPane3;
    @FXML
    public TextArea textArea3;
    @FXML
    public TextField textMessage3;
    Socket socket3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;
    String message3 = "";

    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);
        new Thread(() -> {

            try {
                socket3 = new Socket("localhost", port3);

                dataInputStream3 = new DataInputStream(socket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(socket3.getOutputStream());
                while (!message3.equals("exit")) {
                    message3 = dataInputStream3.readUTF();
                    textArea3.appendText(message3 + "\n");
                }

                socket3.close();
                dataOutputStream3.close();
                dataInputStream3.close();

            } catch (IOException ignored) {
            }
        }).start();
    }

    public void sendOnAction3(ActionEvent actionEvent) throws IOException {
        dataOutputStream3.writeUTF(textMessage3.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream3.flush();
        textMessage3.clear();
        emojiPane.setVisible(false);
    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    public void textMessage3(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
    }

    public void l1emoOnAction(MouseEvent mouseEvent) {
        textMessage3.setText(textMessage3.getText() + "\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
        textMessage3.setText(textMessage3.getText() + "\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
        textMessage3.setText(textMessage3.getText() + "\uD83E\uDD2A");
    }

    public void setController3(AnchorPane anchorPane) {
        this.clientAnchorPane3 = anchorPane;
    }
}