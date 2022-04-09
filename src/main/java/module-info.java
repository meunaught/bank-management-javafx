module com.projectyobank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.projectyobank to javafx.fxml;
    exports com.projectyobank;
    exports com.projectyobank.controllers;
    opens com.projectyobank.controllers to javafx.fxml;
}