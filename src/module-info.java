module SQL {

    exports com.company.javafx;

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    requires java.sql;

    opens com.company.javafx to javafx.fxml;

    opens com.company.database to javafx.base;

    //exports com.company.javafx to javafx.graphics;

    //opens com.company.javafx to javafx.fxml;
}