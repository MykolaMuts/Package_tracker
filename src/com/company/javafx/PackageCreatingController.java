package com.company.javafx;

        import java.io.IOException;
        import java.sql.SQLException;

        import com.company.database.DatabaseMethods;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

public class PackageCreatingController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField priceTextField;

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



    @FXML
    void createPackageButtonClick(MouseEvent event) {
        try {
            DatabaseMethods.addOrderToDatabase(
                    nameTextField.getText(),
                    weightTextField.getText(),
                    lengthTextField.getText(),
                    widthTextField.getText(),
                    heightTextField.getText(),
                    priceTextField.getText());

            int order_id = DatabaseMethods.getOrderId(nameTextField.getText(), Double.parseDouble(priceTextField.getText()));

            JavafxHandler.openFXML("/RecipientSetInfoView.fxml", String.valueOf(order_id));

        } catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error adding order to database");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}


