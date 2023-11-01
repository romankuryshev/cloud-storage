module cloud.storage.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires cloud.storage.command;
    requires io.netty.transport;
    requires io.netty.codec;
    requires org.slf4j.simple;
    requires org.slf4j;

    opens cloud.storage.client to javafx.fxml;
    exports cloud.storage.client;
    exports cloud.storage.client.controller;
    opens cloud.storage.client.controller to javafx.fxml;
}