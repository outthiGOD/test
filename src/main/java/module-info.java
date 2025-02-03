// Для модуля demo5
module com.example.demo5 {
    requires com.example.history;
    requires java.sql;

    opens com.example.demo5 to javafx.fxml;
    opens com.example.demo5.controllers to javafx.fxml;
    opens com.example.demo5.entity to javafx.base;

    exports com.example.demo5;
    exports com.example.demo5.controllers;
    exports com.example.demo5.entity;
}