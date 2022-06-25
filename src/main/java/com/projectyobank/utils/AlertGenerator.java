package com.projectyobank.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class AlertGenerator {

    public boolean showErrorAlert(String title,String ContextText)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(ContextText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
        return false;
    }

    public boolean showConfirmationAlert(String title,String ContextText)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(ContextText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        if (alert.showAndWait().get() == ButtonType.OK) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void showInformationAlert(String title,String ContextText)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(ContextText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        if (alert.showAndWait().get() == ButtonType.OK) {

        }

    }
}
