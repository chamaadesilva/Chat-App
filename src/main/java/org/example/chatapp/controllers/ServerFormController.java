package org.example.chatapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is the controller for the server form of the chat application.
 * It handles the sending and receiving of messages from multiple clients.
 */
public class ServerFormController {
    private static final String text_chat_one = "";
    private static final String text_chat_two = "";
    private static final String text_chat_three = "";
    final int PORT = 5000, port = 8000, port3 = 1234;
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textMessage;
    String message = "", message2 = "", message3 = "";
    Socket accept, accept2, accept3;
    DataInputStream dataInputStream, dataInputStream2, dataInputStream3;
    DataOutputStream dataOutputStream, dataOutputStream2, dataOutputStream3;
    ServerSocket serverSocket, serverSocket_client2, serverSocket_client3;

    public void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                textArea.appendText("Server Started..\n");
                accept = serverSocket.accept();
                textArea.appendText("\nClient 1 Connected..");

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nClient 1 : " + message);

                    dataOutputStream.writeUTF("Client 1 : " + message.trim());
                    dataOutputStream.flush();
                    dataOutputStream2.writeUTF("Client 1 : " + message.trim());
                    dataOutputStream2.flush();
                    dataOutputStream3.writeUTF("Client 1 : " + message.trim());
                    dataOutputStream3.flush();
                }

                serverSocket.close();
                dataOutputStream.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException ignored) {
            }

        }).start();

        new Thread(() -> {
            try {
                serverSocket_client2 = new ServerSocket(port);
                accept2 = serverSocket_client2.accept();
                textArea.appendText("\nClient 2 Connected..");

                dataInputStream2 = new DataInputStream(accept2.getInputStream());
                dataOutputStream2 = new DataOutputStream(accept2.getOutputStream());

                while (!message2.equals("exit")) {
                    message2 = dataInputStream2.readUTF();
                    textArea.appendText("\nClient 2 : " + message2);

                    dataOutputStream2.writeUTF("Client 2 : " + message2.trim());
                    dataOutputStream2.flush();
                    dataOutputStream.writeUTF("Client 2 : " + message2.trim());
                    dataOutputStream.flush();
                    dataOutputStream3.writeUTF("Client 2 : " + message2.trim());
                    dataOutputStream3.flush();
                }

                serverSocket_client2.close();
                dataOutputStream.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException ignored) {
            }

        }).start();

        new Thread(() -> {
            try {
                serverSocket_client3 = new ServerSocket(port3);
                accept3 = serverSocket_client3.accept();
                textArea.appendText("\nClient 3 Connected..");

                dataInputStream3 = new DataInputStream(accept3.getInputStream());
                dataOutputStream3 = new DataOutputStream(accept3.getOutputStream());

                while (!message3.equals("exit")) {
                    message3 = dataInputStream3.readUTF();
                    textArea.appendText("\nClient 3 : " + message3);

                    dataOutputStream3.writeUTF("Client 3 : " + message3.trim());
                    dataOutputStream3.flush();
                    dataOutputStream.writeUTF("Client 3 : " + message3.trim());
                    dataOutputStream.flush();
                    dataOutputStream2.writeUTF("Client 3 : " + message3.trim());
                    dataOutputStream2.flush();
                }
                serverSocket_client3.close();
                dataOutputStream.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException ignored) {
            }

        }).start();
    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream.flush();
        dataOutputStream2.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream2.flush();
        dataOutputStream3.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream3.flush();
        textMessage.clear();
    }
}