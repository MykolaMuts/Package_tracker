package com.company.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DatabaseMethods {

    public static Search searchOrderFromDatabase(int order_id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT o.name, o.weight, o.price, ds.delivery_date, is_delivery_late(ds.delivery_date) as is_late FROM \"order\" o JOIN delivery_status ds ON o.order_id = ds.order_id WHERE o.order_id = ? LIMIT 1;")) {
            statement.setInt(1, order_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double weight = resultSet.getDouble("weight");
                    double price = resultSet.getDouble("price");
                    Date deliveryDate = resultSet.getDate("delivery_date");
                    boolean isLate = true; //resultSet.getBoolean("is_late");

                    return new Search(name, weight, price, deliveryDate, isLate);
                }

                return null;
            }
        }
    }


    public static boolean addOrderToDatabase(String name, String weight, String length, String width, String height, String price) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO \"order\" (name, weight, length, width, height, price) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setDouble(2, Double.parseDouble(weight));
            statement.setDouble(3, Double.parseDouble(length));
            statement.setDouble(4, Double.parseDouble(width));
            statement.setDouble(5, Double.parseDouble(height));
            statement.setDouble(6, Double.parseDouble(price));
            int result = statement.executeUpdate();
            return result > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }



    public static boolean addOrderStatusToDatabase(int order_id, int company_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("call insert_delivery_status(?, ?)");
            statement.setInt(1, order_id);
            statement.setInt(2, company_id);


            int result = statement.executeUpdate();
            return result > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static boolean addAddressToDatabase(String city, int zipCode, String street, String streetNumber, String room, int client_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO \"address\" (city, zip_code, street, street_number, room, \"client_id\") VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, city);
            statement.setInt(2, zipCode);
            statement.setString(3, street);
            statement.setString(4, streetNumber);
            statement.setString(5, room);
            statement.setInt(6, client_id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static boolean addCompanyToDatabase(String name, String period, String size, String weight, String price, String sPackage, String mPackage, String lPackage) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO \"company\" (name, delivery_period, size_limit, weight_limit, price_limit, price_for_small_package, price_for_medium_package, price_for_large_package) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, period);
            statement.setString(3, size);
            statement.setDouble(4, Double.parseDouble(weight));
            statement.setDouble(5, Double.parseDouble(price));
            statement.setDouble(6, Double.parseDouble(sPackage));
            statement.setDouble(7, Double.parseDouble(mPackage));
            statement.setDouble(8, Double.parseDouble(lPackage));
            int result = statement.executeUpdate();
            return result > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static ObservableList<Company> getCompaniesFromDatabase() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ObservableList<Company> companies = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT name, delivery_period, price_limit FROM company");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Company company = new Company(resultSet.getString("name"), resultSet.getString("delivery_period"), resultSet.getDouble("price_limit"));
                companies.add(company);
            }

            return companies;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }





    public static boolean setCompanyToOrder(String companyName, Integer order_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("UPDATE INTO \"order\" ");
            statement.setString(1, companyName);

            ResultSet rs = statement.executeQuery();
            return rs.next();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static int checkCredentials(String phoneOrEmail, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT client_id FROM client WHERE (email = ? OR phone_number = ?) AND password = ?");
            statement.setString(1, phoneOrEmail);
            statement.setString(2, phoneOrEmail);
            statement.setString(3, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("client_id");
            } else {
                return 0;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static int getClientId(String name, String email, String phone) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT client_id FROM client WHERE (email = ? OR phone_number = ?) AND name_and_surname = ?");
            statement.setString(1, email);
            statement.setString(2, phone);
            statement.setString(3, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("client_id");
            } else {
                return 0;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static int getCompanyId(String companyName, String deliveryPeriod, double price) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT company_id FROM company WHERE name = ? AND delivery_period = ? AND price_limit = ?");
            statement.setString(1, companyName);
            statement.setString(2, deliveryPeriod);
            statement.setDouble(3, price);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("company_id");
            } else {
                return 0;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }


    public static int getOrderId(String name, double price) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT order_id FROM \"order\" WHERE name = ? AND price = ? ");
            statement.setString(1, name);
            statement.setDouble(2, price);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("order_id");
            } else {
                return 0;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static boolean deleteAddressFromDatabase(int client_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement("DELETE FROM public.address WHERE client_id = ?;");
            statement.setInt(1, client_id);
            int result = statement.executeUpdate();
            return result > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
