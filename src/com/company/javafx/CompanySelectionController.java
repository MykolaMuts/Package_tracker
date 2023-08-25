package com.company.javafx;

import com.company.database.Company;
import com.company.database.DatabaseMethods;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanySelectionController {
    @FXML
    public TableColumn companyColumn;

    @FXML
    public TableColumn deliveryColumn;

    @FXML
    public TableColumn priceColumn;

    @FXML
    public TableView selectionTable;

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
        initializeScene();
    }

    public void initializeScene() {
        try {
            ObservableList<Company> companies = DatabaseMethods.getCompaniesFromDatabase();
            companyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryPeriod"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceLimit"));
            selectionTable.setItems(companies);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String[]> getSelectedData() {
        ObservableList<TablePosition> selectedCells = selectionTable.getSelectionModel().getSelectedCells();
        List<String[]> selectedData = new ArrayList<>();
        for (TablePosition tablePosition : selectedCells) {
            int row = tablePosition.getRow();
            String company = companyColumn.getCellData(row).toString();
            String delivery = deliveryColumn.getCellData(row).toString();
            String priceStr = priceColumn.getCellData(row).toString();
            Double price = Double.parseDouble(priceStr);
            selectedData.add(new String[]{company, delivery, price.toString()});
        }
        return selectedData;
    }

    public void getSelectedCompanyClickButton(MouseEvent mouseEvent) throws SQLException {
        try {
            List<String[]> selectedData = getSelectedData();
            if (!selectedData.isEmpty()) {
                String[] data = selectedData.get(0);
                String company = data[0];
                String delivery = data[1];
                Double price = Double.parseDouble(data[2]);

                int companyId = DatabaseMethods.getCompanyId(company, delivery, price);

                int orderId = Integer.parseInt(titleLabel.getText());

                DatabaseMethods.addOrderStatusToDatabase(orderId, companyId);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Company selected");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error selecting a company");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }


}


