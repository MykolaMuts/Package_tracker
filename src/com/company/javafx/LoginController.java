package com.company.javafx;

import com.company.database.DatabaseConnection;
import com.company.database.DatabaseMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField phoneOrEmailTextField;

    @FXML
    private TextField passwordTextField;

    int clientID = 0;






    public void loginClickButton(MouseEvent mouseEvent) throws SQLException {
        int client_id;
        try {
            client_id = DatabaseMethods.checkCredentials(
                    phoneOrEmailTextField.getText(), passwordTextField.getText());
            if (client_id == 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mistake");
                alert.setHeaderText(null);
                alert.setContentText("Password not match");
                alert.showAndWait();

            } else {
                JavafxHandler.openFXML("/ClientView.fxml", String.valueOf(client_id));
            }
        } catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error with login to database");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}


