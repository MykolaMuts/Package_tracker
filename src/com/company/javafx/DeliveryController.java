package com.company.javafx;

import com.company.database.DatabaseMethods;
import com.company.database.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class DeliveryController {

    @FXML
    public TableColumn arrivedColumn;

    @FXML
    public TableColumn nameColumn;

    @FXML
    public TableColumn sizeColumn;

    @FXML
    public TableColumn priceColumn;

    @FXML
    public TableColumn weightColumn;

    @FXML
    public TableColumn arrivedAtColumn;

    @FXML
    public TableView trackTable;

    @FXML
    public TextField searchTextField;


    public void addCompanyActionBar(ActionEvent actionEvent) throws IOException {
        JavafxHandler.openFXML("/CompanyCreatingView.fxml", "Add company");
    }

    public void registrationActionBar(ActionEvent actionEvent) throws IOException {
        JavafxHandler.openFXML("/RegistrationView.fxml", "Registration");
    }

    public void loginActionBar(ActionEvent actionEvent) throws IOException {
        JavafxHandler.openFXML("/LoginView.fxml", "Login");
    }

    public void searchButtonClick(MouseEvent mouseEvent) {
        int order_id = Integer.parseInt(searchTextField.getText());

        try {
            ObservableList<Search> orders = FXCollections.observableArrayList();
            Search search = DatabaseMethods.searchOrderFromDatabase(order_id);
            orders.add(search);

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            arrivedAtColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
            //arrivedColumn.setCellValueFactory(new PropertyValueFactory<>("arrivedLate"));

            trackTable.setItems(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}