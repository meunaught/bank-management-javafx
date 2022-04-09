module com.projectyobank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.projectyobank.controllers to javafx.fxml;
    exports com.projectyobank.controllers;
    opens com.projectyobank to javafx.fxml;
    exports com.projectyobank;
}