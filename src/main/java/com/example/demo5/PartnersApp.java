package com.example.demo5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PartnersApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PartnersApp.class.getResource("partners.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Hello!");
        stage.getIcons().add(new Image(PartnersApp.class.getResourceAsStream("/com/example/demo5/pictures/MasterFloor.png")));
        scene.getStylesheets().add(PartnersApp.class.getResource("/com/example/demo5/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}