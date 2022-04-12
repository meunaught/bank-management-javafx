module com.projectyobank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires de.jensd.fx.glyphs.fontawesome;


    opens com.projectyobank.controllers to javafx.fxml;
    exports com.projectyobank.controllers;
    opens com.projectyobank to javafx.fxml;
    exports com.projectyobank;

}