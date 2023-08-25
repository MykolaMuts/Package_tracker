package com.company.javafx;

import java.io.IOException;
import java.sql.SQLException;

import com.company.database.DatabaseMethods;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClientController {

    @FXML
    private Label titleLabel;


    public void setTitleLabelText() {
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        titleLabel.setText(stage.getTitle());
        //stage.setTitle(titleLabel.getText());
    }

    @FXML
    public void handleSceneClicked(MouseEvent event) {
        setTitleLabelText();
    }


    public void createPackageClickButton(MouseEvent mouseEvent) throws IOException {
        JavafxHandler.openFXML("/PackageCreatingView.fxml", "Create new package");
        setTitleLabelText();
    }

    public void setAddressClickButton(MouseEvent mouseEvent) throws IOException {
        String client_id = titleLabel.getText();
        JavafxHandler.openFXML("/ClientSetAddressView.fxml", client_id);
    }

    public void deleteAddressClickButton(MouseEvent mouseEvent) throws IOException, SQLException {

        System.out.println("delete");

        int client_id = Integer.parseInt(titleLabel.getText());

        boolean res;
        try {
            res = DatabaseMethods.deleteAddressFromDatabase(client_id);
            if(res){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Address was deleted");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can't delete address");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}