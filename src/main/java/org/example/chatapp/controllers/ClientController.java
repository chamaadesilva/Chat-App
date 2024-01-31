package org.example.chatapp.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ClientController {

    public static String userName;
    static String emo1 = "", emo2 = "", emo3 = "";
    final int PORT = 5000;
    public AnchorPane emojiPane;
    public AnchorPane clientOneAnchorPane;
    public ScrollPane scrollpane;
    public VBox vbox;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    FileChooser fileChooser = new FileChooser();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textMessage;

    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);

        new Thread(() -> {
            try {
                accept = new Socket("localhost", PORT);

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText(message + "\n");
                }

                dataOutputStream.writeUTF(message.trim() + emo1 + emo2 + emo3);
                dataOutputStream.flush();

                accept.close();

                dataOutputStream.close();
                dataInputStream.close();
                textMessage.clear();

            } catch (IOException ignored) {
            }

        }).start();

    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) throws IOException, InterruptedException {
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ALL FILES", "*.*"), new FileChooser.ExtensionFilter("ZIP", "*.zip"), new FileChooser.ExtensionFilter("PDF", "*.pdf"), new FileChooser.ExtensionFilter("TEXT", "*.txt"), new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

        File file = fileChooser.showOpenDialog(new Stage());
        System.out.println(file);
    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(
                //TODO : remove this
                textMessage.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream.flush();
        emojiPane.setVisible(false);
        textMessage.clear();
    }

    public void textMessage(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
    }

    public void l1emoOnAction(MouseEvent mouseEvent) {
        textMessage.setText(textMessage.getText() + "\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
        textMessage.setText(textMessage.getText() + "\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
        textMessage.setText(textMessage.getText() + "\uD83E\uDD2A");
    }

    public void setController(AnchorPane anchorPane) {
        this.clientOneAnchorPane = anchorPane;
    }
}