package com.company.javafx;

import com.company.database.DatabaseMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RecipientSetInfoController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField emailTextField;

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


    public void useHisAddressClickButton(MouseEvent mouseEvent) {
        int client_id;
        try {
            client_id = DatabaseMethods.getClientId(
                    nameTextField.getText(), emailTextField.getText(), phoneTextField.getText());
            if (client_id == 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mistake");
                alert.setHeaderText(null);
                alert.setContentText("Not exist");
                alert.showAndWait();

            } else {
                JavafxHandler.openFXML("/CompanySelectionView.fxml", titleLabel.getText());
            }
        } catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error with database");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void useAnotherAddressClickButton(MouseEvent mouseEvent) throws IOException {
        JavafxHandler.openFXML("/ClientSetAddressView.fxml", "His address");
    }
}