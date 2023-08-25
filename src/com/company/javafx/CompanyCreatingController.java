package com.company.javafx;

import com.company.database.DatabaseMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class CompanyCreatingController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField periodTextField;

    @FXML
    private TextField sizeTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField sPackageTextField;

    @FXML
    private TextField mPackageTextField;

    @FXML
    private TextField lPackageTextField;

    public void addCompanyClickButton(MouseEvent mouseEvent) {
        try {
            DatabaseMethods.addCompanyToDatabase(
                    nameTextField.getText(),
                    periodTextField.getText(),
                    sizeTextField.getText(),
                    weightTextField.getText(),
                    priceTextField.getText(),
                    sPackageTextField.getText(),
                    mPackageTextField.getText(),
                    lPackageTextField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("New company was added");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error adding company to database");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
}