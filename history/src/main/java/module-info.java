// Для модуля history
module com.example.history {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires java.sql;

    opens com.example.history to javafx.fxml;
    opens com.example.history.controller to javafx.fxml;
    opens com.example.history.entity to javafx.base;

    exports com.example.history;
    exports com.example.history.controller;
    exports com.example.history.entity;
}