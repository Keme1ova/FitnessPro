package com.example.fitnespro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
        stage.setTitle("FitnessPro!");

        String css = this.getClass().getResource("base1.css").toExternalForm();
        scene.getStylesheets().add(css);

//        root.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());


        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}