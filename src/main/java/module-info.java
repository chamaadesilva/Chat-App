module org.example.chatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens org.example.chatapp to javafx.fxml;
    exports org.example.chatapp;
    exports org.example.chatapp.controllers;
    opens org.example.chatapp.controllers to javafx.fxml;
}