module com.example.projectyobank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectyobank to javafx.fxml;
    exports com.example.projectyobank;
}