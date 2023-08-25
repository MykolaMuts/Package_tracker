package com.company.javafx;

import com.company.database.DatabaseConnection;
import com.company.database.DatabaseMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientSetAddressController {

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField streetNumberTextField;

    @FXML
    private TextField roomTextField;

    @FXML
    private Label titleLabel;

    public void setTitleLabelText() {
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        titleLabel.setText(stage.getTitle());
        stage.setTitle(titleLabel.getText());
    }

    @FXML
    public void handleSceneClicked(MouseEvent event) {
        setTitleLabelText();
    }

    public void setAddressButtonClick(MouseEvent mouseEvent) {
        setTitleLabelText();

        int client_id = Integer.parseInt(titleLabel.getText());

        System.out.println("client_id " + client_id);

        try {
            DatabaseMethods.addAddressToDatabase(
                    cityTextField.getText(),
                    Integer.parseInt(zipCodeTextField.getText()),
                    streetTextField.getText(),
                    streetNumberTextField.getText(),
                    roomTextField.getText(), client_id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("The new address was set");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success");
            alert.setHeaderText("The new address was set");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}