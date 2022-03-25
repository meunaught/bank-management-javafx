module com.example.projectyobank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.projectyobank to javafx.fxml;
    exports com.example.projectyobank;
}