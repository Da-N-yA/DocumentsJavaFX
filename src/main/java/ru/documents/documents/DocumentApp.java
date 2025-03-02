package ru.documents.documents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DocumentApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DocumentApp.class.getResource("documents-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 440);
        stage.setMinHeight(440);
        stage.setMinWidth(440);
        stage.setTitle("Documents");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}