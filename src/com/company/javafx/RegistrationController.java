package com.company.javafx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.company.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegistrationController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private void addClientToDatabase() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO \"client\" (name_and_surname, email, phone_number, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, nameTextField.getText());
            statement.setString(2, emailTextField.getText());
            statement.setString(3, phoneTextField.getText());
            statement.setString(4, passwordTextField.getText());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void createAccountButtonClick(MouseEvent mouseEvent) {
        try {
            addClientToDatabase();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account Created");
            alert.setHeaderText(null);
            alert.setContentText("Account has been added to the database");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error adding account to database");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void provideAddressButtonClick(MouseEvent mouseEvent) throws IOException, SQLException {
        JavafxHandler.openFXML("/ClientSetAddressView.fxml", "Address");

    }

    public void signInButtonClick(MouseEvent mouseEvent) throws IOException {
        JavafxHandler.openFXML("/LoginView.fxml", "Login");
    }
}


