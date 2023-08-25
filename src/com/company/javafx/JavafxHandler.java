package com.company.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class JavafxHandler extends Application {

    static public void openFXML(String fxmlPath, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(JavafxHandler.class.getResource(fxmlPath));
        AnchorPane root = (AnchorPane) loader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void start(Stage stage) throws Exception {
        openFXML("/DeliveryView.fxml", "Delivery");
    }


    public static void main(String[] args) {
        launch(args);
    }
}