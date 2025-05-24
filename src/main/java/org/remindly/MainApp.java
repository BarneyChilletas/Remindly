package org.remindly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("remindly-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 700);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Remindly");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){launch();}}